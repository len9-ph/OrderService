package com.lgadetsky.orderservice.servlet;

import javax.xml.bind.annotation.XmlEnum;

import com.lgadetsky.orderservice.model.dto.MessageDTO;

/**
 * enum keeps commands for {@link MessageDTO}
 * 
 * @author Leonid Gadetsky
 */
@XmlEnum
public enum Command {
	CREATE,
	UPDATE,
	DELETE
}
