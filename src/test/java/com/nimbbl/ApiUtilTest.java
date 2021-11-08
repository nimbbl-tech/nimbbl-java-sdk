package com.nimbbl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;

public class ApiUtilTest {

	@Test
	public void signatureTest() throws NimbblException, JSONException, IOException, InvalidKeyException, NoSuchAlgorithmException {
		JSONObject s=new JSONObject();
        s.put("nimbbl_signature", "41547fa353e7e01dd6cb85715fa323fa35e3f76bf383d4041c371daff7adb645");
        s.put("nimbbl_transaction_id", "order_BmO74B5pZQJ4W3qx-20211018144400");
        s.put("order_amount", 14.0);
        s.put("order_currency", "INR");
        s.put("merchant_order_id", "order_BmO74B5pZQJ4W3qx");
        String secret="access_secret_ArL0OKDKBGx5A0zP";
        assertTrue(ApiUtils.verifySignature(s,secret));
        
	}
}
