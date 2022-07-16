package com.lgadetsky.orderservice.repository;

import com.lgadetsky.orderservice.model.Order;
import com.lgadetsky.orderservice.repository.mapper.OrderItemMapper;
import com.lgadetsky.orderservice.repository.mapper.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/* TODO
*   Доделать update
*            insert
*            */
/**
 * Implements all method for with DB
 */
@Repository
public class OrderRepositoryImpl implements OrderRepository{

    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private OrderItemMapper orderItemMapper;

    @Override
    @Transactional
    public Order insertOrder(Order order) {
        orderMapper.insertOrder(order);
        return order;
    }

    @Override
    public Order selectOrder(long id) {
        return orderMapper.findOrderById(id);
    }

    @Override
    public void updateOrder(Order order) {
        orderMapper.update(order);
    }

    @Override
    public void deleteOrder(long id) {
        orderMapper.delete(id);
    }
}
