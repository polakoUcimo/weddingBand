package com.springboot.weedingband.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.weedingband.entity.Responce;
import com.springboot.weedingband.entity.User;
import com.springboot.weedingband.service.UserService;

/**
 * User rest controller for the user. Mapping user urls.
 * @author PC
 *
 */
@RestController
public class UserRestController {
	
	/**
	 * User service
	 */
	private UserService userService;

	@Autowired
	public UserRestController(UserService theUserService) {
		userService = theUserService;
	}
	
	/**
	 * Get all users from database.
	 * @return list of all users
	 */
	@GetMapping("/users")
	public List<User> allUsers() {
		
		return userService.findAll();
	}
	
	/**
	 * Find user by id.
	 * @param userId user id.
	 * @return user with specific id.
	 */
	@GetMapping("/users/{userId}")
	public User findUserById(@PathVariable int userId) {
		
		User user = userService.findById(userId);
		
		if(user == null) {
			throw new UserNotFoundException("User id not found - " + userId);
		}
		
		return user;
	}
	
	
	/**
	 * Add user to the database.
	 * @param theUser the user to be added.
	 * @return added user.
	 */
	@PostMapping("/users")
	public Responce addUser(@RequestBody User theUser) {
		
		theUser.setId(0);
		
		return userService.save(theUser);
	}
	
	/**
	 * Update user.
	 * @param theUser the user to be updated.
	 * @return updated user.
	 */
	@PutMapping("/users")
	public User updateUser(@RequestBody User theUser) {
		
		User user = userService.findById(theUser.getId());
		
		if(user == null) {
			throw new UserNotFoundException("User id not found - " + theUser.getId());
		}
		
		userService.update(theUser);
		
		return theUser;
	}
	
	/**
	 * Delete user with specific id.
	 * @param userId user id
	 * @return message if user has been deleted.
	 */
	@DeleteMapping("/users/{userId}")
	public String deleteuser(@PathVariable int userId) {
		
		User user = userService.findById(userId);
		
		if(user == null) {
			throw new UserNotFoundException("User id not found - " + userId);
		}
		
		userService.deleteById(userId);
		
		return "Deleted employee id - " + userId;
	}	
	
}
