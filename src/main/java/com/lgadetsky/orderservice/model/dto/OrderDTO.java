package com.lgadetsky.orderservice.model.dto;

import java.util.LinkedList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import com.lgadetsky.orderservice.model.Order;
import com.lgadetsky.orderservice.model.OrderItem;
import com.lgadetsky.orderservice.servlet.Command;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Leonid Gadetsky
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name = "order")
@XmlAccessorType(XmlAccessType.FIELD)
public class OrderDTO {
	
	@XmlElement(name = "id")
    private int id;
	
	@XmlElement(name = "orderStatusId")
    private int orderStatusId;
	
	@XmlElement(name = "customerName")
    private String customerName;
	
	@XmlElement(name = "customerPhone")
    private String customerPhone;
	
	@XmlElement(name = "customerComment")
    private String customerComment;
	
	@XmlElementWrapper(name = "items")
	@XmlElement(name = "item")
    private List<String> items;
	
	@XmlElementWrapper(name = "orderItems")
	@XmlElement(name = "item")
    private List<ItemDTO> orderItems; 
	
	
	
	/**
	 * Util method that converts dto OrderItems to List of {@link OrderItem}
	 * method depends on field type if {@link Command} equals create -> when dto contains
	 * items and orderItems equals null in other way items equals null and orderItems contains {@link ItemDTO}
	 * @return {@link List} of OrderItem elements 
	 */
	public List<OrderItem> toOrderItems(){
		List<OrderItem> it = new LinkedList<>();
		if(items.isEmpty()) 
			for (ItemDTO dto : orderItems)
				it.add(OrderItem.of(dto));	
		else 
			for (String s : items) {
				OrderItem i = OrderItem.of(s);
				i.setOrderId(id);
				it.add(i);
		}
		return it;
	}
	
	public static OrderDTO of(Order o) {
		return new OrderDTOBuilder()
				.id(o.getId())
				.orderStatusId(o.getOrderStatusId())
				.customerName(o.getCustomerName())
				.customerPhone(o.getCustomerPhone())
				.customerComment(o.getCustomerComment())
				.orderItems(o.toItemsDto())
				.build();
	}
}
