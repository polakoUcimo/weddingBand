package com.springboot.weedingband.rest;

/**
 * User error responce
 * @author PC
 *
 */
public class UserErrorResponce {
	
	/**
	 * Status error.
	 */
	private int status;
	
	/**
	 * Message
	 */
	private String message;
	
	/**
	 * Time stamp
	 */
	private long timeStamp;
	
	/**
	 * Default constructor
	 */
	public UserErrorResponce() {
		
	}

	/**
	 * Constructor for the user error responce.
	 * @param status status
	 * @param message message
	 * @param timeStamp timestamp.
	 */
	public UserErrorResponce(int status, String message, long timeStamp) {
		this.status = status;
		this.message = message;
		this.timeStamp = timeStamp;
	}
	
	/**
	 * Getters and Setters
	 * @return
	 */

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public long getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(long timeStamp) {
		this.timeStamp = timeStamp;
	}

	@Override
	public String toString() {
		return "UserErrorResponce [status=" + status + ", message=" + message + ", timeStamp=" + timeStamp + "]";
	}
	
	
	
	
}
