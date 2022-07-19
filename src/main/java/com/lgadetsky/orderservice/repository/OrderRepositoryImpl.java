package com.lgadetsky.orderservice.repository;

import com.lgadetsky.orderservice.model.Order;
import com.lgadetsky.orderservice.model.OrderItem;
import com.lgadetsky.orderservice.repository.mapper.OrderItemMapper;
import com.lgadetsky.orderservice.repository.mapper.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

/**
 * Implements all method for with DB
 */
@Repository
public class OrderRepositoryImpl implements OrderRepository{

    private final OrderMapper orderMapper;
    private final OrderItemMapper orderItemMapper;

    @Autowired
    public OrderRepositoryImpl(OrderMapper orderMapper, OrderItemMapper orderItemMapper) {
        this.orderMapper = orderMapper;
        this.orderItemMapper = orderItemMapper;
    }

    @Override
    @Transactional
    public Order insertOrder(Order order) {
        //Вставка заказа
        orderMapper.insertOrder(order);
        //Вставка элементов заказа
        List<OrderItem> orderItems = order.getOrderItems();
        if(!orderItems.isEmpty()) {
            //Обновление идентификатора элементов заказа
            orderItems.forEach(orderItem -> orderItem.setOrderId((int) order.getId()));
            orderItemMapper.insertOrderItems(orderItems);
        }
        return order;
    }

    @Override
    public Order selectOrder(long id) {
        return orderMapper.findOrderById(id);
    }

    @Override
    public List<Order> selectAllOrders() {
        return orderMapper.findAllOrders();
    }

    /*@Override
    public boolean orderExistById(long id) {
        return false;
    }*/

    // TODO Дописать Update
    @Override
    @Transactional
    public Order updateOrder(Order order) {
        Order oldOrder = this.selectOrder(order.getId());
        if(oldOrder == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        orderMapper.update(order);

        List<OrderItem> newOrderItems = order.getOrderItems();
        List<OrderItem> oldOrderItems = oldOrder.getOrderItems();
        return order;
    }

    @Override
    @Transactional
    public void deleteOrder(long id) {
        orderMapper.delete(id);
    }
}
