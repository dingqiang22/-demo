package com.dazhi.demo.dazhi.conf;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by xubing on 2018/3/10.
 */
@Configuration
public class RabbitmqConfiguration {


    /**
     * 实例化队列
     *
     * @return
     */
    @Bean
    public Queue Queue() {
        return new Queue("DQDEMO");
    }


}