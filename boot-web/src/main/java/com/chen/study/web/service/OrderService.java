package com.chen.study.web.service;

import com.chen.study.web.entity.Order;
import com.chen.study.web.mapper.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class OrderService {
    @Autowired
    OrderMapper orderMapper;

    public static Long orderId = 1L;
    public static Long userId = 1L;

    public void insert() {
        for (int i = 1; i <= 100; i++) {
            Order order = new Order();
            order.setOrderId(i);
            order.setUserId(i);
            orderId++;
            userId++;
            orderMapper.insert(order);
        }
    }

    public Order getOrderInfoByOrderId(Integer id) {
        return orderMapper.selectByPrimaryKey(id);
    }

}
