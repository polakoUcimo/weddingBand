package com.springboot.weedingband.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Repository;

import com.springboot.weedingband.entity.User;
import com.springboot.weedingband.model.ResponceBody;
import com.springboot.weedingband.util.Util;

/**
 * User DAO Implementation - all logic goes here.
 * @author PC
 *
 */
@Repository
public class UserDAOImpl implements UserDAO {
	
	/**
	 * Entity manager
	 */
	private EntityManager entityManager;
	
	/**
	 * Hibernate session.
	 */
	private Session currentSession;
	
	/**
	 * Contructor with the entity manager set.
	 * @param theEntityManager entity manager
	 */
	@Autowired
	public  UserDAOImpl(EntityManager theEntityManager) {
		entityManager = theEntityManager;
	}

	/**
	 * List of all users.
	 */
	@Override
	@Transactional
	public List<User> findAll() {
		
		getSession();
		
		Query<User> theQuery = currentSession.createQuery("from User", User.class);
		
		List<User> users = theQuery.getResultList();
		
		return users;
	}

	/**
	 * Find user by id.
	 */
	@Override
	@Transactional
	public User findById(long theId) {
		
		getSession();
		
		User user = currentSession.get(User.class, theId);
		
		return user;
	}
	
	/**
	 * Find user by username
	 */
	@Override
	public User findByUsername(String theUsername) {
		
		getSession();
		 
		Query<User> theQuery = currentSession.createQuery("from User where username=:username", User.class);
		
		theQuery.setParameter("username", theUsername);
		
		List<User> user = theQuery.getResultList();
		
		for(int i=0;i<user.size();i++) {
			if(user.get(i).getUsername().equalsIgnoreCase(theUsername)) {
				return user.get(i);
			}
		}
		
		return null;
	}
	
	/**
	 * Find user by email
	 */
	@Override
	public User findByEmail(String email) {

		getSession();

		Query<User> theQuery = currentSession.createQuery("from User where email=:email", User.class);

		theQuery.setParameter("email", email);

		List<User> user = theQuery.getResultList();
		
		for(int i=0;i<user.size();i++) {
			if(user.get(i).getEmail().equalsIgnoreCase(email)) {
				return user.get(i);
			}
		}

		return null;
	}
	
	
	/**
	 * Check if user exists, if not saves him to database.
	 */
	@Override
	@Transactional
	public ResponceBody save(User theUser) {
		
		ResponceBody responce;
	
		getSession();
		
		Query<User> theQuery = currentSession.createQuery("from User where username=:username", User.class);
		
		theQuery.setParameter("username", theUser.getUsername());
		
		User user = theQuery.uniqueResult();

		if(user==null) {
			currentSession.save(theUser);
			responce = new ResponceBody(theUser.getUsername(), "User has been saved", true,theUser.isEnabled());
		}else {
			responce = new ResponceBody(theUser.getUsername(), "Username allready exists", false,theUser.isEnabled());
		}
		
		return responce;
	}
	
	/**
	 * Update the user.
	 */
	@Override
	@Transactional
	public void update(User theUser) {
		
		currentSession.clear();
		
		getSession();
		
		Util.getLogger(UserDAOImpl.class).warn("User: " + theUser.getEmail(), theUser.getRole(), theUser.getUsername(), theUser.getId());
		
		currentSession.update(theUser);
	}
	
	

	/**
	 * Delete user by id.
	 */
	@Override
	@Transactional
	public void deleteById(int theId) {
		
		getSession();
		
		Util.getLogger(UserDAOImpl.class).warn("User id: " + theId);
		
		User user = findById(theId);
		
		currentSession.delete(user);
		
	}
	
	/**
	 * Get the session.
	 */
	private void getSession() {
		currentSession = entityManager.unwrap(Session.class);
	}

}
