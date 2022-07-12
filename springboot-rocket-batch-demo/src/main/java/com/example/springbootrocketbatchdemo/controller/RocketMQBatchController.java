package com.example.springbootrocketbatchdemo.controller;

import com.example.springbootrocketbatchdemo.splitter.ListSplitter;
import io.netty.util.CharsetUtil;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
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
     * 发送批量消息(小于4MB)
     * 发送批量消息，最主要的区别是在发送消息的send方法入参一个List。
     */
    @RequestMapping("/testBatchSend")
    public void testSyncSend(){

        List<org.springframework.messaging.Message<String>> messageList = new ArrayList<>();
        for(int i=0;i<10;i++){
            messageList.add(MessageBuilder.withPayload("批量消息"+(i+1)).build());
        }
        //参数一：topic   如果想添加tag,可以使用"topic:tag"的写法
        //参数二：消息内容
        SendResult sendResult = rocketMQTemplate.syncSend("test-topic-batch",messageList,6000);
        System.out.println(sendResult);
    }

    /**
     * 发送批量消息(大于4MB)
     * 发送批量消息，最主要的区别是在发送消息的send方法入参一个List。
     */
    @RequestMapping("/testBatchSendSplitter")
    public void testBatchSendSplitter(){

        List<Message> messageList = new ArrayList<>();
        for(int i=0;i<1000;i++){
            //添加内容
            byte[] bytes = (("批量消息"+i).getBytes(CharsetUtil.UTF_8));
            messageList.add(new Message("message-topic-batch","message-tag-batch",bytes));
        }

        //切割消息
        //把大的消息分裂传给你若干个小的消息
        ListSplitter splitter = new ListSplitter(messageList);
        while(splitter.hasNext()){
            List<Message> listItem = splitter.next();
            //发送消息
            //参数一：topic   如果想添加tag,可以使用"topic:tag"的写法
            //参数二：消息内容
            SendResult sendResult = rocketMQTemplate.syncSend("message-topic-batch",messageList,6000);
            System.out.println(sendResult);
        }
    }

}
