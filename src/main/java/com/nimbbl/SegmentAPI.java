package com.nimbbl;

import java.sql.Timestamp;
import java.util.UUID;

import org.json.JSONObject;

public class SegmentAPI extends ApiClient {

	String userId;
	String annonymousId;

	SegmentAPI(String auth, String userId) {
		super(auth);
		this.annonymousId = UUID.randomUUID().toString();
	}

	public Segment identify(JSONObject request) throws NimbblException {
		return post(Constants.SEGMENT_IDENTIFY, request, Segment.class, true);
	}

	public Segment track(JSONObject request) throws NimbblException {
		return post(Constants.SEGMENT_TRACK, request, Segment.class, true);
	}

	public Segment page(JSONObject request) throws NimbblException {
		return post(Constants.SEGMENT_PAGE, request, Segment.class, true);
	}

	public Segment screen(JSONObject request) throws NimbblException {
		return post(Constants.SEGMENT_SCREEN, request, Segment.class, true);
	}

	public Segment generateJSONOrderReq(JSONObject request) throws NimbblException {
		JSONObject jsonObj = new JSONObject();
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		if (request != null) {
			if (userId != null && !userId.isEmpty()) {
				jsonObj.put(Constants.USER, userId);
			}
			jsonObj.put(Constants.ANNONYMOUS_ID, annonymousId);
			jsonObj.put(Constants.EVENT, Constants.ORDER_CREATION_REQ);
			JSONObject propObj = new JSONObject();
			propObj.put(Constants.INVOICE_ID, request.get("invoice_id"));
			propObj.put(Constants.AMOUNT, request.get("total_amount"));
			propObj.put(Constants.MERCHANT_ID, ApiClient.getMerchentId());
			propObj.put(Constants.KIT_NAME, Constants.JAVA_SDK);
			propObj.put(Constants.KIT_VERSION, ApiUtils.getProjectVersion());
			jsonObj.put(Constants.PROPERTIES, propObj);
			jsonObj.put(Constants.TIMESTAMP, timestamp);
		}
		return track(jsonObj);
	}

	public Segment generateJSONOrderRes(JSONObject request) throws NimbblException {
		JSONObject jsonObj = new JSONObject();
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		if (request != null) {
			if (userId != null && !userId.isEmpty()) {
				jsonObj.put(Constants.USER, userId);
			}
			jsonObj.put(Constants.ANNONYMOUS_ID, annonymousId);
			jsonObj.put(Constants.EVENT, Constants.ORDER_CREATED);
			JSONObject propObj = new JSONObject();
			propObj.put(Constants.INVOICE_ID, request.get("invoice_id"));
			propObj.put(Constants.AMOUNT, request.get("total_amount"));
			propObj.put(Constants.MERCHANT_ID, ApiClient.getMerchentId());
			propObj.put(Constants.KIT_NAME, Constants.JAVA_SDK);
			propObj.put(Constants.KIT_VERSION, ApiUtils.getProjectVersion());
			jsonObj.put(Constants.PROPERTIES, propObj);
			jsonObj.put(Constants.TIMESTAMP, timestamp);
		}
		return track(jsonObj);
	}

	public Segment generateJSONAuthReq(JSONObject request) throws NimbblException {
		JSONObject jsonObj = new JSONObject();
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		if (request != null) {
			if (userId != null && !userId.isEmpty()) {
				jsonObj.put(Constants.USER, userId);
			}
			jsonObj.put(Constants.ANNONYMOUS_ID, annonymousId);
			jsonObj.put(Constants.EVENT, Constants.AUTH_REQ);
			JSONObject propObj = new JSONObject();
			propObj.put(Constants.ACCESS_KEY, request.get("access_key"));
			propObj.put(Constants.KIT_NAME, Constants.JAVA_SDK);
			propObj.put(Constants.KIT_VERSION, ApiUtils.getProjectVersion());
			jsonObj.put(Constants.PROPERTIES, propObj);
			jsonObj.put(Constants.TIMESTAMP, timestamp);
		}
		return track(jsonObj);
	}

	public Segment generateJSONAuthRes(int code, String acceess_key) throws NimbblException {
		JSONObject jsonObj = new JSONObject();
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		if (userId != null && !userId.isEmpty()) {
			jsonObj.put(Constants.USER, userId);
		}
		jsonObj.put(Constants.ANNONYMOUS_ID, annonymousId);
		jsonObj.put(Constants.EVENT, Constants.AUTH_RES);
		JSONObject propObj = new JSONObject();
		propObj.put(Constants.ACCESS_KEY, acceess_key);
		propObj.put(Constants.AUTH_STATUS, code);
		propObj.put(Constants.KIT_NAME, Constants.JAVA_SDK);
		propObj.put(Constants.KIT_VERSION, ApiUtils.getProjectVersion());
		jsonObj.put(Constants.PROPERTIES, propObj);
		jsonObj.put(Constants.TIMESTAMP, timestamp);
		return track(jsonObj);
	}

	public Segment generateTransactionReq(String transactionId) throws NimbblException {

		JSONObject jsonObj = new JSONObject();
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		if (userId != null && !userId.isEmpty()) {
			jsonObj.put(Constants.USER, userId);
		}
		jsonObj.put(Constants.ANNONYMOUS_ID, annonymousId);
		jsonObj.put(Constants.EVENT, Constants.TRANS_REQ);
		JSONObject propObj = new JSONObject();
		propObj.put(Constants.TRANS_ID, transactionId);
		propObj.put(Constants.KIT_NAME, Constants.JAVA_SDK);
		propObj.put(Constants.KIT_VERSION, ApiUtils.getProjectVersion());
		jsonObj.put(Constants.PROPERTIES, propObj);
		jsonObj.put(Constants.TIMESTAMP, timestamp);
		return track(jsonObj);
	}

	public Segment generateTransactionRes(JSONObject jsonRes) throws NimbblException {
		JSONObject jsonObj = new JSONObject();
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		if (userId != null && !userId.isEmpty()) {
			jsonObj.put(Constants.USER, userId);
		}
		jsonObj.put(Constants.ANNONYMOUS_ID, annonymousId);
		jsonObj.put(Constants.EVENT, Constants.TRANS_RES);
		JSONObject propObj = new JSONObject();
		propObj.put(Constants.TRANS_ID, jsonRes.getString("transaction_id"));
		propObj.put(Constants.TRANS_STATUS, jsonRes.getString("transaction_id"));
		propObj.put(Constants.KIT_NAME, Constants.JAVA_SDK);
		propObj.put(Constants.KIT_VERSION, ApiUtils.getProjectVersion());
		jsonObj.put(Constants.PROPERTIES, propObj);
		jsonObj.put(Constants.TIMESTAMP, timestamp);
		return track(jsonObj);
	}

	public Segment generateIdentify() throws NimbblException {
		JSONObject jsonObj = new JSONObject();
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		if (userId != null && !userId.isEmpty()) {
			jsonObj.put(Constants.USER, userId);
		}
		jsonObj.put(Constants.ANNONYMOUS_ID, annonymousId);
		jsonObj.put(Constants.EVENT, Constants.IDENTIFY);
		JSONObject propObj = new JSONObject();
		propObj.put(Constants.KIT_NAME, Constants.JAVA_SDK);
		propObj.put(Constants.KIT_VERSION, ApiUtils.getProjectVersion());
		jsonObj.put(Constants.PROPERTIES, propObj);
		jsonObj.put(Constants.TIMESTAMP, timestamp);
		return identify(jsonObj);
	}

}
