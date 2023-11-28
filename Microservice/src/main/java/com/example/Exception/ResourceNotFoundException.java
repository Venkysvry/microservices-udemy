package com.example.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException  extends RuntimeException{
	
	private String fieldName;
	private String resourceName;
	private int fieldValue;
	public ResourceNotFoundException(String fieldName, String resourceName, int fieldValue) {
		super(String.format(" %s not found with %s :%s ",fieldName,resourceName,fieldValue));
		this.fieldName = fieldName;
		this.resourceName = resourceName;
		this.fieldValue = fieldValue;
	}
	
	

}
