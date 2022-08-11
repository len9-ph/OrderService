/**
 * 
 */
package com.lgadetsky.orderservice.repository.mapper;

import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.lgadetsky.orderservice.model.Order;
import com.lgadetsky.orderservice.model.OrderItem;
import com.lgadetsky.orderservice.model.dto.ItemDTO;
import com.lgadetsky.orderservice.model.dto.OrderDTO;

/**
 * @author Leonid Gadetsky
 *
 */
@Component
public class Mapper {
	public OrderDTO toDTO(Order order) {
		OrderDTO dto = new OrderDTO();
		
		dto.setId(order.getId());
		dto.setOrderStatusId(order.getOrderStatusId());
		dto.setCustomerName(order.getCustomerName());
		dto.setCustomerPhone(dto.getCustomerPhone());
		dto.setCustomerComment(order.getCustomerComment());
		dto.setOrderItems(itemToDto(order.getOrderItems()));
		
		return dto;
	}
	
	public Order toOrder(OrderDTO dto) {
		Order order = new Order();
		
		order.setId(dto.getId());
		order.setOrderStatusId(dto.getOrderStatusId());
		order.setCustomerName(dto.getCustomerName());
		order.setCustomerPhone(dto.getCustomerPhone());
		order.setCustomerComment(dto.getCustomerComment());
		order.setOrderItems(dtoToItem(dto.getOrderItems()));
		
		return order;
	}
	
	private List<ItemDTO> itemToDto(List<OrderItem> items) {
		List<ItemDTO> itemsDTO = new LinkedList<>();
		for (OrderItem item : items) 
			itemsDTO.add(new ItemDTO(item.getId(), item.getOrderId(), item.getItemName()));
		
		return itemsDTO;
	}
	
	private List<OrderItem> dtoToItem(List<ItemDTO> dtos){
		List<OrderItem> items = new LinkedList<>();
		
		for (ItemDTO dto : dtos)
			items.add(new OrderItem(dto.getId(), dto.getOrderId(), dto.getName()));
		
		return items;
	}
	
}
