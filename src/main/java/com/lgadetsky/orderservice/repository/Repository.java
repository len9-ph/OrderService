package com.lgadetsky.orderservice.repository;

import com.lgadetsky.orderservice.model.Order;

import java.util.List;

/**
 * @author Leonid Gadetsky
 * Интерфейс описывающий методы для работы с базой данных
 */
public interface Repository {
    Order create(Order order);
    Order selectOrderById(int id);
    List<Order> selectAll();
    Order update(Order order);
    void deleteOrderById(int id);
}
