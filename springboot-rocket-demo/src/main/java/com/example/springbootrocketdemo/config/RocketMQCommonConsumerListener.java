package com.example.springbootrocketdemo.config;

import org.apache.rocketmq.spring.annotation.ConsumeMode;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Service;

/**
 * 消费顺序消息
 * 配置RocketMQ监听
 *
 * ConsumeMode.ORDERLY:顺序消费
 * @author qzz
 */
@Service
@RocketMQMessageListener(consumerGroup = "test-orderly",topic = "test-topic-orderly",consumeMode = ConsumeMode.ORDERLY)
public class RocketMQCommonConsumerListener implements RocketMQListener<String> {

    @Override
    public void onMessage(String s) {
        System.out.println("consumer 顺序消费，收到消息:"+s);
    }
}

