package com.springboot.weedingband.errorhandle;

import org.springframework.http.HttpStatus;

/**
 * Forbiden exception
 * @author PC
 *
 */
public class ForbidenException extends RuntimeException {
	
	private HttpStatus httpCode;

	public ForbidenException(String message, Throwable cause) {
		super(message, cause);
		
	}

	public ForbidenException(String message) {
		super(message);
		
	}

	public ForbidenException(Throwable cause) {
		super(cause);
		
	}

}
