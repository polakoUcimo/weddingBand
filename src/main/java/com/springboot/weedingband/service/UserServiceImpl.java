package com.springboot.weedingband.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.weedingband.dao.UserDAO;
import com.springboot.weedingband.entity.Responce;
import com.springboot.weedingband.entity.User;

@Service
public class UserServiceImpl implements UserService {
	
	private UserDAO userDAO;
	
	@Autowired
	public UserServiceImpl(UserDAO theUserDAO) {
		userDAO = theUserDAO;
	}

	@Override
	public List<User> findAll() {
		return userDAO.findAll();
	}

	@Override
	public User findById(int theId) {
		return userDAO.findById(theId);
	}

	@Override
	public Responce save(User theUser) {
		return userDAO.save(theUser);
	}
	
	@Override
	public void update(User theUser) {
		userDAO.update(theUser);
	}

	@Override
	public void deleteById(int theId) {
		userDAO.deleteById(theId);
	}

	@Override
	public User findByUsername(String theUsername) {
		return userDAO.findByUsername(theUsername);
	}

	@Override
	public User findByEmail(String email) {
		
		return userDAO.findByEmail(email);
	}

}
