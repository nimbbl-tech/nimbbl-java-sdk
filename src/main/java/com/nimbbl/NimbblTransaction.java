package com.nimbbl;

import java.io.Serializable;

import org.json.JSONObject;

public class NimbblTransaction extends NimbblEntity implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NimbblTransaction(JSONObject jsonObject) {
	    super(jsonObject);
	  }	

}
