package com.lgadetsky.orderservice.model.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
//@XmlRootElement(name = "items")
public class ItemDTO {
	//@XmlTransient
	private int id;
	//@XmlTransient
	private int orderId;
	//@XmlValue
	private String name;
}
