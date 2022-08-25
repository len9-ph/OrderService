package com.lgadetsky.orderservice.model.dto;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
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
	@XmlElement(name = "itemName")
    private List<String> items;
	
	@XmlElementWrapper(name = "orderItems")
	@XmlElement(name = "item")
    private List<ItemDTO> orderItems;   
}
