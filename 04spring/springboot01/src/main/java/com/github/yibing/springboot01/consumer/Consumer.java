//package com.github.yibing.springboot01.consumer;
//
//import com.github.yibing.springboot01.util.MqConnectionUtil;
//import com.rabbitmq.client.Channel;
//import com.rabbitmq.client.Connection;
//import com.rabbitmq.client.QueueingConsumer;
//
//import java.io.IOException;
//
//public class Consumer {
//    public static void main(String[] args) throws IOException, InterruptedException {
//        Connection connection = MqConnectionUtil.getConnection();
//        Channel channel = connection.createChannel();
//        // 声明队列
//        channel.queueDeclare("q_test_1", false, false, false, null);
//        // 创建消费队列
//        QueueingConsumer consumer = new QueueingConsumer(channel);
//        // 消费消息
//        channel.basicConsume("q_test_1", consumer);
//        // 转换消费的消息
//        while (true) {
//            QueueingConsumer.Delivery delivery = consumer.nextDelivery();
//            String message = new String(delivery.getBody());
//            System.out.println("收到消息:" + message);
//        }
//
//    }
//}
