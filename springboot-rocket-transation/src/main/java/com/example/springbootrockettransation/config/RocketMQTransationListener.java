package com.example.springbootrockettransation.config;

import org.apache.rocketmq.spring.annotation.MessageModel;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Service;

/**
 * 消费事务消息
 * 配置RocketMQ监听
 * @author qzz
 */
@Service
@RocketMQMessageListener(consumerGroup = "test-transation",topic = "test-topic-transation",messageModel = MessageModel.CLUSTERING)
public class RocketMQTransationListener implements RocketMQListener<String> {

    @Override
    public void onMessage(String s) {
        System.out.println("消费消息 事务消息："+s);
    }
}