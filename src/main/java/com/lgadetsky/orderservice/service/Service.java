package com.lgadetsky.orderservice.service;

import com.lgadetsky.orderservice.model.Order;

import java.util.List;

/**
 * @author Leonid Gadetsky
 */
public interface Service {
    Order create(Order order);
    Order findById(int id);
    List<Order> findAll();
    Order update(int id, Order order);
    void deleteById(int id);
}
