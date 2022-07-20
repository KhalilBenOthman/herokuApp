package com.TeamSeven.CConge.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestController
@ControllerAdvice
public class CustomResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler
	public final ResponseEntity<Object> handleCongeCodeException(TA_CongesCodeException ex, WebRequest request){
		TA_CongesCodeExceptionResponse exceptionResponse = new TA_CongesCodeExceptionResponse(ex.getMessage());
		return new ResponseEntity<Object>(exceptionResponse, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler
	public final ResponseEntity<Object> handleTACongeNotFoundException(TA_CongesNotFoundException ex, WebRequest request){
		TA_CongesNotFoundExceptionResponce exceptionResponse = new TA_CongesNotFoundExceptionResponce(ex.getMessage());
		return new ResponseEntity<Object>(exceptionResponse, HttpStatus.BAD_REQUEST);
	}
	
	
	@ExceptionHandler
	public final ResponseEntity<Object> handleUsernameExist(UsernameExistException ex, WebRequest request){
		UsernameExistResponse exceptionResponse = new UsernameExistResponse(ex.getMessage());
		return new ResponseEntity<Object>(exceptionResponse, HttpStatus.BAD_REQUEST);
	}
	
	
}
