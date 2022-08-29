package com.lgadetsky.orderservice.model.dto;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@XmlRootElement(name = "order")
@XmlAccessorType(XmlAccessType.FIELD)
public class OrderView {
	
	@XmlElement(name = "id")
	private int id;
	@XmlElement(name = "orderStatusId")
	private int orderStatusId;
	@XmlElement(name = "customerComment")
	private String customerComment;
	@XmlElement(name = "patientId")
	private int patientId;
	@XmlElementWrapper(name = "orderItems")
	@XmlElement(name = "item")
	private List<ItemDTO> orderItems;
}