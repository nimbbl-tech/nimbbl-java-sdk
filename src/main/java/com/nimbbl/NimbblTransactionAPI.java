package com.nimbbl;

import java.util.List;

import org.json.JSONObject;

public class NimbblTransactionAPI extends ApiClient {

	SegmentAPI segmentAPI;
	
	public NimbblTransactionAPI(String auth, SegmentAPI segmentApi) {
		super(auth);
		this.segmentAPI=segmentApi;
	}

	public NimbblTransaction create(JSONObject request) throws NimbblException {
		throw new NimbblException("Unsupported Method");
	}

	public List<NimbblTransaction> fetchAll() throws NimbblException {
		return fetchAll(null);
	}
	
	public List<NimbblTransaction> fetchAll(JSONObject request) throws NimbblException {
		if (request == null) {
			request = ApiUtils.preparefetchManyParams();
		}
		return getCollection(Constants.Transaction_LIST, request, NimbblTransaction.class);
	}

	public NimbblTransaction fetch(String id) throws NimbblException {
		return get(String.format(Constants.Transaction_GET, id), null, NimbblTransaction.class);
	}

	public NimbblTransaction edit(JSONObject request) throws NimbblException {
		throw new NimbblException("Unsupported Method");
	}
}
