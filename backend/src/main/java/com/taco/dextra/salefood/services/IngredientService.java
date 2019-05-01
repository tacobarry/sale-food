package com.taco.dextra.salefood.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taco.dextra.salefood.models.Ingredient;
import com.taco.dextra.salefood.resources.IngredientResource;

@Service
public class IngredientService {

	@Autowired
	private IngredientResource iResource;
	
	public List<Ingredient> getIngredients() {
		return this.iResource.findAll().getBody();	
	}
}
