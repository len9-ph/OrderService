package com.lgadetsky.orderservice.repository;


import com.lgadetsky.orderservice.model.Order;
import com.lgadetsky.orderservice.repository.mapper.OrderItemMapper;
import com.lgadetsky.orderservice.repository.mapper.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class OrderRepositoryImpl implements OrderRepository {
    @Autowired
    public OrderMapper orderMapper;
    @Autowired
    public OrderItemMapper orderItemMapper;

    @Override
    @Transactional
    public Order save(Order order) {
        orderMapper.insertOrder(order);
    }

    @Override
    public Order findById(long id) {
        return null;
    }

    @Override
    public Order update(Order order) {
        return null;
    }

    @Override
    public void delete(long id) {

    }
}
