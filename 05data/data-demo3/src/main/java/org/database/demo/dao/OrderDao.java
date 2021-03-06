package org.database.demo.dao;

import org.database.demo.entity.Order;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Repository
public interface OrderDao {

    int insertOne(Order order);

    List<HashMap<String, Object>> query(HashMap<String, Object> condition);

    int update(Order order);
}
