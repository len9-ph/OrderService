package com.lgadetsky.orderservice.exception;

public class OrderNotValidException extends RuntimeException {
	private static final long serialVersionUID = 4907187086583591984L;

	public OrderNotValidException() {
		super("Patient not valid");
	}
}
