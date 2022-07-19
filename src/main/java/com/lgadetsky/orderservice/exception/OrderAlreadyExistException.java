package com.lgadetsky.orderservice.exception;

public class OrderAlreadyExistException extends RuntimeException{
    public OrderAlreadyExistException() {
        super("Order already exist");
    }
}
