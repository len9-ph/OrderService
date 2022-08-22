package com.lgadetsky.orderservice.exception;

import org.springframework.http.HttpStatus;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class ApiError {
	private HttpStatus status;
	private String message;
	
}
