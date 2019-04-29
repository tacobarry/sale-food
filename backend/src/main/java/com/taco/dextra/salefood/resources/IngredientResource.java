package com.taco.dextra.salefood.resources;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.taco.dextra.salefood.models.Ingredient;

@RestController
@RequestMapping(value="/api")
public class IngredientResource {

	private Map<Integer, Ingredient> ingredients;

	public Map<Integer, Ingredient> getIngredients() {
		return ingredients;
	}

	public IngredientResource() {
		ingredients = new HashMap<Integer, Ingredient>();

		ingredients.put(1, new Ingredient(1, "alface", .4f));
		ingredients.put(2,
			new Ingredient()
				.setId(2)
				.setName("bacon")
				.setValue(3f)
		);
		ingredients.put(3,
			new Ingredient()
				.setId(3)
				.setName("hambúrguer de carne")
				.setValue(3f)
		);
		ingredients.put(4,
			new Ingredient()
				.setId(4)
				.setName("ovo")
				.setValue(.8f)
		);
		ingredients.put(5,
			new Ingredient()
				.setId(5)
				.setName("queijo")
				.setValue(1.5f)
		);
	}

	@RequestMapping(value = "/ingredients", method = RequestMethod.GET)
	public ResponseEntity<List<Ingredient>> list() {
		return new ResponseEntity<List<Ingredient>>(new ArrayList<Ingredient>(this.ingredients.values()), HttpStatus.OK);
	}

	@RequestMapping(value = "/ingredient/{id}", method = RequestMethod.GET)
	public ResponseEntity<Ingredient> searchById(@PathVariable("id") Integer id) {
		Ingredient ingredient = ingredients.get(id);
		if (ingredient == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Ingredient>(ingredient, HttpStatus.OK);
	}

	@RequestMapping(value = "/ingredient/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deletar(@PathVariable("id") int id) {
		Ingredient ingredient = ingredients.remove(id);
		if (ingredient == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@PutMapping(path = "/ingredient/{id}", consumes = "application/json")
	public ResponseEntity<Ingredient> change(@PathVariable("id") Integer id, @RequestBody Ingredient ing) {
		Ingredient ingredient = ingredients.get(id);
		if (ingredient == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		ingredient = ing;
		return new ResponseEntity<Ingredient>(ingredient, HttpStatus.OK);
	}

//	@RequestMapping(value = "/ingredient/{id}", method = RequestMethod.PATCH)
	@PatchMapping(path = "/ingredient/{id}", consumes = "application/json")
	public ResponseEntity<Ingredient> change(@PathVariable("id") Integer id, @RequestBody Map<String, Float> value) {
		Ingredient ingredient = ingredients.get(id);
		if (ingredient == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		ingredient.setValue(value.get("value").floatValue());
		return new ResponseEntity<Ingredient>(ingredient, HttpStatus.OK);
	}
}
