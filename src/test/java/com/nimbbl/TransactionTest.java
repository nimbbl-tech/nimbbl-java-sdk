package com.nimbbl;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.List;

import org.json.JSONException;
import org.junit.Test;

public class TransactionTest {

	@Test
	public void getOneTest() throws NimbblException, JSONException, IOException {
		NimbblAPI api= new NimbblAPI("{accesskey}", "{secret_key}");
		NimbblTransaction trans= api.transactionApi.fetch("{transaction_id}");
		assertEquals(trans.getJsonModel().get("transaction_id"), "{transaction_id}");
	}
	
	@Test
	public void getAllTest() throws NimbblException, JSONException, IOException {
		NimbblAPI api= new NimbblAPI("{accesskey}", "{secret_key}");
		List<NimbblTransaction> trans= api.transactionApi.fetchAll();
		assertEquals(trans.size(), 0);
	}
	
	@Test(expected = NimbblException.class)
	public void createTest() throws NimbblException, JSONException, IOException {
		NimbblAPI api= new NimbblAPI("{accesskey}", "{secret_key}");
		api.transactionApi.create(null);
	}
	
	@Test(expected = NimbblException.class)
	public void editTest() throws NimbblException, JSONException, IOException {
		NimbblAPI api= new NimbblAPI("{accesskey}", "{secret_key}");
		api.transactionApi.edit(null);
	}
}
