package com.example.springbootrocketfilter.config;

import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.spring.annotation.MessageModel;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.annotation.SelectorType;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Service;

/**
 * 消费消息---消息过滤  sql
 * 配置RocketMQ监听
 *
 * selectorType：指明了消息过滤使用SQL92方式
 * selectorExpression：指明了只能接收消息属性（header）中a=1的消息， 默认值*，代表全部
 * messageModel:指明了消息消费的模式，默认值为MessageModel.CLUSTERING（每条消息只能有一个消费者进行消费）；
 *                                      MessageModel.BROADCASTING(广播消息，所有订阅者都能收到消息)
 *
 * @author qzz
 */
@Service
@RocketMQMessageListener(consumerGroup = "test-sql",topic = "test-rocketmq-sql"
        ,messageModel = MessageModel.CLUSTERING
        ,selectorExpression = "a=1",selectorType = SelectorType.SQL92)
public class RocketMQFilterSqlListener implements RocketMQListener<MessageExt> {

    @Override
    public void onMessage(MessageExt message) {

        System.out.println("消费消息："+new String(message.getBody()));
        System.out.println("消费消息："+message.getProperties());
    }
}
