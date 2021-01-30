package org.database.demo.dao;

import org.database.demo.dao.OrderDao;
import org.database.demo.entity.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@MapperScan(basePackages = "org.database.demo.dao")
public class OrderTest {

    @Autowired
    private OrderDao orderDao;

    @Test
    public void test(){
        Order order = new Order(1, 1);
        int num = orderDao.insertOne(order);
    }
}
