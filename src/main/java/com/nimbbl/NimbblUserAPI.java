package com.nimbbl;

import java.util.List;

import org.json.JSONObject;

public class NimbblUserAPI extends ApiClient {
	NimbblUserAPI(String auth) {
		super(auth);
	}

	public NimbblUser create(JSONObject request) throws NimbblException {
		throw new NimbblException("Unsupported Method");
	}

	public List<NimbblUser> fetchAll() throws NimbblException {
		return fetchAll(null);
	}
	
	public List<NimbblUser> fetchAll(JSONObject request) throws NimbblException {
		if (request == null) {
			request = ApiUtils.preparefetchManyParams();
		}
		return getCollection(Constants.USER_LIST, request, NimbblUser.class);
	}

	public NimbblUser fetch(String id) throws NimbblException {
		return get(String.format(Constants.USER_GET, id), null, NimbblUser.class);
	}

	public NimbblUser edit(JSONObject request) throws NimbblException {
		throw new NimbblException("Unsupported Method");
	}
}
