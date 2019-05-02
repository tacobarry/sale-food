package com.taco.dextra.salefood.models;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import com.taco.dextra.salefood.composite.SandwichComposite;
import com.taco.dextra.salefood.interfaces.IProduct;
import com.taco.dextra.salefood.resources.repository.IngredientsRepository;
import com.taco.dextra.salefood.singletons.SequenceSingleton;

public class ItemCart implements IProduct {
	private int id;
	private String name;
	float value;
	private String message;

	private SandwichComposite product;
	private List<Integer> additionalIds = new ArrayList<Integer>();
	
	public ItemCart() {}
	
	public ItemCart(SandwichComposite prod) {
		this.product = prod;
	}

	public void setId(Integer id) {
		if (id == null) {
			this.id = SequenceSingleton.instance.getValue();
		} else {
			this.id = id;
		}
	}

	@Override
	public int getId() {
		return this.id;
	}

	@Override
	public String getName() {
		if (product != null) {
			this.name = product.getName();
		}
		return this.name;
	}
	
	public void setProduct(SandwichComposite prod) {
		this.product = prod;
	}
	
	public SandwichComposite getProduct() {
		return this.product;
	}
	
	public List<Integer> getAdditionalIds() {
		return this.additionalIds;
	}

	@Override
	public float getValue() {
		this.value = product.getValue();
		if (!this.additionalIds.isEmpty()) {
			Iterator it = this.additionalIds.iterator();
			while (it.hasNext()) {
				Integer ingredientId = (Integer) it.next();
				value += IngredientsRepository.instance.getIngredientMap().get(ingredientId).getValue();
			}
		}
		return value;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public ItemCart add(Integer ip) {
		if (this.additionalIds == null) {
			this.additionalIds = new ArrayList<Integer>();
		}
		this.additionalIds.add(ip);
		return this;
	}

	public void addAll(Collection<Integer> additionalIds) {
		if (this.additionalIds == null) {
			this.additionalIds = new ArrayList<Integer>();
		}
		this.additionalIds.addAll(additionalIds);
	}

}
