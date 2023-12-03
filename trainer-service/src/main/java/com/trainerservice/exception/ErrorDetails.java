package com.trainerservice.exception;

import java.time.LocalDate;

public class ErrorDetails {
	private LocalDate timeStamp;
	private String message;
	private String path;
	private String errorCode;

	public ErrorDetails() {

	}

	public ErrorDetails(LocalDate timeStamp, String message, String path, String errorCode) {
		super();
		this.timeStamp = timeStamp;
		this.message = message;
		this.path = path;
		this.errorCode = errorCode;
	}

	public LocalDate getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(LocalDate timeStamp) {
		this.timeStamp = timeStamp;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

}
