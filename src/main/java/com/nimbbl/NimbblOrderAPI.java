package com.nimbbl;

import java.util.List;

import org.json.JSONObject;

public class NimbblOrderAPI extends ApiClient {

	NimbblOrderAPI(String auth) {
		super(auth);
	}

	public NimbblOrder create(JSONObject request) throws NimbblException {
		throw new NimbblException("Unsupported Method");
	}
	
	public List<NimbblOrder> fetchAll() throws NimbblException {
		return fetchAll(null);
	}

	public List<NimbblOrder> fetchAll(JSONObject request) throws NimbblException {
		if (request == null) {
			request = ApiUtils.preparefetchManyParams();
		}
		return getCollection(Constants.ORDER_LIST, request, NimbblOrder.class);
	}

	public NimbblOrder fetch(String id) throws NimbblException {
		return get(String.format(Constants.ORDER_GET, id), null, NimbblOrder.class);
	}

	public NimbblOrder edit(JSONObject request) throws NimbblException {
		throw new NimbblException("Unsupported Method");
	}
}
