package com.example.springbootrocketfilter.controller;

import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * 消息过滤
 * @author qzz
 */
@RestController
public class RocketMQFilterCnntroller {

    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    /**
     * 发送带tag消息，测试根据 tag过滤消息
     */
    @RequestMapping("/testMessageSendByTag")
    public void sendMessageByTag(){
        //构造消息1
        Message msg1 = MessageBuilder.withPayload("rocket 消息过滤测试---tag1").build();
        //构造消息2
        Message msg2 = MessageBuilder.withPayload("rocket 消息过滤测试---tag2").build();
        //构造消息3
        Message msg3 = MessageBuilder.withPayload("rocket 消息过滤测试---tag3").build();

        //发送消息
        //参数一：topic   如果想添加tag,可以使用"topic:tag"的写法(消息发送端在设置消息目的地时通过topic与tag拼接方式)
        //参数二：消息内容
        rocketMQTemplate.syncSend("test-rocketmq-filter:tag1",msg1);
        rocketMQTemplate.syncSend("test-rocketmq-filter:tag2",msg2);
        rocketMQTemplate.syncSend("test-rocketmq-filter:tag3",msg3);
    }

    /**
     * 测试根据 sql92过滤消息
     */
    @RequestMapping("/testMessageSendBySql")
    public void testMessageSendBySql(){
        //构造消息1
        Message msg1 = MessageBuilder.withPayload("rocket 消息过滤测试---sql92").build();

        //设置消息的属性（header）信息
        Map<String,Object> headers = new HashMap<>();
        headers.put("a",3);

        //发送消息
        //参数一：topic   如果想添加tag,可以使用"topic:tag"的写法
        //参数二：消息内容
        rocketMQTemplate.convertAndSend("test-rocketmq-sql",msg1,headers);
    }

}
