package com.lgadetsky.orderservice.service;

import com.lgadetsky.orderservice.model.Order;
import com.lgadetsky.orderservice.model.OrderItem;
import com.lgadetsky.orderservice.repository.mapper.OrderItemMapper;
import com.lgadetsky.orderservice.repository.mapper.OrderMapper;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

/**
 * @author Leonid Gadetsky
 */
@org.springframework.stereotype.Service
public class OrderService implements Service{
	
	private final OrderMapper orderMapper;
	private final OrderItemMapper orderItemMapper;
	
	public OrderService(OrderMapper orderMapper, OrderItemMapper orderItemMapper) {
		this.orderMapper = orderMapper;
		this.orderItemMapper = orderItemMapper;
	}

	@Override
	@Transactional
	public Order create(Order order) {
		orderMapper.insert(order);
		
		List<OrderItem> items = order.getOrderItems();
		if(items != null && !items.isEmpty()) {
			items.forEach(item -> item.setOrderId(order.getId()));
			orderItemMapper.insertOrderItems(items);
		}
		return order;
	}

	@Override
	public Order findById(int id) {
		return orderMapper.findById(id);
	}

	@Override
	public List<Order> findAll() {
		return orderMapper.findAll();
	}
	
	
	@Override
	@Transactional
	public Order update(Order order) {
		orderMapper.update(order);
		
		List<OrderItem> items = order.getOrderItems();
		items.forEach(item -> item.setOrderId(order.getId()));
		orderItemMapper.deleteByOrderId(order.getId());
		orderItemMapper.insertOrderItems(items);
		return order;
	}

	@Override
	@Transactional
	public void deleteById(int id) {
		orderItemMapper.deleteByOrderId(id);
		orderMapper.deleteById(id);
	}
}
