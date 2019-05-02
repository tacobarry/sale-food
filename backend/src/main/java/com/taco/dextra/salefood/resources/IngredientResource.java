package com.taco.dextra.salefood.resources;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.taco.dextra.salefood.enumeration.IngredientEnum;
import com.taco.dextra.salefood.models.Ingredient;
import com.taco.dextra.salefood.resources.repository.IngredientsRepository;

@RestController
@RequestMapping(value="/api")
public class IngredientResource {

	public IngredientResource() {
		IngredientsRepository.instance.add(new Ingredient(IngredientEnum.LETUCE.getId(), IngredientEnum.LETUCE.getName(), .4f));
		IngredientsRepository.instance.add(
			new Ingredient()
				.setId(IngredientEnum.BACON.getId())
				.setName(IngredientEnum.BACON.getName())
				.setValue(3f)
		);
		IngredientsRepository.instance.add(
			new Ingredient()
				.setId(IngredientEnum.HAMBURGUER.getId())
				.setName(IngredientEnum.HAMBURGUER.getName())
				.setValue(3f)
		);
		IngredientsRepository.instance.add(
			new Ingredient()
				.setId(IngredientEnum.EGG.getId())
				.setName(IngredientEnum.EGG.getName())
				.setValue(.8f)
		);
		IngredientsRepository.instance.add(
			new Ingredient()
				.setId(IngredientEnum.CHEESE.getId())
				.setName(IngredientEnum.CHEESE.getName())
				.setValue(1.5f)
		);
	}

	@RequestMapping(value = "/ingredients", method = RequestMethod.GET)
	public ResponseEntity<List<Ingredient>> findAll() {
		return new ResponseEntity<List<Ingredient>>(
			new ArrayList<Ingredient>(IngredientsRepository.instance.getIngredientMap().values()),
			HttpStatus.OK
		);
	}

	@RequestMapping(value = "/ingredients", method = RequestMethod.POST)
	@ResponseBody
	public List<String> createManyIngredients(@RequestBody Ingredient[] ingredientsArray) {
		List<String> response = new ArrayList<String>();
		for(Ingredient i: ingredientsArray) {
			IngredientsRepository.instance.add(i);
			response.add("Add Ingredient: " + i.toString());
		}
		return response;
	}

	@RequestMapping(value = "/ingredient/{id}", method = RequestMethod.GET)
	public ResponseEntity<Ingredient> searchById(@PathVariable("id") int id) {
		Ingredient ingredient = IngredientsRepository.instance.getIngredient(id);
		if (ingredient == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Ingredient>(ingredient, HttpStatus.OK);
	}

	@RequestMapping(value = "/ingredient/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deletar(@PathVariable("id") int id) {
		Boolean alreadyRemoved = IngredientsRepository.instance.remove(new Integer(id));
		if (!alreadyRemoved) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@PutMapping(path = "/ingredient/{id}", consumes = "application/json")
	public ResponseEntity<Ingredient> change(@PathVariable("id") int id, @RequestBody Ingredient ing) {
		Ingredient ingredient = IngredientsRepository.instance.getIngredient(id);
		if (ingredient == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		IngredientsRepository.instance.add(ing);
		return new ResponseEntity<Ingredient>(ing, HttpStatus.OK);
	}
}
