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

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
	
	public List<OrderItem> toOrderItems(){
		if(items.isEmpty()) {
			List<OrderItem> it = new LinkedList<>();
			for (ItemDTO dto : orderItems)
				it.add(OrderItem.of(dto));
			return it;
		}else {
			List<OrderItem> it = new LinkedList<>();
			for (String s : items) {
				OrderItem i = OrderItem.of(s);
				i.setOrderId(id);
				it.add(i);
			}
			return it;
		}
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
