package com.example.springbootrocketfilter.config;

import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.spring.annotation.MessageModel;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.annotation.SelectorType;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Service;

/**
 * 消费消息---消息过滤 tag
 * 配置RocketMQ监听
 *
 * selectorType：指明了消息选择通过tag的方法，默认值SelectorType.TAG
 * selectorExpression：指明能够接收哪些tag,多个tag通过 || 或的方法， 默认值*，代表全部
 * messageModel:指明了消息消费的模式，默认值为MessageModel.CLUSTERING（每条消息只能有一个消费者进行消费）；
 *                                      MessageModel.BROADCASTING(广播消息，所有订阅者都能收到消息)
 *
 * @author qzz
 */
@Service
@RocketMQMessageListener(consumerGroup = "test-filter",topic = "test-rocketmq-filter"
        ,messageModel = MessageModel.CLUSTERING
        ,selectorExpression = "tag1||tag2",selectorType = SelectorType.TAG)
public class RocketMQFilterListener implements RocketMQListener<MessageExt> {

    @Override
    public void onMessage(MessageExt message) {

        System.out.println("消费消息："+new String(message.getBody()));
        System.out.println("消费消息："+message.getProperties());
    }
}
