package com.lgadetsky.orderservice.service;

import com.lgadetsky.orderservice.model.Order;

import java.util.List;

public interface Service {
    Order create(Order order);
    Order findById(int id);
    List<Order> findAll();
    Order update(Order order);
    void deleteById(int id);
}
