//package com.github.yibing.springboot01.producer;
//
//import com.github.yibing.springboot01.util.MqConnectionUtil;
//import com.rabbitmq.client.Channel;
//import com.rabbitmq.client.Connection;
//
//import java.io.IOException;
//
//public class Producer {
//    public static void main(String[] args) throws IOException {
//        Connection connection = MqConnectionUtil.getConnection();
//        Channel channel = connection.createChannel();
//        // 声明队列
//        channel.queueDeclare("q_test_1", false, false, false, null);
//        // 发送消息
//        channel.basicPublish("", "q_test_1", null, "hello a".getBytes());
//        // 关闭通道
//        channel.close();
//        // 关闭通道
//        connection.close();
//    }
//}
