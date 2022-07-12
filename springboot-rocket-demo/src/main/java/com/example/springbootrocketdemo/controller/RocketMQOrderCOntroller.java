package com.example.springbootrocketdemo.controller;

import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 模拟两个订单发送消息
 *
 * 顺序信息的三种方式：同步、异步、单向
 * syncSendOrderly，发送同步顺序消息；
 * asyncSendOrderly，发送异步顺序消息；
 * sendOneWayOrderly，发送单向顺序消息；
 * 一般我们用第一种发送同步顺序消息；
 * @author qzz
 */
@RestController
public class RocketMQOrderCOntroller {

    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    /**
     * 发送同步顺序消息
     */
    @RequestMapping("/testSyncOrderSend")
    public void testSyncSend(){
        //参数一：topic   如果想添加tag,可以使用"topic:tag"的写法
        //参数二：消息内容
        //参数三：hashKey 用来计算决定消息发送到哪个消息队列， 一般是订单ID，产品ID等
        rocketMQTemplate.syncSendOrderly("test-topic-orderly","111111创建","111111");
        rocketMQTemplate.syncSendOrderly("test-topic-orderly","111111支付","111111");
        rocketMQTemplate.syncSendOrderly("test-topic-orderly","111111完成","111111");

        rocketMQTemplate.syncSendOrderly("test-topic-orderly","222222创建","222222");
        rocketMQTemplate.syncSendOrderly("test-topic-orderly","222222支付","222222");
        rocketMQTemplate.syncSendOrderly("test-topic-orderly","222222完成","222222");
    }
}
