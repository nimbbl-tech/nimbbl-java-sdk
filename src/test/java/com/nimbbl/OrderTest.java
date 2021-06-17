package com.nimbbl;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.List;

import org.json.JSONException;
import org.junit.Test;

public class OrderTest {

	@Test
	public void getOneTest() throws NimbblException, JSONException, IOException {
		NimbblAPI api= new NimbblAPI("access_key_1MwvMkKkweorz0ry", "access_secret_81x7ByYkRpB4g05N");
		NimbblOrder order= api.orderApi.fetch("order_x1Dve4Ex6KXEm7KB");
		assertEquals(order.getJsonModel().get("order_id"), "order_x1Dve4Ex6KXEm7KB");
	}
	
	@Test
	public void getAllTest() throws NimbblException, JSONException, IOException {
		NimbblAPI api= new NimbblAPI("access_key_1MwvMkKkweorz0ry", "access_secret_81x7ByYkRpB4g05N");
		List<NimbblOrder> order= api.orderApi.fetchAll();
		assertEquals(order.size(), 20);
	}
	
	@Test(expected = NimbblException.class)
	public void createTest() throws NimbblException, JSONException, IOException {
		NimbblAPI api= new NimbblAPI("access_key_1MwvMkKkweorz0ry", "access_secret_81x7ByYkRpB4g05N");
		api.orderApi.create(null);
	}
	
	@Test(expected = NimbblException.class)
	public void editTest() throws NimbblException, JSONException, IOException {
		NimbblAPI api= new NimbblAPI("access_key_1MwvMkKkweorz0ry", "access_secret_81x7ByYkRpB4g05N");
		api.orderApi.edit(null);
	}
}
