package com.nimbbl;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

import org.apache.maven.model.Model;
import org.apache.maven.model.io.xpp3.MavenXpp3Reader;
import org.codehaus.plexus.util.xml.pull.XmlPullParserException;
import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

class ApiUtils {

	private static OkHttpClient client;
	private static Map<String, String> headers = new HashMap<String, String>();

	private static String version = null;

	static void createHttpClientInstance(boolean enableLogging) throws NimbblException {
		if (client == null) {
			HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
			if (enableLogging) {
				loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);
			} else {
				loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.NONE);
			}

			try {
				client = new OkHttpClient.Builder().readTimeout(60, TimeUnit.SECONDS).writeTimeout(60, TimeUnit.SECONDS)
						.addInterceptor(loggingInterceptor)
						.sslSocketFactory(new CustomTLSSocketFactory(), createDefaultTrustManager()).build();
			} catch (Exception e) {
				throw new NimbblException(e);
			}
		}

		Properties properties = new Properties();
		try {
			properties.load(ApiUtils.class.getResourceAsStream("/project.properties"));
			version = (String) properties.get("version");
		} catch (IOException e) {
			throw new NimbblException(e.getMessage());
		}
	}

	private enum Method {
		GET, POST, PUT, PATCH, DELETE
	}

	static Response postRequest(String base,String path, JSONObject requestObject, String auth) throws NimbblException {

		HttpUrl.Builder builder = getBuilder(base,path);

		String requestContent = requestObject == null ? "" : requestObject.toString();
		RequestBody requestBody = RequestBody.create(Constants.MEDIA_TYPE_JSON, requestContent);

		Request request = createRequest(Method.POST.name(), builder.build().toString(), requestBody, auth);
		return processRequest(request);
	}

	static Response putRequest(String base,String path, JSONObject requestObject, String auth) throws NimbblException {

		HttpUrl.Builder builder = getBuilder(base,path);

		String requestContent = requestObject == null ? "" : requestObject.toString();
		RequestBody requestBody = RequestBody.create(Constants.MEDIA_TYPE_JSON, requestContent);

		Request request = createRequest(Method.PUT.name(), builder.build().toString(), requestBody, auth);
		return processRequest(request);
	}

	static Response patchRequest(String base,String path, JSONObject requestObject, String auth) throws NimbblException {

		HttpUrl.Builder builder = getBuilder(base,path);

		String requestContent = requestObject == null ? "" : requestObject.toString();
		RequestBody requestBody = RequestBody.create(Constants.MEDIA_TYPE_JSON, requestContent);

		Request request = createRequest(Method.PATCH.name(), builder.build().toString(), requestBody, auth);
		return processRequest(request);
	}

	static Response getRequest(String base,String path, JSONObject requestObject, String auth) throws NimbblException {

		HttpUrl.Builder builder = getBuilder(base,path);
		addQueryParams(builder, requestObject);

		Request request = createRequest(Method.GET.name(), builder.build().toString(), null, auth);
		return processRequest(request);
	}

	static Response deleteRequest(String base,String path, JSONObject requestObject, String auth) throws NimbblException {

		HttpUrl.Builder builder = getBuilder(base,path);
		addQueryParams(builder, requestObject);

		Request request = createRequest(Method.DELETE.name(), builder.build().toString(), null, auth);
		return processRequest(request);
	}

	private static HttpUrl.Builder getBuilder(String base, String path) {
		return new HttpUrl.Builder().scheme(Constants.SCHEME).host(base).addPathSegments(path);
	}

	private static Request createRequest(String method, String url, RequestBody requestBody, String auth) {
		
		Request.Builder builder = new Request.Builder().url(url);
		
		if(auth!= null && auth.split(" ").length>1) {
			builder.addHeader(Constants.AUTH_HEADER_KEY,
					 auth);
		}
		for (Map.Entry<String, String> header : headers.entrySet()) {
			builder.addHeader(header.getKey(), header.getValue());
		}

		return builder.method(method, requestBody).build();
	}

	private static void addQueryParams(HttpUrl.Builder builder, JSONObject request) {
		if (request == null)
			return;

		Iterator<?> keys = request.keys();
		while (keys.hasNext()) {
			String key = (String) keys.next();
			builder.addQueryParameter(key, request.get(key).toString());
		}
	}

	private static Response processRequest(Request request) throws NimbblException {
		try {
			return client.newCall(request).execute();
		} catch (IOException e) {
			throw new NimbblException(e.getMessage());
		}
	}

	static void addHeaders(Map<String, String> header) {
		headers.putAll(header);
	}

	private static X509TrustManager createDefaultTrustManager() throws NoSuchAlgorithmException, KeyStoreException {
		TrustManagerFactory trustManagerFactory = TrustManagerFactory
				.getInstance(TrustManagerFactory.getDefaultAlgorithm());
		trustManagerFactory.init((KeyStore) null);
		TrustManager[] trustManagers = trustManagerFactory.getTrustManagers();
		if (trustManagers.length != 1 || !(trustManagers[0] instanceof X509TrustManager)) {
			throw new IllegalStateException("Unexpected default trust managers:" + Arrays.toString(trustManagers));
		}
		X509TrustManager trustManager = (X509TrustManager) trustManagers[0];
		return trustManager;
	}

	public static String getOauth(String key, String secret, SegmentAPI segmentApi) throws NimbblException, JSONException, IOException {
		JSONObject json = new JSONObject();
		json.put(Constants.ACCESS_KEY, key);
		json.put(Constants.SECRET_KEY, secret);
		
		segmentApi.generateJSONAuthReq(json);
		
		Response res = postRequest(Constants.BASEURL,Constants.AUTHURL, json, null);
		String str = res.body().string();
		JSONObject jsonRes = new JSONObject(str);
		
		segmentApi.generateJSONAuthRes(res.code(),key);
		
		return jsonRes.getString(Constants.TOKEN);
	}

	public static JSONObject preparefetchManyParams() {
		JSONObject obj = new JSONObject();
		obj.put(Constants.LIST_QUERYPARAM1, Constants.Empty);
		obj.put(Constants.LIST_QUERYPARAM2, Constants.NO);
		return obj;
	}

	public static String getBasicOauth() {
		return Base64.getEncoder().encodeToString(Constants.SegmentUName.getBytes());
	}
	
	public static String getProjectVersion() {
	       	MavenXpp3Reader reader = new MavenXpp3Reader();
	        Model model;
			try {
				model = reader.read(new FileReader("pom.xml"));
				return model.getVersion();
			} catch (IOException | XmlPullParserException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        return "No Version Found";
	    }
	
	public static String readFileAsString(String file)throws Exception
    {
        return new String(Files.readAllBytes(Paths.get(file)));
    }
}
