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
	
	public NimbblTransaction fetchAll(String orderId) throws NimbblException {
		return get(String.format(Constants.Transaction_LIST, orderId),null, NimbblTransaction.class);
	}

	public NimbblTransaction fetch(String id) throws NimbblException {
		this.segmentAPI.generateTransactionReq(id);
		NimbblTransaction nimbblEntity = get(String.format(Constants.Transaction_GET, id), null, NimbblTransaction.class);
		this.segmentAPI.generateTransactionRes(nimbblEntity.getJsonModel());
		return nimbblEntity;
	}

	public NimbblTransaction edit(JSONObject request) throws NimbblException {
		throw new NimbblException("Unsupported Method");
	}
}
