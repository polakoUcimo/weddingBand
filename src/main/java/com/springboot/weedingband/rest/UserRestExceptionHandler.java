package com.springboot.weedingband.rest;

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
public class UserRestExceptionHandler {
	
	/**
	 * Handle wrong id 
	 * @param exc exception
	 * @return exception request code and message
	 */
	@ExceptionHandler
	public ResponseEntity<UserErrorResponce> handlException(UserNotFoundException exc){
		
		UserErrorResponce errorResponce = new UserErrorResponce();
		
		errorResponce.setStatus(HttpStatus.NOT_FOUND.value());
		errorResponce.setMessage(exc.getMessage());
		errorResponce.setTimeStamp(System.currentTimeMillis());
		
		return new ResponseEntity<>(errorResponce, HttpStatus.NOT_FOUND);
	}
	
	/**
	 * Handle wrong id type.
	 * @param exc exception
	 * @return exception request code and message
	 */
	@ExceptionHandler
	public ResponseEntity<UserErrorResponce> handlException(Exception exc){
		
		UserErrorResponce errorResponce = new UserErrorResponce();
		
		errorResponce.setStatus(HttpStatus.BAD_REQUEST.value());
		errorResponce.setMessage(exc.getMessage());
		errorResponce.setTimeStamp(System.currentTimeMillis());
		
		return new ResponseEntity<>(errorResponce, HttpStatus.BAD_REQUEST);
	}


		
}
