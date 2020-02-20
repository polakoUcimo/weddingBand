package com.springboot.weedingband.dao;

import java.util.List;

import com.springboot.weedingband.entity.Responce;
import com.springboot.weedingband.entity.User;

/**
 * User DAO
 * @author PC
 *
 */
public interface UserDAO {
	
	/**
	 * List of all users.
	 * @return list of all users
	 */
	public List<User> findAll();
	
	/**
	 * Find user by id.
	 * @param theId user id.
	 * @return specific user with id.
	 */
	public User findById(int theId);
	
	/**
	 * Save user.
	 * @param theUser user
	 * @return saves user to database.
	 */
	public Responce save(User theUser);
	
	/**
	 * Update the user.
	 * @param theUser user.
	 */
	public void update(User theUser);
	
	/**
	 * Delete user by id.
	 * @param theId user id.
	 */
	public void deleteById(int theId);
}
