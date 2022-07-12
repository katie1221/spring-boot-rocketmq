package com.example.springbootrocketdemo.config;

import com.alibaba.fastjson.JSONObject;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Service;
import java.util.Map;

/**
 * 消费延时消息
 * 配置RocketMQ监听
 * @author qzz
 */
@Service
@RocketMQMessageListener(consumerGroup = "test-delay",topic = "test-topic-delay")
public class RocketMQDelayConsumerListener implements RocketMQListener<String> {

    @Override
    public void onMessage(String s) {
        Map<String,Object> orderMap = JSONObject.parseObject(s,Map.class);
        String orderNumber = String.valueOf(orderMap.get("orderNumber"));
        String createTime = String.valueOf(orderMap.get("createTime"));
        //根据orderNumber 查询订单状态，若为未支付，则消息订单并修改库存
        //....
        System.out.println("consumer 延时消息消费 orderNumber:"+orderNumber+",createTime:"+createTime);
    }
}

