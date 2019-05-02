package com.taco.dextra.salefood.dto;

import java.util.Arrays;

import com.taco.dextra.salefood.interfaces.IProduct;
import com.taco.dextra.salefood.models.ItemCart;
import com.taco.dextra.salefood.resources.repository.SandwichRepository;

public class ItemCartDTO implements IProduct {

	private Integer id;
	private String name;
	private float value;
	private String message;
	private int productId;
	private Integer[] ingredientArray;

	public ItemCartDTO() {}

	public void setId(Integer id) {
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

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public Integer[] getIngredientArray() {
		return ingredientArray;
	}

	public void setIngredientArray(Integer[] ingredientArray) {
		this.ingredientArray = ingredientArray;
	}

	public ItemCart dtoToItemCart() {
		ItemCart ic = new ItemCart();
		ic.setId(this.id);
		ic.setProduct(SandwichRepository.instance.getSandwichMap().get(this.productId));
		ic.setMessage(this.message);
		if (this.ingredientArray != null && this.ingredientArray.length > 0) {
			ic.addAll(Arrays.asList(this.ingredientArray));
		}
		return ic;
	}
}
