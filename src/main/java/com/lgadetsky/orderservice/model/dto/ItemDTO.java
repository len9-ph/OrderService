package com.lgadetsky.orderservice.model.dto;

import javax.xml.bind.annotation.XmlValue;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ItemDTO {
	@XmlValue
	private int id;
	@XmlValue
	private int orderId;
	@XmlValue
	private String name;
}
