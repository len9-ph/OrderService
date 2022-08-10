package com.lgadetsky.orderservice.service;

import com.lgadetsky.orderservice.exception.OrderIdNotFoundException;
import com.lgadetsky.orderservice.model.Order;
import com.lgadetsky.orderservice.model.OrderItem;
import com.lgadetsky.orderservice.repository.mapper.OrderItemMapper;
import com.lgadetsky.orderservice.repository.mapper.OrderMapper;

import java.util.LinkedList;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

/**
 * @author Leonid Gadetsky
 */
@org.springframework.stereotype.Service
public class OrderService implements Service<Order, Integer>{
	
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
	public Order findById(Integer id) {
		return orderMapper.findById(id);
	}
	
	@Override
	@Transactional
	public Order update(Order order) {

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
		else throw new OrderIdNotFoundException();
	}

	@Override
	@Transactional
	public void deleteById(Integer id) {
		orderItemMapper.deleteByOrderId(id);
		orderMapper.deleteById(id);
	}
}
