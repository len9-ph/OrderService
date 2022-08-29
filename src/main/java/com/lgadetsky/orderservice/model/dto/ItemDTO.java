package com.lgadetsky.orderservice.model.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.lgadetsky.orderservice.model.OrderItem;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "items")
public class ItemDTO {
	private int id;
	private int orderId;
	private String name;
	
	public static ItemDTO of(OrderItem oi) {
		return new ItemDTOBuilder()
				.id(oi.getId())
				.orderId(oi.getOrderId())
				.name(oi.getItemName())
				.build();
	}
}
