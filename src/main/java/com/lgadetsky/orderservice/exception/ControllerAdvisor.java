package com.lgadetsky.orderservice.exception;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ControllerAdvisor extends ResponseEntityExceptionHandler{
	private static final String TIMESTAMP = "timestamp";
	private static final String MESSAGE = "timestamp";
	private static final String ORDER_NOT_FOUND = "Order not found";
	
	@ExceptionHandler(OrderNotFoundException.class)
	public ResponseEntity<Object> handleOrderNotFoundException(
			OrderNotFoundException ex, WebRequest request){
		
		Map<String, Object> body = new LinkedHashMap<>();
		body.put(TIMESTAMP, LocalDateTime.now());
		body.put(MESSAGE, ORDER_NOT_FOUND);
		return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
	}

}
