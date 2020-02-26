package com.springboot.weedingband.rest;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mail.SimpleMailMessage;
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
import org.springframework.web.servlet.ModelAndView;

import com.springboot.weedingband.dao.ConfirmationTokenRepository;
import com.springboot.weedingband.entity.ConfirmationToken;
import com.springboot.weedingband.entity.Responce;
import com.springboot.weedingband.entity.User;
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
	
	@GetMapping("/users/{usereEmail}")
	public User findUserByEmail(@PathVariable String userEmail) {
		
		User user =userService.findByEmail(userEmail);
		
		if(user == null) {
			throw new UserNotFoundException("User id not found - " +userEmail);
		}
		
		return user;
	}
	
	/**
	 * Add user to the database.
	 * @param theUser the user to be added.
	 * @return added user.
	 */
	@PostMapping("/users")
	public String addUser(@RequestBody User theUser) {
		
		theUser.setId(0);
		
		String encoder = Util.encryptePassword(theUser.getPassword());
		
		theUser.setPassword(encoder); 
		
		userService.save(theUser);
		
		ConfirmationToken confirmationToken = new ConfirmationToken(theUser);

		/**
		 * This is not working. Fix this.
		 */
        confirmationTokenRepository.save(confirmationToken);
        
        String toMail = "kosovac.strahinja@gmail.com";
        String subject = "Complete Registration!";
        String fromMail="kosovac.strahinja@gmail.com";
        String textMail="To confirm your account, please click here : \"\r\n" + 
        		"        +\"http://localhost:8080/confirm-account?token=" + confirmationToken.getConfirmationToken();
        
		return Util.sendMail(emailSenderService,toMail, subject, fromMail, textMail);
	}
	
	@RequestMapping(value="/confirm-account", method= {RequestMethod.GET, RequestMethod.POST})
    public Responce confirmUserAccount(@RequestParam("token")String confirmationToken)
    {
		Responce responce=null;
        ConfirmationToken token = confirmationTokenRepository.findByConfirmationToken(confirmationToken);
        
        
        if(token != null)
        {
        	User user = userService.findById(token.getUser().getId());
        	user.setEnabled(true);
        	userService.update(user);
            responce = new Responce(user.getUsername(),"User saved",true,user.isEnabled());
        }
        else
        {
        	 responce = new Responce("error","User not saved",false,false);
        }

        return responce;
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
			responce = new Responce(theUser.getUsername(),"LOGIN SUCCESS",true,theUser.isEnabled());
		}else{
			responce = new Responce(theUser.getUsername(),"WRONG PASSWORD",false,theUser.isEnabled());
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
	
	
	
	@PostMapping("/password-request-reset")
	public Responce getUserMailResponce(@RequestBody String email)
	{
		//User user=userService.findByEmail(email);
		
//		if(user == null) {
//				
//			throw new UserNotFoundException("User with that email is not found  ");
//			
//		    }	
//		else {
			User user = new User("test", "test", true);
			sendPasswordMail(user,email);
			return new Responce(user.getUsername(),"email has been sent",true,true);
//			}
	}
	
	public String sendPasswordMail(User user, String email) {
		

		ConfirmationToken confirmationToken = new ConfirmationToken(user);
		confirmationTokenRepository.save(confirmationToken);
	    
	    String toMail = "kosovac.strahinja@gmail.com";
	    String subject = "Complete Registration!";
	    String fromMail="kosovac.strahinja@gmail.com";
	    String textMail="To confirm your account, please click here : \"\r\n" + 
	    		"        +\"http://localhost:8080/password-page-reset?token=" + confirmationToken.getConfirmationToken();
    return Util.sendMail(emailSenderService,toMail, subject, fromMail, textMail);}

	
	@PostMapping("/password-page-reset")	
	 public Responce getPassword(@RequestBody String password, @RequestParam("token")String confirmationToken) 
	{	
		
		Responce responce=null;
		ConfirmationToken token = confirmationTokenRepository.findByConfirmationToken(confirmationToken);
    	
		 
        if(token != null) {
		User user = userService.findById(token.getUser().getId());
    	user.setEnabled(true);
    	user.setPassword(Util.encryptePassword(password));
    	userService.update(user);
    	Util.getLogger(UserRestController.class).warn("VALUES: " + user.getId() + " " + user.getPassword() + " " + confirmationToken + " " + password);
        responce = new Responce(user.getUsername(),"User saved",true,user.isEnabled());
        }
        else{
        	responce = new Responce("error","wrong password",false,false);
        }
		return responce;
	}
	
}
