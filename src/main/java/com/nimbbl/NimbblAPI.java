package com.nimbbl;

import java.io.IOException;

import org.json.JSONException;

import okhttp3.Credentials;

public class NimbblAPI {

	public NimbblOrderAPI orderApi;
	public NimbblTransactionAPI transactionApi;
	public NimbblUserAPI userApi;
	
	NimbblAPI(String key,String secret) throws NimbblException, JSONException, IOException{
		this( key, secret,false);
	}
	
	NimbblAPI(String key,String secret,boolean enableLogging) throws NimbblException, JSONException, IOException{
		ApiUtils.createHttpClientInstance(enableLogging);
	    String auth = ApiUtils.getOauth(key,secret);
	    orderApi=new NimbblOrderAPI(auth);
	    transactionApi=new NimbblTransactionAPI(auth);
	    userApi=new NimbblUserAPI(auth);
	}
}
