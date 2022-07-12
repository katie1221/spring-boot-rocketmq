package com.example.springbootrocketdemo.controller;

import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 广播消息
 * @author qzz
 */
@RestController
public class RocketMQBroadCOntroller {

    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    /**
     * 发送广播消息
     */
    @RequestMapping("/testBroadSend")
    public void testSyncSend(){
        //参数一：topic   如果想添加tag,可以使用"topic:tag"的写法
        //参数二：消息内容
        for(int i=0;i<10;i++){
            rocketMQTemplate.convertAndSend("test-topic-broad","test-message"+i);
        }
    }
}
