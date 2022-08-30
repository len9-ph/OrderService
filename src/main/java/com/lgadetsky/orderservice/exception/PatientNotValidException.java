package com.lgadetsky.orderservice.exception;

/**
 * Exception for the situation when user provide invalid patient in request
 * @author Leonid Gadetsky
 */
public class PatientNotValidException extends RuntimeException{
	private static final long serialVersionUID = 322L;

	public PatientNotValidException() {
		super("Patient not valid");
	}

}
