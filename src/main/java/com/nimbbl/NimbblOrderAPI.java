package com.nimbbl;

import java.util.List;

import org.json.JSONObject;

public class NimbblOrderAPI extends ApiClient {
	
	SegmentAPI segmentAPI;

	NimbblOrderAPI(String auth, SegmentAPI segmentApi) {
		super(auth);
		this.segmentAPI=segmentApi;
	}

	public NimbblOrder create(JSONObject request) throws NimbblException {
		if (request ==null)
			throw new NimbblException("Request Object is Empty");
		else {
		// Copy so the SDK-added fields below don't mutate the caller's JSONObject.
		request = new JSONObject(request.toString());
		// order_source is fixed by the creating SDK (anti-spoof); a caller cannot override it.
		request.put(Constants.ORDER_SOURCE_KEY, Constants.ORDER_SOURCE);
		request.put(Constants.ORDER_SOURCE_VERSION_KEY, Constants.ORDER_SOURCE_VERSION);
		segmentAPI.generateJSONOrderReq(request);
		NimbblOrder res = post(Constants.ORDER_CREATE,request,NimbblOrder.class);
		segmentAPI.userId=res.getJsonModel().getString("userId");
		segmentAPI.generateJSONOrderRes(res.getJsonModel());
		return res;
		
		}
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
