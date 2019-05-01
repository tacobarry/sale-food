package com.taco.dextra.salefood.enumeration;

public enum IngredientEnum {
	LETUCE(1, "alface"),
	BACON(2, "bacon"),
	HAMBURGUER(3, "hamburguer de carne"),
	EGG(4, "ovo"),
	CHEESE(5, "queijo");
	
	private final int id;
	private final String name;
	
	public int getId() {
		return this.id;
	}
	
	public String getName() {
		return this.name;
	}
	
	private IngredientEnum(int id, String name) {
		this.id = id;
		this.name = name;
	}

}
