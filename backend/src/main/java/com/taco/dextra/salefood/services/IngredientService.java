package com.taco.dextra.salefood.services;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.taco.dextra.salefood.models.Ingredient;
import com.taco.dextra.salefood.resources.IngredientResource;

@Service
public class IngredientService {

	@Autowired
	private IngredientResource iResource;

	public ResponseEntity<List<Ingredient>> getIngredients() {
		return this.iResource.findAll();
	}

	public ResponseEntity<List<Ingredient>> getIngredientsByArray(Integer[] idArray) {
		List<Ingredient> ingredientList = this.iResource.findAll().getBody();
		Iterator<Ingredient> it = ingredientList.iterator();
		Map<Integer, Ingredient> ingredientsMap = new HashMap<Integer, Ingredient>();
		while (it.hasNext()) {
			Ingredient i = it.next();
			ingredientsMap.put(i.getId(), i);
		}
		for (Integer id: idArray) {
			Ingredient ig = ingredientsMap.get(id);
			if (ig == null) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		}
		return new ResponseEntity<List<Ingredient>>(ingredientList, HttpStatus.OK);
	}
}
