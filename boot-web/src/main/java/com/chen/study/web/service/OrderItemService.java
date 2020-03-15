package com.chen.study.web.service;

import com.chen.study.web.entity.OrderItem;
import com.chen.study.web.mapper.OrderItemMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderItemService {
    @Autowired
    OrderItemMapper orderItemMapper;

    public void insert() {
        for (int i = 1; i <= 100; i++) {
            OrderItem orderItem = new OrderItem();
            orderItem.setItemId(i);
            orderItem.setOrderId(i);
            orderItem.setUserId(i);

            orderItemMapper.insert(orderItem);
        }
    }

    public OrderItem getOrderItemByItemId(Integer id) {
        return orderItemMapper.selectByPrimaryKey(id);
    }

}
