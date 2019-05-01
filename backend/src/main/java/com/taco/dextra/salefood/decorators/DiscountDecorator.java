package com.taco.dextra.salefood.decorators;

import java.util.List;

import com.taco.dextra.salefood.interfaces.IProduct;

public abstract class DiscountDecorator implements IProduct {
	protected int id;
	protected String name;
	protected float value;
	
	protected IProduct product;
	
	protected List<IProduct> aditionalList;
	
	public DiscountDecorator(IProduct ip) {
		this.product = ip;
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
	
	public IProduct add(IProduct ip) {
		this.aditionalList.add(ip);
		return this;
	}

}
