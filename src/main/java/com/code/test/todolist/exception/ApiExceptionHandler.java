package com.code.test.todolist.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApiExceptionHandler {
	
	@ExceptionHandler(CustomApiException.class)
	public ResponseEntity<Object> handleApiException(CustomApiException ex) {
		return new ResponseEntity<Object>(new CustomException(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, ex, LocalDateTime.now()), HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
