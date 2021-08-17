package com.nimbbl;

import java.util.Collection;

import okhttp3.MediaType;

public class Constants {

	public static final MediaType MEDIA_TYPE_JSON = MediaType.parse("application/json; charset=utf-8");;
	public static final int PORT = 443;
	static final String SCHEME = "https";
	public static final String BASEURL = "uatapi.nimbbl.tech";
	public static final String SEGMENTURL = "api.segment.io";
	
	public static final String AUTH_HEADER_KEY = "Authorization";
	public static final String AUTHURL = "api/v2/generate-token";
	
	public static final String ORDER_CREATE = "api/v2/create-order";
	public static final String ORDER_GET = "api/v2/get-order/%s";
	public static final String ORDER_LIST = "api/orders/many";
	
	public static final String LIST_QUERYPARAM1 = "f";
	public static final String LIST_QUERYPARAM2 = "pt";
	public static final String NO = "no";
	public static final String Empty = "";
	
	public static final String USER_CREATE = "api/users/create";
	public static final String USER_GET = "api/users/one/%s";
	public static final String USER_LIST = "api/users/many";
	
	public static final String Transaction_CREATE = "api/transactions/create";
	public static final String Transaction_GET = "api/v2/fetch-transaction/%s";
	public static final String Transaction_LIST = "api/v2/order/fetch-transactions/%s";
	
	public static final String ACCESS_KEY = "access_key";
	public static final String SECRET_KEY = "access_secret";
	public static final String TOKEN = "token";
	public static final String Bearer = "Bearer ";
	public static final String Basic = "Basic ";
	
	public static final String SEGMENT_IDENTIFY = "v1/identify";
	public static final String SEGMENT_TRACK = "v1/track";
	public static final String SEGMENT_PAGE = "v1/page";
	public static final String SEGMENT_SCREEN = "v1/screen";
	public static final String AUTH_STATUS="auth_status";
	public static final String KIT_NAME="kit_name";
	public static final String KIT_VERSION="kit_version";
	public static final String INVOICE_ID="invoice_id";
	public static final String ORDER_ID= "order_id";
	public static final String AMOUNT="amount";
	public static final String MERCHANT_ID="merchant_id";
	public static final String MERCHANT_NAME="merchant_name";
	
	public static final String CONTEXT="context";
	public static final String EVENT="event";
	public static final String PROPERTIES="properties";
	public static final String TIMESTAMP="timestamp";
	public static final String SegmentUName = "nvilA20f1bCvxG3GAzYgD43B6gTsdwV9:";
	public static final String JAVA_SDK = "JAVA_SDK";
	public static final String USER = "userId";
	

	public static final String ORDER_CREATION_REQ = "Order Submitted";
	public static final String ORDER_CREATED = "Order Received";
	public static final String AUTH_REQ = "Authorization Submitted";
	public static final String AUTH_RES = "Authorization Received";
	public static final String NIMBBL_KEY = "x-nimbbl-key";
	public static final String AUTH_PRINCIPAL = "auth_principal";
	public static final String SUB_MERCHENT_KEY = "sub_merchant_id";
	
	public static final String TRANS_REQ = "Enquiry Submitted";
	public static final String TRANS_RES = "Enquiry Recieved";
	public static final String TRANS_ID = "Transaction id";
	public static final String TRANS_STATUS ="status";
	public static final String ANNONYMOUS_ID = "anonymousId";
	public static final String IDENTIFY = "Identify";
}
