package com.lgadetsky.orderservice.exception;

public class OrderIdNotFoundException extends RuntimeException {

    public OrderIdNotFoundException() {
        super("Order id not found");
    }
}
