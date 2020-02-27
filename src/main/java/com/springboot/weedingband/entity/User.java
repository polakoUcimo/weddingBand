package com.springboot.weedingband.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

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
	private long id;
	
	/**
	 * Users username.
	 */
	@Column(name = "username")
	private String username;
	
	/**
	 * Users email.
	 */
	@Column(name="email")
	private String email;
	
	/**
	 * Users password.
	 */
	@Column(name = "password")
	private String password;
	
	/**
	 * Role of the user. Not to be added to database user.
	 */
	@Transient
	private String role;
	
	/**
	 * Id of the user wanting to change something.
	 */
	@Transient
	private long idUserRole;
	
	/**
	 * Flag is account enabled.
	 */
	@Column(name = "is_enabled")
	private boolean isEnabled;
	
	@OneToOne(mappedBy = "user",
		        cascade = CascadeType.ALL, orphanRemoval = true)
	private ConfirmationToken confirmationToken;
	 
 	/**
     * Foreign key linking to database user.
     */
	@OneToOne(mappedBy = "user",
	        cascade = CascadeType.ALL, orphanRemoval = true)    
    private Roles roles;
	
	/**
	 * User default empty constructor
	 */
	public User() {
		
	}

	/**
	 * Constructor with parameters
	 * @param username username
	 * @param email email
	 * @param password password
	 * @param isEnabled is users email approved
	 */
	public User(String username, String email, String password, boolean isEnabled) {
		super();
		this.username = username;
		this.email = email;
		this.password = password;
		this.isEnabled = isEnabled;
	}

	/**
	 * Getters and Setters
	 */
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public long getIdUserRole() {
		return idUserRole;
	}

	public void setIdUserRole(long idUserRole) {
		this.idUserRole = idUserRole;
	}
	
	

}
