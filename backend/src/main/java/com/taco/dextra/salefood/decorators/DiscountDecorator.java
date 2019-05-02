package com.taco.dextra.salefood.decorators;

import java.util.ArrayList;
import java.util.List;

import com.taco.dextra.salefood.interfaces.IProduct;
import com.taco.dextra.salefood.models.ItemCart;

public abstract class DiscountDecorator extends ItemCart implements IProduct {
	protected int id;
	protected String name;
	protected float value;
	protected float discount;
	
	public float getDiscount() {
		return discount;
	}

	protected IProduct product;
	
	protected List<Integer> additionalIds = new ArrayList<Integer>();
	
	public DiscountDecorator(ItemCart ip) {
		this.value = ip.getValue();
		this.name = ip.getName();
		this.value = ip.getValue();
		this.product = ip.getProduct();
		this.additionalIds = ip.getAdditionalIds();
	}

	public IProduct setId(int id) {
		this.id = id;
		return this;
	}

	@Override
	public int getId() {
		return this.id;
	}

	public IProduct setName(String name) {
		this.name = name;
		return this;
	}

	@Override
	public String getName() {
		return this.name;
	}

	public IProduct setValue(float value) {
		this.value = value;
		return this;
	}

	@Override
	public abstract float getValue();
	
	public DiscountDecorator add(Integer ingredientId) {
		this.additionalIds.add(ingredientId);
		return this;
	}

}
