package com.lgadetsky.orderservice.service;

import com.lgadetsky.orderservice.model.Order;

import java.util.List;

public interface OrderService {
    Order create(Order order);
    List readAll();
    Order readById(long id);
    Order update(Order order);
    void delete(long id);
}
