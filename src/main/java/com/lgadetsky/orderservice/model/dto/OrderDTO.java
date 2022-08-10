package com.lgadetsky.orderservice.model.dto;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlValue;

import com.lgadetsky.orderservice.model.OrderItem;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@XmlRootElement(name="order")
@XmlAccessorType(XmlAccessType.FIELD)
public class OrderDTO {
	
	@XmlElement
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
	@XmlValue()
    private List<OrderItem> orderItems;
}
