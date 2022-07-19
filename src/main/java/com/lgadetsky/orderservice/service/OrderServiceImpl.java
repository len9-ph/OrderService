package com.lgadetsky.orderservice.service;

import com.lgadetsky.orderservice.model.Order;
import com.lgadetsky.orderservice.repository.OrderRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService{

    private final OrderRepositoryImpl orderRepository;
    @Autowired
    public OrderServiceImpl(OrderRepositoryImpl orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public Order create(Order order) {
        return orderRepository.insertOrder(order);
    }

    @Override
    public List<Order> readAll() {
        return orderRepository.selectAllOrders();
    }

    @Override
    public Order readById(long id) {
        return orderRepository.selectOrder(id);
    }

    @Override
    public Order update(Order order) {
        return orderRepository.updateOrder(order);
    }

    @Override
    public void delete(long id) {
        orderRepository.deleteOrder(id);
    }
}
