package com.springboot.weedingband.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * Roles table
 * @author PC
 *
 */
@Entity
@Table(name="roles")
public class Roles {

	/**
	 * Token id
	 */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="role_id")
    private long roleId;

    /**
     * Role
     */
    @Column(name="role")
    private String role;
    
    /**
     * Foreign key linking to database user.
     */
    @OneToOne(targetEntity = User.class, fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinColumn(nullable = false, name = "id")
    private User user;
    
    /**
     * Empty constructor
     */
    public Roles() {
    	
    }
    
    /**
     * Contructor
     * @param user user.
     */
    public Roles(User user,String role) {
        this.user = user;
        this.role = role;
    }

	/**
	 * Getters and setters.
	 */
	
	public long getRoleId() {
		return roleId;
	}

	public void setRoleId(long roleId) {
		this.roleId = roleId;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
