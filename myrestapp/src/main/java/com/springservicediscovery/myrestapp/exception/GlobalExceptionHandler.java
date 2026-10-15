package com.springservicediscovery.myrestapp.exception;

import java.time.LocalTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler(UserInputException.class)
	public ResponseEntity<String> handletheException(UserInputException ex) {
		ResponseEntity<String> response = new ResponseEntity<String>(LocalTime.now().toString()+" " + ex.getMessage(),
				HttpStatus.BAD_REQUEST);
		return response;
	}

}
