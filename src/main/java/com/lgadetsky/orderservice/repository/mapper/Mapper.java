/**
 * 
 */
package com.lgadetsky.orderservice.repository.mapper;

import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.lgadetsky.orderservice.model.Order;
import com.lgadetsky.orderservice.model.OrderItem;
import com.lgadetsky.orderservice.model.dto.OrderDTO;

/**
 * @author Leonid Gadetsky
 *
 */
@Component
public class Mapper {
	public OrderDTO toDTO(Order order, List<OrderItem> items) {
		return new OrderDTO();
	}
	
	public Order toOrder(OrderDTO dto) {
		return new Order();
	}
	
	public List<OrderItem> toItems(OrderDTO dto){
		return new LinkedList<OrderItem>();
	}
}
