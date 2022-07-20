package com.lgadetsky.orderservice.exception;

public class OrderIdAlreadyExistException extends RuntimeException{
    public OrderIdAlreadyExistException() {
        super("Order Id already exist");
    }
}
