package com.example.springbootrocketdemo.controller;

import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.ArrayList;
import java.util.List;

/**
 * 批量消息
 * @author qzz
 */
@RestController
public class RocketMQBatchController {

    @Autowired
    private RocketMQTemplate rocketMQTemplate;


    /**
     * 发送批量消息
     * 发送批量消息，最主要的区别是在发送消息的send方法入参一个List。
     */
    @RequestMapping("/testBatchSend")
    public void testSyncSend(){

        List<Message<String>> messageList = new ArrayList<>();
        for(int i=0;i<10;i++){
            messageList.add(MessageBuilder.withPayload("批量消息"+(i+1)).build());
        }
        //参数一：topic   如果想添加tag,可以使用"topic:tag"的写法
        //参数二：消息内容
        SendResult sendResult = rocketMQTemplate.syncSend("test-topic-batch",messageList,6000);
        System.out.println(sendResult);
    }

}
