package com.lgadetsky.orderservice.exception;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Exception for the situation when restTemplate send exceptions
 * @author Leonid Gadetsky
 */
@Data
@EqualsAndHashCode(callSuper=false)
@AllArgsConstructor
public class RestTemplateException extends RuntimeException {
	private static final long serialVersionUID = 100L;
	
	private DownstreamApi api;
	private HttpStatus statusCode;
	private String error;
}
