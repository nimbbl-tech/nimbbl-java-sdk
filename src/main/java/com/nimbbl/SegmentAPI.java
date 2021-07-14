package com.nimbbl;

import java.sql.Timestamp;
import java.util.List;

import org.json.JSONObject;

public class SegmentAPI extends ApiClient {

	String userId;
	SegmentAPI(String auth,String userId) {
		super(auth);
		this.userId=userId;
	}
	
	public Segment identify(JSONObject request) throws NimbblException {
		return post(Constants.SEGMENT_IDENTIFY, request, Segment.class,true);
	}
	
	public Segment track(JSONObject request) throws NimbblException {
		return post(Constants.SEGMENT_TRACK, request, Segment.class,true);
	}
	
	public Segment page(JSONObject request) throws NimbblException {
		return post(Constants.SEGMENT_PAGE, request, Segment.class,true);
	}
	
	public Segment screen(JSONObject request) throws NimbblException {
		return post(Constants.SEGMENT_SCREEN, request, Segment.class,true);
	}
	
	public Segment generateJSONOrderReq(JSONObject request) throws NimbblException {
		JSONObject jsonObj=new JSONObject();
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		if (request != null) {
			jsonObj.put(Constants.USER,userId);
			jsonObj.put(Constants.EVENT,Constants.ORDER_CREATION_REQ);
			JSONObject propObj=new JSONObject();
			propObj.put(Constants.INVOICE_ID, request.get("invoice_id"));
			propObj.put(Constants.AMOUNT, request.get("total_amount"));
//			propObj.put(Constants.MERCHANT_ID, request.get("merchant_id"));
//			propObj.put(Constants.MERCHANT_NAME, request.get("merchant_name"));
			propObj.put(Constants.KIT_NAME, Constants.JAVA_SDK);
			propObj.put(Constants.KIT_VERSION, ApiUtils.getProjectVersion());
			jsonObj.put(Constants.PROPERTIES,propObj );
			jsonObj.put(Constants.TIMESTAMP,timestamp );
		}
		return track(jsonObj);
	}
	
	public Segment generateJSONOrderRes(JSONObject request) throws NimbblException {
		JSONObject jsonObj=new JSONObject();
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		if (request != null) {
			jsonObj.put(Constants.USER,userId);
			jsonObj.put(Constants.EVENT,Constants.ORDER_CREATED);
			JSONObject propObj=new JSONObject();
			propObj.put(Constants.INVOICE_ID, request.get("invoice_id"));
			propObj.put(Constants.AMOUNT, request.get("total_amount"));
//			propObj.put(Constants.MERCHANT_ID, request.get("merchant_id"));
//			propObj.put(Constants.MERCHANT_NAME, request.get("merchant_name"));
			propObj.put(Constants.KIT_NAME, Constants.JAVA_SDK);
			propObj.put(Constants.KIT_VERSION, ApiUtils.getProjectVersion());
			jsonObj.put(Constants.PROPERTIES,propObj );
			jsonObj.put(Constants.TIMESTAMP,timestamp );
		}
		return  track(jsonObj);
	}
	
	public Segment generateJSONAuthReq(JSONObject request) throws NimbblException {
		JSONObject jsonObj=new JSONObject();
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		if (request != null) {
			jsonObj.put(Constants.USER,userId);
			jsonObj.put(Constants.EVENT,Constants.AUTH_REQ);
			JSONObject propObj=new JSONObject();
			propObj.put(Constants.ACCESS_KEY, request.get("access_key"));
			propObj.put(Constants.KIT_NAME, Constants.JAVA_SDK);
			propObj.put(Constants.KIT_VERSION, ApiUtils.getProjectVersion());
			jsonObj.put(Constants.PROPERTIES,propObj );
			jsonObj.put(Constants.TIMESTAMP,timestamp );
		}
		return  track(jsonObj);
	}
	
	public Segment generateJSONAuthRes(int code,String acceess_key) throws NimbblException {
		JSONObject jsonObj=new JSONObject();
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
			jsonObj.put(Constants.USER,userId);
			jsonObj.put(Constants.EVENT,Constants.AUTH_RES);
			JSONObject propObj=new JSONObject();
			propObj.put(Constants.ACCESS_KEY, acceess_key);
			propObj.put(Constants.AUTH_STATUS,code );
			propObj.put(Constants.KIT_NAME, Constants.JAVA_SDK);
			propObj.put(Constants.KIT_VERSION, ApiUtils.getProjectVersion());
			jsonObj.put(Constants.PROPERTIES,propObj );
			jsonObj.put(Constants.TIMESTAMP,timestamp );
		return  track(jsonObj);
	}

}
