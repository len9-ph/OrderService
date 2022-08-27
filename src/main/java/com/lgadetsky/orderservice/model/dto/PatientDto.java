package com.lgadetsky.orderservice.model.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@XmlRootElement(name = "patient")
@XmlAccessorType(XmlAccessType.FIELD)
public class PatientDto {
	@XmlElement(name = "id")
	private int id;
	@XmlElement(name = "fullName")
	private String name;
	@XmlElement(name = "gender")
	private int gender;
	@XmlElement(name = "birthday")
	private String birthday;
	@XmlElement(name = "phone")
	private String phone;
	@XmlElement(name = "email")
	private String email;
	@XmlElement(name = "address")
	private String address;
}
