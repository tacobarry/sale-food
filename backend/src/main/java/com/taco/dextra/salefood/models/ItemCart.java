package com.taco.dextra.salefood.models;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.taco.dextra.salefood.composite.SandwichComposite;
import com.taco.dextra.salefood.interfaces.IProduct;
import com.taco.dextra.salefood.singletons.SequenceSingleton;

public class ItemCart implements IProduct {
	private int id;
	private String name;
	private float value;
	private String message;

	private SandwichComposite product;
	private List<IProduct> additionalList = new ArrayList<IProduct>();
	
	public ItemCart() {}
	
	public ItemCart(SandwichComposite prod) {
		this.product = prod;
	}

	public void setId() {
		this.id = SequenceSingleton.instance.getValue();
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
	
	public void setAdditionalList(List<IProduct> additionalList) {
		this.additionalList = new ArrayList<IProduct>();
		this.additionalList.addAll(additionalList);
	}
	
	public List<IProduct> getAdditionalList() {
		return this.additionalList;
	}

	@Override
	public float getValue() {
		this.value = product.getValue();
		Iterator it = additionalList.iterator();
		while (it.hasNext()) {
			IProduct product = (IProduct) it.next();
			this.value += product.getValue();
		}
		return value;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public ItemCart add(IProduct ip) {
		this.additionalList.add(ip);
		return this;
	}

}
