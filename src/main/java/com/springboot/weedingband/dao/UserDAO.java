package com.springboot.weedingband.dao;

import java.util.List;

import com.springboot.weedingband.entity.User;
import com.springboot.weedingband.model.ResponceBody;

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
	public User findById(long theId);
	
	/**
	 * Find user by username
	 * @param theUsername username
	 * @return user with specific username.
	 */
	public User findByUsername(String theUsername);
	
	/**
	 * Find user by email
	 * @param theEmail users email
	 * @return user with specific email.
	 */
	public User findByEmail(String theEmail);
	
	/**
	 * Save user.
	 * @param theUser user
	 * @return saves user to database.
	 */
	public ResponceBody save(User theUser);
	
	/**
	 * Update the user.
	 * @param theUser user.
	 */
	public void update(User thUser);
	
	/**
	 * Delete user by id.
	 * @param theId user id.
	 */
	public void deleteById(int theId);
}
