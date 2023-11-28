package com.example.Exception;

import java.time.LocalDate;
import java.util.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.example.entity.ErrorDetails;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ErrorDetails> handleResourceNotFoundException(ResourceNotFoundException e, WebRequest w) {
		ErrorDetails E = new ErrorDetails(LocalDate.now(), e.getMessage(), w.getDescription(false), "USER_NOT_FOUND");
		return new ResponseEntity<ErrorDetails>(E, HttpStatus.NOT_FOUND);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		Map<String, String> map = new HashMap<>();
		List<ObjectError> errorlist = ex.getBindingResult().getAllErrors();
		errorlist.forEach((error) -> {
			String fieldname = ((FieldError) error).getField();
			String message = error.getDefaultMessage();
			map.put(fieldname, message);
		}

		);

		return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
	}

}
