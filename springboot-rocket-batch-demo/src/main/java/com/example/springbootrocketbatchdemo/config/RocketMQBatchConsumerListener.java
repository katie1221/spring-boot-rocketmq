package com.example.springbootrocketbatchdemo.config;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.spring.annotation.ConsumeMode;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.apache.rocketmq.spring.core.RocketMQPushConsumerLifecycleListener;
import org.springframework.stereotype.Service;

/**
 * 消费批量消息
 * 配置RocketMQ监听
 * ConsumeMode.ORDERLY:顺序消费
 *
 * RocketMQPushConsumerLifecycleListener：当@RocketMQMessageListener中的配置不⾜以满⾜我们的需求时，可以实现该接⼝直接更改消费者类DefaultMQPushConsumer的配置
 * @author qzz
 */
@Service
@RocketMQMessageListener(consumerGroup = "test-batch",topic = "test-topic-batch",consumeMode = ConsumeMode.ORDERLY)
public class RocketMQBatchConsumerListener implements RocketMQListener<String> , RocketMQPushConsumerLifecycleListener {

    /**
     * 客户端收到的消息
     * @param s
     */
    @Override
    public void onMessage(String s) {
        System.out.println("consumer 批量消息，收到消息:"+s);
    }

    /**
     * 对消费者客户端的一些配置
     * 重写prepareStart方法
     * @param defaultMQPushConsumer
     */
    @Override
    public void prepareStart(DefaultMQPushConsumer defaultMQPushConsumer) {
        //设置每次消息拉取的时间间隔 单位 毫秒
        defaultMQPushConsumer.setPullInterval(1000);
        //最小消费线程池数
        defaultMQPushConsumer.setConsumeThreadMin(1);
        //最大消费线程池数
        defaultMQPushConsumer.setConsumeThreadMax(10);
        //设置消费者单次批量消费的消息数目上限    默认1
        defaultMQPushConsumer.setConsumeMessageBatchMaxSize(3);
        //设置每个队列每次拉取的最大消费数
        defaultMQPushConsumer.setPullBatchSize(16);
    }
}

