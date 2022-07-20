package com.lgadetsky.orderservice.exception;

/**
 * @author Leonid Gadetsky
 * Исключение для ситуации, когда в базе данных отсутсвует пользователь с заданным идентификатором
 */
public class OrderIdNotFoundException extends RuntimeException{
    public OrderIdNotFoundException() {
        super("Order Id not found");
    }
}
