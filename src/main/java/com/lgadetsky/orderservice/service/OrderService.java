package com.lgadetsky.orderservice.service;

import com.lgadetsky.orderservice.model.Order;

import java.util.List;

public interface OrderService {
    void create(Order order);
    List readAll();
    Order readById(long id);
    boolean update(Order order);
    boolean delete(long id);
}
