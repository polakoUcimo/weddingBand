package com.springboot.weedingband.entity;

import java.util.Date;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Confirmation token class.
 * @author PC
 *
 */
@Entity
@Table(name="confirmation_token")
public class ConfirmationToken {

	/**
	 * Token id
	 */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="token_id")
    private long tokenid;

    /**
     * Conformation token
     */
    @Column(name="confirmation_token")
    private String confirmationToken;

    /**
     * Date created.
     */
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    /**
     * Foreign key linking to database user.
     */
    @OneToOne(targetEntity = User.class, fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinColumn(nullable = false, name = "id")
    private User user;
    
    /**
     * Default constructor.
     */
    public ConfirmationToken() {
    	
    }

    /**
     * Contructor
     * @param user user.
     */
    public ConfirmationToken(User user) {
        this.user = user;
        createdDate = new Date();
        confirmationToken = UUID.randomUUID().toString();
    }
    
    /**
     * Gettres and Setter.
     */

	public long getTokenid() {
		return tokenid;
	}

	public void setTokenid(long tokenid) {
		this.tokenid = tokenid;
	}

	public String getConfirmationToken() {
		return confirmationToken;
	}

	public void setConfirmationToken(String confirmationToken) {
		this.confirmationToken = confirmationToken;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

    
}
