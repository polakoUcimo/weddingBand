//package com.springboot.weedingband.security;

//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.web.savedrequest.NullRequestCache;

//@Configuration
//@EnableWebSecurity
//    public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
//
//        @Override
//        protected void configure(HttpSecurity http) throws Exception {
//        	http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().
//            authorizeRequests()
//			.antMatchers(HttpMethod.GET, "/users").hasRole("ADMIN")
//			.antMatchers(HttpMethod.GET, "/users/{userId}").hasRole("ADMIN")
//			.antMatchers(HttpMethod.POST, "/users").permitAll()
//			.antMatchers(HttpMethod.PUT, "/users").hasAnyRole("USER","BAND")
//			.antMatchers(HttpMethod.DELETE, "/users/{userId}").hasRole("ADMIN")
//			.antMatchers(HttpMethod.POST, "/users/login").hasAnyRole("USER","BAND","ADMIN")
//			.and().requestCache().requestCache(new NullRequestCache())
//			.and().cors()
//			.and().csrf().disable();
//        }
//}
