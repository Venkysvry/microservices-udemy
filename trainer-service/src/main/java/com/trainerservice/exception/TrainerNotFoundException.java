package com.trainerservice.exception;

public class TrainerNotFoundException extends RuntimeException{
private	String message;

public TrainerNotFoundException(String message) {
	super(message);
	this.message = message;
}
	

}
