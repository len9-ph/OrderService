package com.lgadetsky.orderservice.service;

import com.lgadetsky.orderservice.model.Order;
import com.lgadetsky.orderservice.repository.OrderRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService{

    @Autowired
    private OrderRepositoryImpl orderRepository;

    @Override
    public void create(Order order) {
        orderRepository.insertOrder(order);
    }

    @Override
    public List readAll() {
        return null;
    }

    @Override
    public Order readById(long id) {
        return orderRepository.selectOrder(id);
    }

    @Override
    public boolean update(Order order) {
        return false;
    }

    @Override
    public boolean delete(long id) {
        return false;
    }
}
