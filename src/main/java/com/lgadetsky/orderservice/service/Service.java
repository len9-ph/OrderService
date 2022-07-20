package com.lgadetsky.orderservice.service;

import com.lgadetsky.orderservice.model.Order;

public interface Service {
    Order create(Order order);
    Order findById(int id);
    Order update(Order order);
    void deleteById(int id);
}
