package com.nimbbl;

import java.io.Serializable;

import org.json.JSONObject;

public abstract class NimbblEntity implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JSONObject jsonModel;
	
	
	public JSONObject getJsonModel() {
		return jsonModel;
	}

	public void setJsonModel(JSONObject jsonModel) {
		this.jsonModel = jsonModel;
	}

	@Override
	public String toString() {
		return "NimbblEntity [jsonModel=" + jsonModel + "]";
	}

	public NimbblEntity(JSONObject jsonModel) {
		super();
		this.jsonModel = jsonModel;
	}
}
