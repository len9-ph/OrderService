package com.lgadetsky.orderservice.exception;

/**
 * @author Leonid Gadetsky
 * Исключение для ситуации, когда в базе уже существует пользователь с переданным идентификатором
 */
public class OrderIdAlreadyExistException extends RuntimeException{
    public OrderIdAlreadyExistException() {
        super("Order Id already exist");
    }
}
