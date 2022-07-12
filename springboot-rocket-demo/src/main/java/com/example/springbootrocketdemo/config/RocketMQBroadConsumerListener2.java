package com.example.springbootrocketdemo.config;

import org.apache.rocketmq.spring.annotation.MessageModel;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Service;

/**
 * 广播消息
 * 配置RocketMQ监听
 * MessageModel.CLUSTERING:集群模式
 * MessageModel.BROADCASTING:广播模式
 * @author qzz
 */
@Service
@RocketMQMessageListener(consumerGroup = "test-broad",topic = "test-topic-broad",messageModel = MessageModel.BROADCASTING)
public class RocketMQBroadConsumerListener2 implements RocketMQListener<String> {

    @Override
    public void onMessage(String s) {
        System.out.println("广播消息2 广播模式，消费消息："+s);
    }
}
