package com.springboot.weedingband.errorhandle;

/**
 * User error responce
 * @author PC
 *
 */
public class ErrorResponse {
	
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
	public ErrorResponse() {
		
	}

	/**
	 * Constructor for the user error responce.
	 * @param status status
	 * @param message message
	 * @param timeStamp timestamp.
	 */
	public ErrorResponse(int status, String message, long timeStamp) {
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
