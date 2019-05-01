package com.taco.dextra.salefood.resources.repository;

import java.util.HashMap;
import java.util.Map;

import com.taco.dextra.salefood.models.Ingredient;

public class IngredientsRepository {
	
	public static IngredientsRepository instance = new IngredientsRepository();

	private Map<Integer, Ingredient> ingredientMap;
	
	public IngredientsRepository() {
		this.ingredientMap = new HashMap<Integer, Ingredient>();
	}
	
	public IngredientsRepository add(Ingredient ingredient) {
		if (this.ingredientMap.containsKey(ingredient.getId())) {
			this.ingredientMap.replace(ingredient.getId(), ingredient);
		} else {
			this.ingredientMap.put(ingredient.getId(), ingredient);
		}
		return this;
	}
	
	public Map<Integer, Ingredient> getIngredientMap() {
		return this.ingredientMap;
	}
	
	public Ingredient getIngredient(Integer id) {
		return this.ingredientMap.get(id);
	}
	
	public boolean remove(Integer id) {
		Ingredient i = this.ingredientMap.remove(id);
		if (i==null) {
			return false;
		}
		return true;
	}
}
