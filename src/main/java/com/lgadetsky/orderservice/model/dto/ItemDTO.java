package com.lgadetsky.orderservice.model.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlValue;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
public class ItemDTO {
	@XmlTransient
	private int id;
	@XmlTransient
	private int orderId;
	@XmlValue
	private String name;
}
