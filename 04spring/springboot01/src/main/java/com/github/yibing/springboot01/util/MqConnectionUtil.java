package com.github.yibing.springboot01.util;

import java.util.HashMap;

//
//import com.rabbitmq.client.Connection;
//import com.rabbitmq.client.ConnectionFactory;
//
//import java.io.IOException;
//
public class MqConnectionUtil {
//
//    public static Connection getConnection() throws IOException {
//        ConnectionFactory factory = new ConnectionFactory();
//        factory.setHost("localhost");
//        factory.setPort(5672);
//        factory.setUsername("guest");
//        factory.setPassword("guest");
//        factory.setVirtualHost("testHost");
//        return factory.newConnection();
//    }


    public static void main(String[] args) {
        HashMap<String, Object> hashMap = new HashMap<>(80);
        hashMap.put("0",0);
        hashMap.put("1",1);
        hashMap.put("2",2);
    }
}
