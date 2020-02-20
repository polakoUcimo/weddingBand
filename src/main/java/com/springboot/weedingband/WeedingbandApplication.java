package com.springboot.weedingband;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;


@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class WeedingbandApplication {

	public static void main(String[] args) {
		SpringApplication.run(WeedingbandApplication.class, args);
	}

}
