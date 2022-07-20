package com.lgadetsky.orderservice.exception;

public class OrderDataBaseEmptyException extends RuntimeException{
    public OrderDataBaseEmptyException() {
        super("Data Base empty");
    }
}
