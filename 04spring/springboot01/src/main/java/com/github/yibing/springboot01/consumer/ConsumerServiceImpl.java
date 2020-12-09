package com.github.yibing.springboot01.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service("service_1")
@RabbitListener(queues = "q_test_1")
public class ConsumerServiceImpl {
    @RabbitHandler
    public void getMessage(String message) {
        System.out.println("1" + message);
    }
}
