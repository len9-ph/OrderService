package com.lgadetsky.orderservice.exception;

/**
 * @author Leonid Gadetsky
 * Исключение для ситуации, когда в базе данных отсутсвует пользователь с заданным идентификатором
 */
public class OrderNotFoundException extends RuntimeException{
	private static final long serialVersionUID = 1L;

	public OrderNotFoundException() {
        super("Order not found");
    }
}
