package com.TeamSeven.CConge.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class TA_CongesCodeException extends RuntimeException{

	public TA_CongesCodeException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

}
