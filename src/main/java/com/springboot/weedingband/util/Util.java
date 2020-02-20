package com.springboot.weedingband.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class Util {
	
	public static String encryptePassword(String password) {

		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12); // Strength set as 12
		String encodedPassword = encoder.encode(password);
		
		return encodedPassword;
	}
}
