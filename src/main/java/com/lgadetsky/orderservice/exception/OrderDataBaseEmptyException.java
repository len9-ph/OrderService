package com.lgadetsky.orderservice.exception;

/**
 * @author Leonid Gadetsky
 * Исключение для ситуации, когда в базе данных отсутсвуют записи
 */
public class OrderDataBaseEmptyException extends RuntimeException{
    
	public OrderDataBaseEmptyException() {
        super("Data Base empty");
    }
}
