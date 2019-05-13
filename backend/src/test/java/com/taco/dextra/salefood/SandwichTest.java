package com.taco.dextra.salefood;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.springframework.http.HttpStatus;

import com.taco.dextra.salefood.composite.SandwichComposite;
import com.taco.dextra.salefood.models.Ingredient;

import io.restassured.mapper.TypeRef;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;

public class SandwichTest extends SaleFoodApplicationTests {

	@Test
	public void createSandwich() throws Exception {
		JSONObject sandwichJSON = (JSONObject) new JSONObject();
		Integer[] ingredientArray = new Integer[5];
		ingredientArray[0] = 1; // 0.4
		ingredientArray[1] = 2; // 2.0
		ingredientArray[2] = 3; // 3.0
		ingredientArray[3] = 4; // 0.8
		ingredientArray[4] = 5; // 1.5

		sandwichJSON.put("name", "X-tudo");
		sandwichJSON.put("ingredientArray", ingredientArray);

		String response = given()
			.contentType("application/json")
			.body(sandwichJSON.toString())
			.when()
			.post(super.getRootUrl()+"/sandwich")
			.then()
				.assertThat()
				.statusCode(201)
				.and()
				.extract()
				.response()
				.asString();
			JSONParser parser = new JSONParser();
			JSONObject json = (JSONObject) parser.parse(response);

			// value == 7.700001
			assertTrue((double) json.getAsNumber("value") - 7.7 < 0.001);

	}

	@Test
	public void valueOfDefaultSandwiches() throws Exception {
		String response = given()
				.contentType("application/json")
				.when()
				.get(super.getRootUrl()+"/sandwiches")
				.then()
					.assertThat()
					.statusCode(200)
					.and()
					.extract()
					.response()
					.asString();
		System.out.println(response);
		JSONParser parser = new JSONParser();
		JSONArray arr = (JSONArray) parser.parse(response);
		System.out.println(arr);
		System.out.println(arr.get(0));
	}

	@Test
	public void retriveXBacon() throws Exception {
		given()
		.get(super.getRootUrl()+"/sandwich/11")
		.then()
			.statusCode(HttpStatus.OK.value())
			.and()
			.body("name", equalTo("X-Bacon"))
			.body("id", equalTo(11));

	}

}
