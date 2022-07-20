package com.lgadetsky.orderservice.repository;

import com.lgadetsky.orderservice.model.Order;

public interface Repository {
    Order create(Order order);
    Order selectOrderById(int id);
    Order update(Order order);
    void deleteOrderById(int id);
}
