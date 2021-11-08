package com.nimbbl;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.List;

import org.json.JSONException;
import org.junit.Test;

public class TransactionTest {

	@Test
	public void getOneTest() throws NimbblException, JSONException, IOException {
		NimbblAPI api = new NimbblAPI("access_key_1MwvMkKkweorz0ry", "access_secret_81x7ByYkRpB4g05N");
		NimbblTransaction trans= api.transactionApi.fetch("order_wr8vzzzEX91pDvdB-20211009090656");
		assertEquals(trans.getJsonModel().get("transaction_id"), "order_wr8vzzzEX91pDvdB-20211009090656");
	}
	
	@Test
	public void getManyTest() throws NimbblException, JSONException, IOException {
		NimbblAPI api = new NimbblAPI("access_key_1MwvMkKkweorz0ry", "access_secret_81x7ByYkRpB4g05N");
//		NimbblTransaction trans= api.transactionApi.fetchAll("order_BmO74nnmMLmOW3qx");
//		assertEquals(trans.getJsonModel().getJSONArray("transactions").length(), 3);
	}
	
	@Test(expected = NimbblException.class)
	public void createTest() throws NimbblException, JSONException, IOException {
		NimbblAPI api = new NimbblAPI("access_key_1MwvMkKkweorz0ry", "access_secret_81x7ByYkRpB4g05N");
		api.transactionApi.create(null);
	}
	
	@Test(expected = NimbblException.class)
	public void editTest() throws NimbblException, JSONException, IOException {
		NimbblAPI api = new NimbblAPI("access_key_1MwvMkKkweorz0ry", "access_secret_81x7ByYkRpB4g05N");
		api.transactionApi.edit(null);
	}
}
