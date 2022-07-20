package com.TeamSeven.CConge.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class TA_CongesNotFoundException extends RuntimeException{

	public TA_CongesNotFoundException(String message) {
		super(message);
		
	}

	
}
