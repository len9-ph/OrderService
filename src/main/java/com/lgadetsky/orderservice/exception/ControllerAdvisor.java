package com.lgadetsky.orderservice.exception;

import java.net.ConnectException;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.lgadetsky.orderservice.controller.OrderController;

import lombok.extern.slf4j.Slf4j;

/**
 * Class that handles exceptions from {@link OrderController}
 * 
 * @author Leonid Gadetsky
 * @see ResponseEntityExceptionHandler
 * @see ControllerAdvice
 */
@ControllerAdvice
@Slf4j
public class ControllerAdvisor extends ResponseEntityExceptionHandler{
	private static final String TIMESTAMP = "timestamp";
	private static final String MESSAGE = "message";
	private static final String API = "api";
	private static final String PATH = "path";
	private static final String ERROR = "error";
	private static final String ORDER_NOT_FOUND = "Order not found";
	private static final String ORDER_NOT_VALID = "Order not valid";
	//private static final String PATIENT_NOT_VALID = "Patient not valid";
	
	@ExceptionHandler(OrderNotFoundException.class)
	public ResponseEntity<Object> handleOrderNotFoundException(
			OrderNotFoundException ex, WebRequest request) {
		
		Map<String, Object> body = new LinkedHashMap<>();
		body.put(TIMESTAMP, LocalDateTime.now());
		body.put(MESSAGE, ORDER_NOT_FOUND);
		log.error(ex.getMessage());
		return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(OrderNotValidException.class)
	public ResponseEntity<Object> handleOrderNotValidException(
			OrderNotValidException ex, WebRequest request) {
		Map<String, Object> body = new LinkedHashMap<>();
		body.put(TIMESTAMP, LocalDateTime.now());
		body.put(MESSAGE, ORDER_NOT_VALID);
		log.error(ex.getMessage());
		return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(RestTemplateException.class)
	public ResponseEntity<Object> handleRestTemplateException(
			RestTemplateException ex, WebRequest request) {
		Map<String, Object> body = new LinkedHashMap<>();
		body.put(TIMESTAMP, LocalDateTime.now());
		body.put(API, ex.getApi());
		body.put(PATH, ex.toString());
		body.put(ERROR, ex.getStatusCode().getReasonPhrase());
		body.put(MESSAGE, ex.getError());
		log.error(ex.getError());
		return new ResponseEntity<>(body, ex.getStatusCode());
	}
	
	@ExceptionHandler(ConnectException.class)
	public ResponseEntity<Object> handleConnectException(
			ConnectException ex, WebRequest request) {
		Map<String, Object> body = new LinkedHashMap<>();
		body.put(TIMESTAMP, LocalDateTime.now());
		body.put(MESSAGE, ex.getMessage());
		log.error(ex.getMessage());
		return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(NullPointerException.class)
	public ResponseEntity<Object> handleNullPointerException(
			NullPointerException ex, WebRequest request) {
		Map<String, Object> body = new LinkedHashMap<>();
		body.put(TIMESTAMP, LocalDateTime.now());
		body.put(MESSAGE, ex.getMessage());
		log.error(ex.getMessage());
		return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
	}
}
