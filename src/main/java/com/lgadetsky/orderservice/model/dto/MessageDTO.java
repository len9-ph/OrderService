package com.lgadetsky.orderservice.model.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.lgadetsky.orderservice.servlet.Command;
import com.lgadetsky.orderservice.servlet.CommandAdapter;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author lgadetsky
 * DTO класс необходимый для парсинга комманд полученных из тела запроса
 */
@Data
@NoArgsConstructor
@XmlRootElement(name = "message")
@XmlAccessorType(XmlAccessType.FIELD)
public class MessageDTO {
	@XmlElement(name = "command")
	@XmlJavaTypeAdapter(CommandAdapter.class)
	private Command command;
	
	@XmlElement(name = "body")
	private Body body;
}
