package com.springboot.weedingband.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.springboot.weedingband.service.EmailSenderService;

import junit.framework.Test;

/**
 * Util class
 * @author PC
 *
 */
public class Util {
	
	/**
	 * Encrypte password with bcrypt.
	 * @param password password to be encrypted.
	 * @return encrypted password with bcrypt.
	 */
	public static String encryptePassword(String password) {

		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12); // Strength set as 12
		String encodedPassword = encoder.encode(password);
		
		return encodedPassword;
	}
	
	/**
	 * Logger 
	 * @param class1 class
	 * @return logger
	 */
	
	public static Logger getLogger(Class class1) {
		
		Logger logger =LoggerFactory.getLogger(class1);
		
		return logger;
	}
	
	/**
	 * Function for sending email (authentification mail or forgot password)
	 * @param toMail mail witch we send.
	 * @param subject subject of the mail.
	 * @param fromMail from what mail we send.
	 * @param textMail text of message (link)
	 * @return
	 */
	public static String sendMail(EmailSenderService emailSenderService, String toMail, String subject, String fromMail, String textMail) {
		
		SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(toMail);
        mailMessage.setSubject(subject);
        mailMessage.setFrom(fromMail);
        mailMessage.setText(textMail);
        
        emailSenderService.sendEmail(mailMessage);
		
		return "Mail has been sent";
	}
	
	public static String test() {
		return null;
	}
	
}
