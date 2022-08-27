package com.lgadetsky.orderservice.model.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name = "order")
@XmlAccessorType(XmlAccessType.FIELD)
public class Body {
	@XmlElement(name = "order")
	OrderDTO order;
	@XmlElement(name = "patient")
	PatientDto patient;
}
