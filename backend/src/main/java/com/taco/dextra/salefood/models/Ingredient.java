package com.taco.dextra.salefood.models;

import org.springframework.lang.NonNull;

import com.taco.dextra.salefood.interfaces.IProduct;

public class Ingredient implements IProduct {

	@NonNull
	private int id;
	@NonNull
	private String name;
	@NonNull
	private float value;

	public Ingredient() {}

	public Ingredient(int id, String name, float value) {
		this.id = id;
		this.name = name;
		this.value = value;
	}

	public Ingredient setId(int id) {
		this.id = id;
		return this;
	}

	@Override
	public int getId() {
		return this.id;
	}

	public Ingredient setName(String name) {
		this.name = name;
		return this;
	}

	@Override
	public String getName() {
		return this.name;
	}

	public Ingredient setValue(float value) {
		this.value = value;
		return this;
	}

	@Override
	public float getValue() {
		return this.value;
	}

}
