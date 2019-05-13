package com.taco.dextra.salefood.composite;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.springframework.lang.NonNull;

import com.taco.dextra.salefood.interfaces.IProduct;
import com.taco.dextra.salefood.models.Ingredient;
import com.taco.dextra.salefood.resources.repository.IngredientsRepository;

public class SandwichComposite implements IProduct {
	private int id;
	private String name;
	private float value;

	private List<Integer> ingredientsId;

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
		if (!this.ingredientsId.isEmpty()) {
			Iterator it = IngredientsRepository.instance.getIngredientMap().values().iterator();
			while (it.hasNext()) {
				Ingredient ingredient = (Ingredient) it.next();
				if (this.ingredientsId.contains(ingredient.getId())) {
					value += ingredient.getValue();
				}
			}
		}
		return value;
	}

	public SandwichComposite add(Integer id) {
		if (this.ingredientsId == null) {
			this.ingredientsId = new ArrayList<Integer>();
		}
		this.ingredientsId.add(id);
		return this;
	}
	
	public void addAll(Collection<Integer> ingredientsId) {
		if (this.ingredientsId == null) {
			this.ingredientsId = new ArrayList<Integer>();
		}
		this.ingredientsId.addAll(ingredientsId);
	}

	public List<Ingredient> getIngredients() {
		List<Ingredient> ingredientList = new ArrayList<Ingredient>();
		if (this.ingredientsId != null) {
			Iterator it = this.ingredientsId.iterator();
			while (it.hasNext()) {
				Integer ingredientId = (Integer) it.next();
				Ingredient ingredient = IngredientsRepository.instance.getIngredientMap().get(ingredientId);
				if (ingredient != null) {
					ingredientList.add(ingredient);
				}
			}
			return ingredientList;
		}
		return null;
	}

	public String toString() {
		return "[" + this.id + ": { name: " + this.name + ", value: " + this.value + "} ]";
	}
}
