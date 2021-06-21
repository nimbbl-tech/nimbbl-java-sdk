package com.nimbbl;

import java.io.Serializable;

import org.json.JSONObject;

public class NimbblUser extends NimbblEntity implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NimbblUser(JSONObject jsonObject) {
	    super(jsonObject);
	  }	
}
