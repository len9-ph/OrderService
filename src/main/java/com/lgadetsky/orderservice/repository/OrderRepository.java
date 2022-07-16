package com.lgadetsky.orderservice.repository;

import com.lgadetsky.orderservice.model.Order;

public interface OrderRepository {
    Order save(Order order);
    Order findById(long id);
    Order update(Order order);
    void delete(long id);
}
