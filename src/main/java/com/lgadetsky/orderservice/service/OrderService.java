package com.lgadetsky.orderservice.service;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.lgadetsky.orderservice.exception.OrderNotFoundException;
import com.lgadetsky.orderservice.model.Order;
import com.lgadetsky.orderservice.model.OrderItem;
import com.lgadetsky.orderservice.repository.mapper.OrderItemMapper;
import com.lgadetsky.orderservice.repository.mapper.OrderMapper;

/**
 * @author Leonid Gadetsky
 */
@org.springframework.stereotype.Service
public class OrderService implements Service<Order, Integer>{
	@Autowired
	private OrderMapper orderMapper;
	@Autowired
	private OrderItemMapper orderItemMapper;
	
	@Override
	@Transactional
	public Integer create(Order order) {
		int id = orderMapper.insert(order);
		
		List<OrderItem> items = order.getOrderItems();
		if(items != null && !items.isEmpty()) {
			items.forEach(item -> item.setOrderId(id));
			orderItemMapper.insertOrderItems(items);
		}
		return id;
	}

	@Override
	public Order findById(Integer id) {
		if (orderMapper.findById(id) != null)
			return orderMapper.findById(id);
		else throw new OrderNotFoundException();
	}
	
	@Override
	@Transactional
	public Integer update(Order order) {
		if(orderMapper.findById(order.getId()) != null) {
			List<OrderItem> oldOrderItems = orderMapper.findById(order.getId()).getOrderItems();
			List<OrderItem> newOrderItems = order.getOrderItems();
			List<OrderItem> toUpdate = new LinkedList<>();
			List<OrderItem> toInsert = new LinkedList<>();
			List<OrderItem> toDelete = new LinkedList<>();
			
			int id = orderMapper.update(order);
			
			for (OrderItem item : newOrderItems) {
				if (oldOrderItems.contains(item))
					toUpdate.add(item);
				else if (item.getId() == 0) {
					item.setOrderId(order.getId());
					toInsert.add(item);
				}
			}
			oldOrderItems.removeAll(toUpdate);			
			toDelete = oldOrderItems;
	
			if (!toInsert.isEmpty())
				orderItemMapper.insertOrderItems(toInsert);
			if (!toDelete.isEmpty())
				toDelete.forEach(item -> orderItemMapper.deleteById(item.getId()));
			if(!toUpdate.isEmpty())
				toUpdate.forEach(item -> orderItemMapper.update(item));
			
			return id;
		}
		else throw new OrderNotFoundException();
	}

	@Override
	@Transactional
	public Integer deleteById(Integer id) {
		orderItemMapper.deleteByOrderId(id);
		return orderMapper.deleteById(id);
	}
}
