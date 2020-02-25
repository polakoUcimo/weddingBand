package com.springboot.weedingband.entity;

/**
 * Responce entity
 * @author PC
 *
 */
public class Responce {
	
	/**
	 * Username
	 */
	private String username;
	
	/**
	 * Message
	 */
	private String message;
	
	/**
	 * Boolean is saved.
	 */
	private boolean isSaved;
	
	private boolean isEnabled;
	
	/**
	 * Empty constractor.
	 */
	public Responce() {
		
	}
	
	
	/**
	 * Responce constructor
	 * @param username username
	 * @param message message
	 * @param isSaved is saved in database
	 */
	public Responce(String username, String message, boolean isSaved, boolean isEnabled) {
		this.username = username;
		this.message = message;
		this.isSaved = isSaved;
		this.isEnabled = isEnabled;
	}



	/**
	 * Getters and Setters
	 */

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public boolean isSaved() {
		return isSaved;
	}

	public void setSaved(boolean isSaved) {
		this.isSaved = isSaved;
	}


	public boolean isEnabled() {
		return isEnabled;
	}


	public void setEnabled(boolean isEnabled) {
		this.isEnabled = isEnabled;
	}


	@Override
	public String toString() {
		return "Responce [username=" + username + ", message=" + message + ", isSaved=" + isSaved + ", isEnabled="
				+ isEnabled + "]";
	}

	

}
