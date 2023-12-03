package com.trainerservice.exception;

public class HrsModifiedException extends RuntimeException {
	private String message;

	public HrsModifiedException(String message) {
		super(message);
		this.message = message;
	}
	

}
