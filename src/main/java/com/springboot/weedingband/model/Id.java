package com.springboot.weedingband.model;

import liquibase.pro.packaged.iD;

/**
 * Model for id.
 * @author PC
 *
 */
public class Id {
	
	/**
	 * id
	 */
	private int id;
	
	/**
	 * Empty constructor.
	 */
	public Id() {
		
	}
	
	/**
	 * Constructor with parametar.
	 * @param id id
	 */
	public Id(int id) {
		this.id=id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	
}
