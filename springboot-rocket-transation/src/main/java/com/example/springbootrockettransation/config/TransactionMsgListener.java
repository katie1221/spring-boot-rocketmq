package com.example.springbootrockettransation.config;

import org.apache.rocketmq.client.producer.LocalTransactionState;
import org.apache.rocketmq.spring.annotation.RocketMQTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionState;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;

/**
 * 生产者消息监听器：
 *    用于监听本地事务执行的状态和检查本地事务状态。
 * @author qzz
 */
@RocketMQTransactionListener
public class TransactionMsgListener implements RocketMQLocalTransactionListener {

    /**
     * 执行本地事务（在发送消息成功时执行）
     * @param message
     * @param o
     * @return commit or rollback or unknown
     */
    @Override
    public RocketMQLocalTransactionState executeLocalTransaction(Message message, Object o) {
        //TODO 开启本地事务（实际就是我们的jdbc操作）
        //TODO 执行业务代码（例如插入订单数据库表等）
        //TODO 提交或回滚本地事务

        //模拟一个处理结果
        int index=3;
        /**
         * 模拟返回事务状态
         */
        switch (index){
            case 1:
                //处理业务
                String jsonStr = new String((byte[]) message.getPayload(), StandardCharsets.UTF_8);
                System.out.println("本地事务回滚，回滚消息，"+jsonStr);
                //返回ROLLBACK状态的消息会被丢弃
                return RocketMQLocalTransactionState.ROLLBACK;
            case 3:
                //返回UNKNOW状态的消息会等待Broker进行事务状态回查
                return RocketMQLocalTransactionState.UNKNOWN;
            default:
                System.out.println("事务提交，消息正常处理");
                //返回COMMIT状态的消息会立即被消费者消费到
                return RocketMQLocalTransactionState.COMMIT;
        }
    }

    /**
     * 检查本地事务的状态
     * @param message
     * @return
     */
    @Override
    public RocketMQLocalTransactionState checkLocalTransaction(Message message) {
        String jsonStr = new String((byte[]) message.getPayload(), StandardCharsets.UTF_8);
        System.out.println("调用回查本地事务接口："+jsonStr);
        return RocketMQLocalTransactionState.COMMIT;
    }
}
