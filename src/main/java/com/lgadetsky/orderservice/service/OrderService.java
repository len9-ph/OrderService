package com.lgadetsky.orderservice.service;

import com.lgadetsky.orderservice.model.Order;
import com.lgadetsky.orderservice.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author Leonid Gadetsky
 */
@org.springframework.stereotype.Service
public class OrderService implements Service{
    private final OrderRepository orderRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public Order create(Order order) {
        return orderRepository.create(order);
    }

    @Override
    public Order findById(int id) {
        return orderRepository.selectOrderById(id);
    }

    @Override
    public List<Order> findAll() {
        return orderRepository.selectAll();
    }

    @Override
    public Order update(Order order) {
        return orderRepository.update(order);
    }

    @Override
    public void deleteById(int id) {
        orderRepository.deleteOrderById(id);
    }
}
