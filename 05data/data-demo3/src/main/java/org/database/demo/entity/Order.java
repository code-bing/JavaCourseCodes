package org.database.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    private Integer id;
    private Integer userId;
    private String commodities;
    private Integer status;
    private String deliverStatus;
    private Integer totalPrice;
    private Integer createTime;
    private Integer updateTime;

    public Order(Integer id, Integer userId) {
        this.id = id;
        this.userId = userId;
    }
}
