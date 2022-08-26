package com.lgadetsky.orderservice.service;

import com.lgadetsky.orderservice.exception.OrderNotFoundException;
import com.lgadetsky.orderservice.model.Order;
import com.lgadetsky.orderservice.model.OrderItem;
import com.lgadetsky.orderservice.model.dto.OrderDTO;
import com.lgadetsky.orderservice.repository.mapper.Mapper;
import com.lgadetsky.orderservice.repository.mapper.OrderItemMapper;
import com.lgadetsky.orderservice.repository.mapper.OrderMapper;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Leonid Gadetsky
 */
@org.springframework.stereotype.Service
public class OrderService implements Service<OrderDTO, Integer>{
	@Autowired
	private OrderMapper orderMapper;
	@Autowired
	private OrderItemMapper orderItemMapper;
	@Autowired
	private Mapper mapper;
	
	@Override
	@Transactional
	public OrderDTO create(OrderDTO order) {
		orderMapper.insert(mapper.toOrder(order));
		
		List<OrderItem> items = mapper.toOrder(order).getOrderItems();
		if(items != null && !items.isEmpty()) {
			items.forEach(item -> item.setOrderId(order.getId()));
			orderItemMapper.insertOrderItems(items);
		}
		return order;
	}

	@Override
	public OrderDTO findById(Integer id) {
		if (orderMapper.findById(id) != null)
			return mapper.toDTO(orderMapper.findById(id));
		else throw new OrderNotFoundException();
	}
	
	@Override
	@Transactional
	public OrderDTO update(OrderDTO orderDto) {
		Order order = mapper.toOrder(orderDto);
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
			
			return orderDto;
		}
		else throw new OrderNotFoundException();
	}

	@Override
	@Transactional
	public void deleteById(Integer id) {
		orderItemMapper.deleteByOrderId(id);
		orderMapper.deleteById(id);
	}
}
