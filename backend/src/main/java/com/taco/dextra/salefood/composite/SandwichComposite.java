package com.taco.dextra.salefood.composite;

import java.util.ArrayList;
import java.util.List;

import org.springframework.lang.NonNull;

import com.taco.dextra.salefood.interfaces.IProduct;
import com.taco.dextra.salefood.models.Ingredient;

public class SandwichComposite implements IProduct{
	private int id;
	private String name;
	private float value;

	private List<Ingredient> ingredients;

	@Override
	public int getId() {
		return id;
	}

	@NonNull
	public SandwichComposite setId(int id) {
		this.id = id;
		return this;
	}

	@Override
	public String getName() {
		return name;
	}

	public SandwichComposite setName(String name) {
		this.name = name;
		return this;
	}

	@Override
	public float getValue() {
		float value = 0f;
		for (Ingredient ingredient: this.ingredients) {
			value += ingredient.getValue();
		}
		return value;
	}
	
	public SandwichComposite add(Ingredient ingredient) {
		if (this.ingredients == null) {
			this.ingredients = new ArrayList<Ingredient>();
		}
		this.ingredients.add(ingredient);
		return this;
	}
	
}
