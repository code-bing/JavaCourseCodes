package org.database.demo.dao;

import org.database.demo.dao.OrderDao;
import org.database.demo.entity.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.internal.matchers.Or;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.HashMap;
import java.util.List;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@MapperScan(basePackages = "org.database.demo.dao")
public class OrderTest {

    @Autowired
    private OrderDao orderDao;

    @Test
    public void test(){
//        Order order = new Order(1, 2);
//        int num = orderDao.insertOne(order);

        HashMap<String, Object> condition = new HashMap<>(1);
        condition.put("user_id",1);
        List<HashMap<String, Object>> list = orderDao.query(condition);
        list.forEach(System.out::println);
        Order order = new Order(1, 3);
        int num1 = orderDao.update(order);
    }
}
