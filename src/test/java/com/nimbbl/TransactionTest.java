package com.nimbbl;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.List;

import org.json.JSONException;
import org.junit.Test;

public class TransactionTest {

	@Test
	public void getOneTest() throws NimbblException, JSONException, IOException {
		NimbblAPI api= new NimbblAPI("access_key_1MwvMkKkweorz0ry", "access_secret_81x7ByYkRpB4g05N");
		NimbblTransaction trans= api.transactionApi.fetch("order_4JB02okpV922r7yN-20210617102109");
		assertEquals(trans.getJsonModel().get("transaction_id"), "order_4JB02okpV922r7yN-20210617102109");
	}
	
	@Test
	public void getAllTest() throws NimbblException, JSONException, IOException {
		NimbblAPI api= new NimbblAPI("access_key_1MwvMkKkweorz0ry", "access_secret_81x7ByYkRpB4g05N");
//		List<NimbblTransaction> trans= api.transactionApi.fetchAll();
//		assertEquals(trans.size(), 0);
	}
	
	@Test(expected = NimbblException.class)
	public void createTest() throws NimbblException, JSONException, IOException {
		NimbblAPI api= new NimbblAPI("access_key_1MwvMkKkweorz0ry", "access_secret_81x7ByYkRpB4g05N");
		api.transactionApi.create(null);
	}
	
	@Test(expected = NimbblException.class)
	public void editTest() throws NimbblException, JSONException, IOException {
		NimbblAPI api= new NimbblAPI("access_key_1MwvMkKkweorz0ry", "access_secret_81x7ByYkRpB4g05N");
		api.transactionApi.edit(null);
	}
}
