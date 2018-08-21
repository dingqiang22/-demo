package com.dazhi.demo.dazhi.mq;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "DQDEMO")
public class HelloReceiver2 {

    @RabbitHandler
    public void process(String hello) {
        System.out.println("单对单2接收参数  : " + hello);
    }

}
