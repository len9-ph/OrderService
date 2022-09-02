package com.lgadetsky.orderservice.service;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.lgadetsky.orderservice.exception.OrderNotFoundException;
import com.lgadetsky.orderservice.exception.OrderNotValidException;
import com.lgadetsky.orderservice.model.Order;
import com.lgadetsky.orderservice.model.OrderItem;
import com.lgadetsky.orderservice.repository.mapper.OrderItemMapper;
import com.lgadetsky.orderservice.repository.mapper.OrderMapper;

/**
 * Service class that implements {@link Service} interface
 * Work with {@link OrderMapper} and {@link OrderItemMapper} 
 * 
 * @author Leonid Gadetsky
 * @see Service
 * @see OrderMapper
 * @see OrderItemMapper
 */
@org.springframework.stereotype.Service
public class OrderService implements Service<Order, Integer>{
	@Autowired
	private OrderMapper orderMapper;
	@Autowired
	private OrderItemMapper orderItemMapper;
	
	@Override
	@Transactional
	public Order create(Order order) {
		if(!validate(order))
			throw new OrderNotValidException();
		
		orderMapper.insert(order);
		int id = order.getId();
		List<OrderItem> items = order.getOrderItems();
		if(items != null && !items.isEmpty()) {
			items.forEach(item -> item.setOrderId(id));
			orderItemMapper.insertOrderItems(items);
		}
		return order;
	}

	@Override
	public Order findById(Integer id) {
		if (orderMapper.findById(id) == null)
			throw new OrderNotFoundException();
		
		Order order = orderMapper.findById(id);
		order.setOrderItems(orderItemMapper.findItemsByOrderId(id));
		return order;
	}
	
	@Override
	@Transactional
	public Order update(Order order) {
		if(!validate(order))
			throw new OrderNotValidException();
		
		if(orderMapper.findById(order.getId()) != null) {
			List<OrderItem> oldOrderItems = orderMapper.findById(order.getId()).getOrderItems();
			List<OrderItem> newOrderItems = order.getOrderItems();
			List<OrderItem> toUpdate = new LinkedList<>();
			List<OrderItem> toInsert = new LinkedList<>();
			List<OrderItem> toDelete = new LinkedList<>();
			orderMapper.update(order); 
			
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
			
			return order;
		}
		else throw new OrderNotFoundException();
	}

	@Override
	@Transactional
	public void deleteById(Integer id) {
		orderItemMapper.deleteByOrderId(id);
		orderMapper.deleteById(id);
	}
	
	private boolean validate(Order order) {
		if (order == null | (order.getCustomerName().isBlank()))
			return false;
		else 
			return true;
	}
}
