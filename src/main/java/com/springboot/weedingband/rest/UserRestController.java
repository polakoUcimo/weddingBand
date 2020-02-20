package com.springboot.weedingband.rest;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
import com.springboot.weedingband.util.Util;


/**
 * User rest controller for the user. Mapping user urls.
 * @author PC
 *
 */
@RestController
public class UserRestController {
	
	private static final Logger LOGGER=LoggerFactory.getLogger(UserRestController.class);
	
	/**
	 * User service
	 */
	private UserService userService;
	
	/**
	 * Bcrypt password encoder
	 */
	private BCryptPasswordEncoder encoder;

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
		
		String encoder = Util.encryptePassword(theUser.getPassword());
		
//		theUser.setPassword("{bcrypt}"+encoder);
		theUser.setPassword(encoder); 
		
		return userService.save(theUser);
	}
	
	/**
	 * Check if user has account and simulate login.
	 * @param theUser the user to be added.
	 * @return added user.
	 */
	@PostMapping("/users/login")
	public Responce loginUser(@RequestBody User theUser) {
		
		Responce responce;
		
		encoder = new BCryptPasswordEncoder();
		
		User user = userService.findByUsername(theUser.getUsername());
		
		if(encoder.matches(theUser.getPassword(), user.getPassword())) {
			responce = new Responce(theUser.getUsername(),"LOGIN SUCCESS",true);
		}else{
			responce = new Responce(theUser.getUsername(),"WRONG PASSWORD",false);
		}
		
		return responce;
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
		
		theUser.setPassword(Util.encryptePassword(theUser.getPassword()));
		
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
