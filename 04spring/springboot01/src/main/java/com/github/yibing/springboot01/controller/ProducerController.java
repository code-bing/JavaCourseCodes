package com.github.yibing.springboot01.controller;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
public class ProducerController {

    @Autowired
    private AmqpTemplate amqpTemplate;

    @RequestMapping("/send")
    public String send() {
        String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        String context = "hello" + date;
        amqpTemplate.convertAndSend(context);
        return context;
    }
}
