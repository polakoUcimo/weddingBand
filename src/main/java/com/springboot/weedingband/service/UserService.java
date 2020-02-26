package com.springboot.weedingband.service;

import java.util.List;

import com.springboot.weedingband.entity.Responce;
import com.springboot.weedingband.entity.User;

/**
 * User service interface
 * @author PC
 *
 */
public interface UserService {
	
	/**
	 * List of all users.
	 * @return
	 */
	public List<User> findAll();
	
	/**
	 * Find user by id.
	 * @param theId id of the user.
	 * @return
	 */
	public User findById(int theId);
	
	/**
	 * Find user by username
	 * @param theUsername users username
	 * @return the user with specific username.
	 */
	public User findByUsername(String theUsername);
	
	/**
	 * Find user by email
	 * @param email users email
	 * @return the user with specific email.
	 */
	public User findByEmail(String email);
	/**
	 * Save user.
	 * @param theUser user to be saved
	 */
	public Responce save(User theUser);
	
	/**
	 * Update user
	 * @param theUser user to be updated.
	 */
	public void update(User theUser);
	
	/**
	 * Delete user by id
	 * @param theId id of the user
	 */
	public void deleteById(int theId);
}
