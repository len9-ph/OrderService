package com.lgadetsky.orderservice.exception;

public class PatientNotValidException extends RuntimeException{
	private static final long serialVersionUID = 322L;

	public PatientNotValidException() {
		super("Patient not valid");
	}

}
