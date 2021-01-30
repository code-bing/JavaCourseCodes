package org.database.demo.dao;

import org.database.demo.entity.Order;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderDao {

    int insertOne(Order order);
}
