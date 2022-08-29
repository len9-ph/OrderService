package com.lgadetsky.orderservice.servlet;

import javax.xml.bind.annotation.XmlEnum;

@XmlEnum
public enum Command {
	CREATE,
	UPDATE,
	DELETE
}
