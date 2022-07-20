package com.lgadetsky.orderservice.repository;

import com.lgadetsky.orderservice.exception.OrderIdNotFoundException;
import com.lgadetsky.orderservice.model.Order;
import com.lgadetsky.orderservice.model.OrderItem;
import com.lgadetsky.orderservice.repository.mapper.OrderItemMapper;
import com.lgadetsky.orderservice.repository.mapper.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@org.springframework.stereotype.Repository
public class OrderRepository implements Repository{

    private final OrderMapper orderMapper;
    private final OrderItemMapper orderItemMapper;
    @Autowired
    public OrderRepository(OrderMapper orderMapper, OrderItemMapper orderItemMapper) {
        this.orderMapper = orderMapper;
        this.orderItemMapper = orderItemMapper;
    }

    @Override
    @Transactional
    public Order create(Order order) {
        orderMapper.insert(order);

        List<OrderItem> orderItems = order.getOrderItems();
        if(orderItems != null && !orderItems.isEmpty()) {
            orderItems.forEach(orderItem -> orderItem.setOrderId(order.getId()));
            orderItemMapper.insertOrderItems(orderItems);
        }
        return order;
    }

    @Override
    public Order selectOrderById(int id) {
        return orderMapper.findById(id);
    }

    @Override
    @Transactional
    public Order update(Order order) {
        if(orderMapper.findById(order.getId()) != null){
            orderMapper.update(order);
            orderItemMapper.update(order.getOrderItems());
        } else {
            throw new OrderIdNotFoundException();
        }
        return order;
    }

    @Override
    @Transactional
    public void deleteOrderById(int id) {
        orderMapper.deleteById(id);
    }
}
