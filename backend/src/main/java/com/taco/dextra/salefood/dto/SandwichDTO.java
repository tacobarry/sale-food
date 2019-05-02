package com.taco.dextra.salefood.dto;

import java.util.Arrays;

import com.taco.dextra.salefood.composite.SandwichComposite;
import com.taco.dextra.salefood.interfaces.IProduct;

public class SandwichDTO implements IProduct {
	
	private int id;
	private String name;
	private float value;
	private Integer[] ingredientArray;
	
	public SandwichDTO() {}
	
	public void setId(int id) {
		this.id = id;
	}

	@Override
	public int getId() {
		return this.id;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public float getValue() {
		return this.value;
	}

	public Integer[] getIngredientArray() {
		return ingredientArray;
	}

	public void setIngredientArray(Integer[] ingredientArray) {
		this.ingredientArray = ingredientArray;
	}
	
	public SandwichComposite dtoToSandwich() {
		SandwichComposite composite = new SandwichComposite();
		composite.setId(this.id);
		composite.setName(this.name);
		if (this.ingredientArray != null && this.ingredientArray.length > 0) {
			composite.addAll(Arrays.asList(this.ingredientArray));
		}
		return composite;
	}

}
