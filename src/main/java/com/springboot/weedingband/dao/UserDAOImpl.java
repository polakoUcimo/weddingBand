package com.springboot.weedingband.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Repository;

import com.springboot.weedingband.entity.ConfirmationToken;
import com.springboot.weedingband.entity.Responce;
import com.springboot.weedingband.entity.User;
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
	 * Confirmation token repository
	 */
	@Autowired
    private ConfirmationTokenRepository confirmationTokenRepository;
	
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
	public User findById(int theId) {
		
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
		
		User user = theQuery.uniqueResult();
		
		return user;
	}

	/**
	 * Check if user exists, if not saves him to database.
	 */
	@Override
	@Transactional
	public Responce save(User theUser) {
		
		Responce responce;
	
		getSession();
		
		Query<User> theQuery = currentSession.createQuery("from User where username=:username", User.class);
		
		theQuery.setParameter("username", theUser.getUsername());
		
		User user = theQuery.uniqueResult();

		if(user==null) {
			currentSession.save(theUser);
			responce = new Responce(theUser.getUsername(), "User has been saved", true,theUser.isEnabled());
		}else {
			responce = new Responce(theUser.getUsername(), "Username allready exists", false,theUser.isEnabled());
		}
		
		return responce;
	}
	
	/**
	 * Update the user.
	 */
	@Override
	@Transactional
	public void update(User theUser) {
		
		getSession();
		
		currentSession.merge(theUser);
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
		
//		Query<?> theQuery = currentSession.createQuery("delete from ConfirmationToken where id:=userId");
//		
//		theQuery.setParameter("userId", theId);
//		
//		theQuery.executeUpdate();
		 
//		Query<?> theQuery = currentSession.createQuery("delete from User where id=:userId");
//		
//		theQuery.setParameter("userId", theId);
//		
//		theQuery.executeUpdate(); 
		
	}
	
	/**
	 * Get the session.
	 */
	private void getSession() {
		currentSession = entityManager.unwrap(Session.class);
	}

}
