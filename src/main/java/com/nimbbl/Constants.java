package com.nimbbl;

import okhttp3.MediaType;

public class Constants {

	public static final MediaType MEDIA_TYPE_JSON = MediaType.parse("application/json; charset=utf-8");;
	public static final int PORT = 443;
	public static final String HOSTNAME = "uatapi.nimbbl.tech";
	public static final String SCHEME = "https";
	public static final String API = "api";
	public static final String AUTH_HEADER_KEY = "Authorization";
	public static final String AUTHURL = "v2/generate-token";
	
	public static final String ORDER_CREATE = "v2/create-order";
	public static final String ORDER_GET = "v2/get-order/%s";
	public static final String ORDER_LIST = "orders/many";
	
	public static final String LIST_QUERYPARAM1 = "f";
	public static final String LIST_QUERYPARAM2 = "pt";
	public static final String NO = "no";
	public static final String Empty = "";
	
	public static final String USER_CREATE = "users/create";
	public static final String USER_GET = "users/one/%s";
	public static final String USER_LIST = "users/many";
	
	public static final String Transaction_CREATE = "transactions/create";
	public static final String Transaction_GET = "transactions/one/%s";
	public static final String Transaction_LIST = "transactions/many?f=%sandpt=no";
	
	public static final String ACCESS_KEY = "access_key";
	public static final String SECRET_KEY = "access_secret";
	public static final String TOKEN = "token";
	public static final String Bearer = "Bearer ";
}
