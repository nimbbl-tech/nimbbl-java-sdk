package com.nimbbl;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.commons.text.WordUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import okhttp3.Response;

class ApiClient {

	String auth;
		
	private static int merchentId;
	
	private final String NimbblEntity = "NimbblEntity";

	private final String COLLECTION = "collection";

	private final String ERROR = "error";

	private final String DESCRIPTION = "description";

	private final String STATUS_CODE = "code";

	private final int STATUS_OK = 200;

	private final int STATUS_MULTIPLE_CHOICE = 300;

	ApiClient(String auth) {
		this.auth = auth;
	}

	public static void setMerchentId(int id) {
		merchentId=id;
	}
	
	public static int getMerchentId() {
		return merchentId;
	}
	

	public <T extends NimbblEntity> T get(String path, JSONObject requestObject, Class className)
			throws NimbblException {
		Response response = ApiUtils.getRequest(Constants.BASEURL, path, requestObject, auth);
		return processResponse(response, className);
	}

	public <T extends NimbblEntity> T post(String path, JSONObject requestObject, Class className)
			throws NimbblException {
		Response response = ApiUtils.postRequest(Constants.BASEURL,path, requestObject, auth);
		return processResponse(response, className);
	}
	// For Segment API Call
	public <T extends NimbblEntity> T post(String path, JSONObject requestObject, Class className,boolean isSegment)
			throws NimbblException {
		Response response = ApiUtils.postRequest(Constants.SEGMENTURL,path, requestObject, auth);
		return processResponse(response, className);
	}

	public <T extends NimbblEntity> T put(String path, JSONObject requestObject, Class className)
			throws NimbblException {
		Response response = ApiUtils.putRequest(Constants.BASEURL,path, requestObject, auth);
		return processResponse(response, className);
	}

	public <T extends NimbblEntity> T patch(String path, JSONObject requestObject, Class className)
			throws NimbblException {
		Response response = ApiUtils.patchRequest(Constants.BASEURL,path, requestObject, auth);
		return processResponse(response, className);
	}

	public void delete(String path, JSONObject requestObject) throws NimbblException {
		Response response = ApiUtils.deleteRequest(Constants.BASEURL,path, requestObject, auth);
		processDeleteResponse(response);
	}

	<T extends NimbblEntity> ArrayList<T> getCollection(String path, JSONObject requestObject, Class className)
			throws NimbblException {
		Response response = ApiUtils.getRequest(Constants.BASEURL,path, requestObject, auth);
		return processCollectionResponse(response, className);
	}
	

	private <T extends NimbblEntity> T parseResponse(JSONObject jsonObject, Class className) throws NimbblException {
		if (jsonObject != null) {
//			Class<T> cls = getClass(jsonObject.getString(NimbblEntity));
			Class<T> cls = getClass(className.getName());
			try {
				return cls.getConstructor(JSONObject.class).newInstance(jsonObject);
			} catch (Exception e) {
				throw new NimbblException("Unable to parse response because of " + e.getMessage());
			}
		}

		throw new NimbblException("Unable to parse response");
	}

	private <T extends NimbblEntity> ArrayList<T> parseCollectionResponse(JSONObject jsonObject, Class className)
			throws NimbblException {

		ArrayList<T> modelList = new ArrayList<T>();
		if (jsonObject != null) {
			JSONArray jsonArray = jsonObject.getJSONArray("items");
			try {
				for (int i = 0; i < jsonArray.length(); i++) {
					JSONObject jsonObj = jsonArray.getJSONObject(i);
					T t = parseResponse(jsonObj, className);
					modelList.add(t);
				}
				return modelList;
			} catch (NimbblException e) {
				throw e;
			}
		}

		throw new NimbblException("Unable to parse response");
	}

	<T extends NimbblEntity> T processResponse(Response response, Class className) throws NimbblException {
		if (response == null) {
			throw new NimbblException("Invalid Response from server");
		}

		int statusCode = response.code();
		String responseBody = null;
		JSONObject responseJson = null;

		try {
			responseBody = response.body().string();
			responseJson = new JSONObject(responseBody);
		} catch (IOException e) {
			throw new NimbblException(e.getMessage());
		}

		if (statusCode >= STATUS_OK && statusCode < STATUS_MULTIPLE_CHOICE) {
			return parseResponse(responseJson, className);
		}

		throwException(statusCode, responseJson);
		return null;
	}

	<T extends NimbblEntity> ArrayList<T> processCollectionResponse(Response response, Class className)
			throws NimbblException {
		if (response == null) {
			throw new NimbblException("Invalid Response from server");
		}

		int statusCode = response.code();
		String responseBody = null;
		JSONObject responseJson = null;

		try {
			responseBody = response.body().string();
			responseJson = new JSONObject(responseBody);
		} catch (IOException e) {
			throw new NimbblException(e.getMessage());
		}

		if (statusCode >= STATUS_OK && statusCode < STATUS_MULTIPLE_CHOICE) {
			return parseCollectionResponse(responseJson, className);
		}

		throwException(statusCode, responseJson);
		return null;
	}

	private void processDeleteResponse(Response response) throws NimbblException {
		if (response == null) {
			throw new NimbblException("Invalid Response from server");
		}

		int statusCode = response.code();
		String responseBody = null;
		JSONObject responseJson = null;

		try {
			responseBody = response.body().string();
			responseJson = new JSONObject(responseBody);
		} catch (IOException e) {
			throw new NimbblException(e.getMessage());
		}

		if (statusCode < STATUS_OK || statusCode >= STATUS_MULTIPLE_CHOICE) {
			throwException(statusCode, responseJson);
		}
	}

	private void throwException(int statusCode, JSONObject responseJson) throws NimbblException {
		if (responseJson.has(ERROR)) {
			JSONObject errorResponse = responseJson.getJSONObject(ERROR);
			String code = errorResponse.getString(STATUS_CODE);
			String description = errorResponse.getString(DESCRIPTION);
			throw new NimbblException(code + ":" + description);
		}
		throwServerException(statusCode, responseJson.toString());
	}

	private void throwServerException(int statusCode, String responseBody) throws NimbblException {
		StringBuilder sb = new StringBuilder();
		sb.append("Status Code: ").append(statusCode).append("\n");
		sb.append("Server response: ").append(responseBody);
		throw new NimbblException(sb.toString());
	}

	private Class getClass(String NimbblEntity) {
		try {
			String NimbblEntityClass = NimbblEntity;
			return Class.forName(NimbblEntityClass);
		} catch (ClassNotFoundException e) {
			return null;
		}
	}
}
