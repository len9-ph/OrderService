package com.lgadetsky.orderservice.exception;

/**
 * @author Leonid Gadetsky
 * Exception for the situation when there is no user with the given id in the database
 */
public class OrderNotFoundException extends RuntimeException{
	private static final long serialVersionUID = 1L;

	public OrderNotFoundException() {
        super("Order not found");
    }
}
