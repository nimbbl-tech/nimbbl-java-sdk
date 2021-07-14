package com.nimbbl;

import java.io.IOException;

import org.json.JSONException;

import okhttp3.Credentials;

public class NimbblAPI {

	public NimbblOrderAPI orderApi;
	public NimbblTransactionAPI transactionApi;
	public NimbblUserAPI userApi;
	private SegmentAPI segmentApi;
	
	NimbblAPI(String key,String secret) throws NimbblException, JSONException, IOException{
		this( key, secret,false);
	}
	
	NimbblAPI(String key,String secret,boolean enableLogging) throws NimbblException, JSONException, IOException{
	    String authSegment= Constants.Basic+ApiUtils.getBasicOauth();
	    segmentApi=new SegmentAPI(authSegment,key);
		ApiUtils.createHttpClientInstance(enableLogging);
	    String auth = ApiUtils.getOauth(key,secret,segmentApi);
	    auth= Constants.Bearer + auth;
	    orderApi=new NimbblOrderAPI(auth,segmentApi);
	    transactionApi=new NimbblTransactionAPI(auth,segmentApi);
	    userApi=new NimbblUserAPI(auth,segmentApi);
	}
}
