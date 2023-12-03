package com.trainerservice.exception;

import java.time.LocalDate;
import java.util.*;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
	@ExceptionHandler(TrainerNotFoundException.class)
	public ResponseEntity<ErrorDetails> handleTrainerNotFoundException(TrainerNotFoundException e, WebRequest w) {
		ErrorDetails errordetails = new ErrorDetails(LocalDate.now(), e.getMessage(), w.getDescription(false),
				"TRAINER-NOT-FOUND");
		return new ResponseEntity<ErrorDetails>(errordetails, HttpStatus.BAD_REQUEST);
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

	@ExceptionHandler(NullPointerException.class)
	public ResponseEntity<ErrorDetails> handleNullpointerException(NullPointerException e, WebRequest w) {
		ErrorDetails errorResponse = new ErrorDetails(LocalDate.now(), e.getMessage(), w.getContextPath(),
				"BAD_REQUEST");
		return new ResponseEntity<ErrorDetails>(errorResponse, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(HrsModifiedException.class)
	public ResponseEntity<ErrorDetails> handleHrsModifiedException(HrsModifiedException e, WebRequest w) {
		ErrorDetails errorResponse = new ErrorDetails(LocalDate.now(), e.getMessage(), w.getContextPath(),
				"BAD_REQUEST");
		return new ResponseEntity<ErrorDetails>(errorResponse, HttpStatus.BAD_REQUEST);
	}

}
