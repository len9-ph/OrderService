package com.lgadetsky.orderservice.model.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
public class BodyDTO {
	@XmlElement(name = "order")
	private OrderDTO order;
	
	public BodyDTO(OrderDTO order) {
		this.order = order;
	}
}
