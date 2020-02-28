package com.springboot.weedingband.errorhandle;

import org.springframework.http.HttpStatus;

/**
 * Forbiden exception
 * @author PC
 *
 */
public class UserExistsException extends RuntimeException {
	
	private HttpStatus httpCode;

	public UserExistsException(String message, Throwable cause) {
		super(message, cause);
		
	}

	public UserExistsException(String message) {
		super(message);
		
	}

	public UserExistsException(Throwable cause) {
		super(cause);
		
	}

}
