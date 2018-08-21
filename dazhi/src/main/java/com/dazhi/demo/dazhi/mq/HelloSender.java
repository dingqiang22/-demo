package com.dazhi.demo.dazhi.mq;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class HelloSender {

    @Autowired
    private AmqpTemplate rabbitTemplate;

    public String send() {
        String context = "hello " + "你好我是单对单DQ测试";
        System.out.println("单对单DQ发送参数 : " + context);
        this.rabbitTemplate.convertAndSend("DQDEMO", context);
        return "发送成功";
    }

}