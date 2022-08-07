package com.lgadetsky.orderservice.exception;

/**
 * Сериализация
 * @author Leonid Gadetsky
 * Исключение для ситуации, когда в базе данных отсутсвует пользователь с заданным идентификатором
 */
public class OrderIdNotFoundException extends RuntimeException{
	private static final long serialVersionUID = 1L;

	public OrderIdNotFoundException() {
        super("Order Id not found");
    }
}
