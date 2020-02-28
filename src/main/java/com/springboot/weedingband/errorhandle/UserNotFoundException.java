package com.springboot.weedingband.errorhandle;

import org.springframework.http.HttpStatus;

/**
 * User not found exception
 * @author PC
 *
 */
public class UserNotFoundException extends RuntimeException {
	
	private HttpStatus httpCode;

	public UserNotFoundException(String message, Throwable cause) {
		super(message, cause);
		
	}

	public UserNotFoundException(String message) {
		super(message);
		
	}

	public UserNotFoundException(Throwable cause) {
		super(cause);
		
	}

}
