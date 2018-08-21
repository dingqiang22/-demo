package com.dazhi.demo.dazhi.web;

import com.dazhi.demo.dazhi.mq.HelloSender;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/mq")
public class MQController {
    @Resource
    private HelloSender helloSender;

    @RequestMapping(value="send")
    public String send(){
        helloSender.send();
        return "成功发送";
    }
}
