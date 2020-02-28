package com.springboot.weedingband.rest;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.weedingband.dao.ConfirmationTokenRepository;
import com.springboot.weedingband.dao.UserDAOImpl;
import com.springboot.weedingband.entity.ConfirmationToken;
import com.springboot.weedingband.entity.Roles;
import com.springboot.weedingband.entity.RolesRepository;
import com.springboot.weedingband.entity.User;
import com.springboot.weedingband.errorhandle.ForbidenException;
import com.springboot.weedingband.errorhandle.UserExistsException;
import com.springboot.weedingband.errorhandle.UserNotFoundException;
import com.springboot.weedingband.model.Id;
import com.springboot.weedingband.model.ResponceBody;
import com.springboot.weedingband.service.EmailSenderService;
import com.springboot.weedingband.service.UserService;
import com.springboot.weedingband.util.Util;


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
	
	/**
	 * Bcrypt password encoder
	 */
	private BCryptPasswordEncoder encoder;
	
	/**
	 * Confirmation token repository
	 */
	@Autowired
    private ConfirmationTokenRepository confirmationTokenRepository;
	
	/**
	 * Roles repository
	 */
	@Autowired
	private RolesRepository rolesRepository;

	/**
	 * Email sender service
	 */
    @Autowired
    private EmailSenderService emailSenderService;

	@Autowired
	public UserRestController(UserService theUserService) {
		userService = theUserService;
	}
	
	/**
	 * Get all users from database.
	 * @return list of all users
	 */
	@GetMapping("/users")
	public List<User> allUsers(@RequestBody Id id) {
		
		User user = userService.findById(id.getId());
		
		Roles roles = rolesRepository.findByUserId(id.getId());
		
		if(user==null) {
			throw new UserNotFoundException("User id not found - " + id.getId());
		}else if(roles.getRole().equalsIgnoreCase(Util.Roles.ADMIN.name())) {
			return userService.findAll();
		}else {
			throw new ForbidenException("Unauthorized entry");
		}
	
	}
	
	/**
	 * Find user by id.
	 * @param userId user id.
	 * @return user with specific id.
	 */
	@GetMapping("/users/{userId}")
	public User findUserById(@PathVariable int userId,@RequestBody Id id) {
		
		User user = userService.findById(id.getId());
		
		Roles roles = rolesRepository.findByUserId(id.getId());
		
		if(user == null) {
			throw new UserNotFoundException("User id not found - " + userId);
		}else if(roles.getRole().equalsIgnoreCase(Util.Roles.ADMIN.name())) {
			return user;
		}else {
			throw new ForbidenException("Unauthorized entry");
		}
		
	}
	
	
	/**
	 * Add user to the database logic.
	 * @param theUser the user to be added.
	 * @return added user.
	 */
	@PostMapping("/users")
	public String addUser(@RequestBody User theUser) {
		
		User user = userService.findByUsername(theUser.getUsername());
		
		User user2 = userService.findByEmail(theUser.getEmail());
		
		if (user!=null) {
			throw new UserExistsException("User allready exist with: " + theUser.getUsername());
		}else if(user2!=null) {
			throw new UserExistsException("User allready exist with: " + theUser.getEmail());
		}
		
		theUser.setId(0);
		
		String encoder = Util.encryptePassword(theUser.getPassword());
		
		theUser.setPassword(encoder);
		
		
		userService.save(theUser);
		
		ConfirmationToken confirmationToken = new ConfirmationToken(theUser);
        confirmationTokenRepository.save(confirmationToken);
        
        Roles roles = new Roles(theUser,theUser.getRole());
        rolesRepository.save(roles);
        
        String toMail = "kovacjugoslav@gmail.com";
        String subject = "Complete Registration!";
        String fromMail="kovacjugoslav@gmail.com";
        String textMail="To confirm your account, please click here : \r\n" + 
        		"http://localhost:8080/confirm-account?token=" + confirmationToken.getConfirmationToken();
        
		return Util.sendMail(emailSenderService,toMail, subject, fromMail, textMail);
	}
	
	/**
	 * Confirm account via email.
	 * @param confirmationToken conformation token
	 * @return response if it is successful 
	 */
	@RequestMapping(value="/confirm-account", method= {RequestMethod.GET, RequestMethod.POST})
    public ResponceBody confirmUserAccount(@RequestParam("token")String confirmationToken)
    {
		ResponceBody responce=null;
        ConfirmationToken token = confirmationTokenRepository.findByConfirmationToken(confirmationToken);
        
        
        if(token != null)
        {
        	User user = userService.findById(token.getUser().getId());
        	user.setEnabled(true);
        	userService.update(user);
            responce = new ResponceBody(user.getUsername(),"User saved",true,user.isEnabled());
        }
        else
        {
        	 responce = new ResponceBody("error","User not saved",false,false);
        }

        return responce;
    }
	
	/**
	 * Check if user has account and simulate login.
	 * @param theUser the user to be added.
	 * @return added user.
	 */
	@PostMapping("/users/login")
	public ResponceBody loginUser(@RequestBody User theUser) {
		
		ResponceBody responce;
		
		encoder = new BCryptPasswordEncoder();
		
		User user = userService.findByUsername(theUser.getUsername());
		
		if(encoder.matches(theUser.getPassword(), user.getPassword())) {
			responce = new ResponceBody(theUser.getUsername(),"LOGIN SUCCESS",true,theUser.isEnabled());
		}else{
			responce = new ResponceBody(theUser.getUsername(),"WRONG PASSWORD",false,theUser.isEnabled());
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
		
		Roles roles = rolesRepository.findByUserId(theUser.getIdUserRole());
		
		if(user == null) {
			throw new UserNotFoundException("User id not found - " + theUser.getId());
		}else if (roles.getRole().equalsIgnoreCase(Util.Roles.ADMIN.name()) || roles.getRole().equalsIgnoreCase(Util.Roles.USER.name())) {
			theUser.setPassword(Util.encryptePassword(theUser.getPassword()));
			Util.getLogger(UserDAOImpl.class).warn("User: " + theUser.getEmail() + " " + theUser.getRole() + " " + theUser.getUsername() + " " + theUser.getId());
			userService.update(theUser);
			return theUser;
		}else {
			throw new ForbidenException("Unauthorized entry");
		}
	
	}
	
	/**
	 * Delete user with specific id.
	 * @param userId user id
	 * @return message if user has been deleted.
	 */
	@DeleteMapping("/users/{userId}")
	public ResponceBody deleteuser(@PathVariable int userId,@RequestBody Id id) {
		
		ResponceBody responce;
		
		User user = userService.findById(userId);
		
		Roles roles = rolesRepository.findByUserId(id.getId());
		
		if(user == null) {
			throw new UserNotFoundException("User id not found - " + userId);
		}else if(roles.getRole().equalsIgnoreCase(Util.Roles.ADMIN.name())){
			userService.deleteById(userId);
			return new ResponceBody(user.getUsername(),"Deleted employee id - " + userId,true,user.isEnabled());
		}else {
			throw new ForbidenException("Unauthorized entry");
		}
		
	}
	
}
