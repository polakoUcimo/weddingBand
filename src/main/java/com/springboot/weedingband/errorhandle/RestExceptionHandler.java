package com.springboot.weedingband.errorhandle;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * User excepton handler.
 * @author PC
 *
 */
@ControllerAdvice
public class RestExceptionHandler {
	
	/**
	 * Handle wrong id 
	 * @param exc exception
	 * @return exception request code and message
	 */
	@ExceptionHandler
	public ResponseEntity<ErrorResponse> handlException(UserNotFoundException exc){
		
		ErrorResponse errorResponce = new ErrorResponse();
		
		errorResponce.setStatus(HttpStatus.NOT_FOUND.value());
		errorResponce.setMessage(exc.getMessage());
		errorResponce.setTimeStamp(System.currentTimeMillis());
		
		return new ResponseEntity<>(errorResponce, HttpStatus.NOT_FOUND);
	}
	
	/**
	 * Handle bad id type
	 * @param exc exception
	 * @return exception request code and message
	 */
	@ExceptionHandler
	public ResponseEntity<ErrorResponse> handlException(Exception exc){
		
		ErrorResponse errorResponce = new ErrorResponse();
		
		errorResponce.setStatus(HttpStatus.BAD_REQUEST.value());
		errorResponce.setMessage(exc.getMessage());
		errorResponce.setTimeStamp(System.currentTimeMillis());
		
		return new ResponseEntity<>(errorResponce, HttpStatus.BAD_REQUEST);
	}
	
	/**
	 * Handle forbidden entry.
	 * @param exc exception
	 * @return exception request code and message
	 */
	@ExceptionHandler
	public ResponseEntity<ErrorResponse> handleForbiddenException(ForbidenException exc){
		
		ErrorResponse errorResponce = new ErrorResponse();
		
		errorResponce.setStatus(HttpStatus.FORBIDDEN.value());
		errorResponce.setMessage(exc.getMessage());
		errorResponce.setTimeStamp(System.currentTimeMillis());
		
		return new ResponseEntity<>(errorResponce, HttpStatus.FORBIDDEN);
	}

}
