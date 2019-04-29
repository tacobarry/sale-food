package com.taco.dextra.salefood;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.Test;
import org.springframework.http.HttpStatus;

import com.taco.dextra.salefood.models.Ingredient;

import io.restassured.mapper.TypeRef;

public class IngredientsTest extends SaleFoodApplicationTests {

	@Test
	public void getIngredientsStatus200() throws Exception {
		given()
		.get(super.getRootUrl()+"/ingredients")
		.then()
			.statusCode(HttpStatus.OK.value());
	}
	
	@Test
	public void getAllIngredients() throws Exception {
		List<Ingredient> ingredients = given().get(super.getRootUrl()+"/ingredients").as(new TypeRef<List<Ingredient>>() {});
		
		assertThat(ingredients.size(), equalTo(5));

		assertThat(ingredients.get(0).getId(), equalTo(1));
		assertThat(ingredients.get(0).getName(), equalTo("alface"));
		assertThat(ingredients.get(0).getValue(), equalTo(.4f));
	}
	
	@Test
	public void getBacon() throws Exception {
		given()
		.get(super.getRootUrl()+"/ingredient/2")
		.then()
			.statusCode(HttpStatus.OK.value())
			.and()
			.body("name", equalTo("bacon"))
			.body("id", equalTo(2));
	}
	
	@Test
	public void get404() throws Exception {
		given()
		.get(super.getRootUrl()+"/ingredient/999")
		.then()
			.statusCode(HttpStatus.NOT_FOUND.value());
	}
}
