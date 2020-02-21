package com.springboot.weedingband.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

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
}
