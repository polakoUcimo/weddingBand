package com.springboot.weedingband.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * User entity.
 * @author PC
 *
 */
@Entity
@Table(name="user")
public class User {
	
	/**
	 * Id of the user, auto generated.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id",unique = true, nullable = false)
	private int id;
	
	/**
	 * Users username.
	 */
	@Column(name = "username")
	private String username;
	
	/**
	 * Users password.
	 */
	@Column(name = "password")
	private String password;
	
	/**
	 * Flag is account enabled.
	 */
	@Column(name = "is_enabled")
	private boolean isEnabled;
	
	 @OneToOne(mappedBy = "user",
		        cascade = CascadeType.ALL, orphanRemoval = true)
	 private ConfirmationToken confirmationToken;
	
	/**
	 * User default empty constructor
	 */
	public User() {
		
	}
	
	/**
	 * Constructor for the user.
	 * @param theUsername username.
	 * @param thePassword password.
	 */
	public User(String theUsername, String thePassword, boolean theIsEnabled) {
		username = theUsername;
		password = thePassword;
		isEnabled = theIsEnabled;
	}

	/**
	 * Getters and Setters
	 * @return
	 */
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isEnabled() {
		return isEnabled;
	}

	public void setEnabled(boolean isEnabled) {
		this.isEnabled = isEnabled;
	}

	@Override
	public String toString() {
		return "User [username=" + username + ", password=" + password + "]";
	}
	
	

}
