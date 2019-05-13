package com.taco.dextra.salefood;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import org.hamcrest.collection.IsArray;
import org.junit.Test;

import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;

public class ItemCartTest extends SaleFoodApplicationTests {

	@Test
	public void createItemCart() throws Exception {
		JSONObject itemcartJSON = (JSONObject) new JSONObject();
		Integer[] ingredientArray = new Integer[5];
		ingredientArray[0] = 5; // 0.4
		ingredientArray[1] = 5; // 2.0
		ingredientArray[2] = 5; // 3.0
		ingredientArray[3] = 5; // 0.8
		ingredientArray[4] = 5; // 1.5
		/*{
	"message": "muito queijo",
	"productId": "11",
	"ingredientArray": [5, 5, 5, 5, 5, 5]*/
	
		itemcartJSON.put("message", "muito queijo");
		itemcartJSON.put("productId", "11");
		itemcartJSON.put("ingredientArray", ingredientArray);

		String response = given()
			.contentType("application/json")
			.body(itemcartJSON.toString())
			.when()
			.post(super.getRootUrl()+"/itemcart")
			.then()
				.assertThat()
				.statusCode(201)
				.and()
				.extract()
				.response()
				.asString();

		JSONParser parser = new JSONParser();
		JSONObject json = (JSONObject) parser.parse(response);
		JSONArray arr = (JSONArray) json.get("additionalIds");
		for (int i=0; i < arr.size(); i++) {
			assertEquals(arr.get(i), ingredientArray[i]);
		}
	}
}
