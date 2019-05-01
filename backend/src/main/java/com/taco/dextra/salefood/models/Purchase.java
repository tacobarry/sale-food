package com.taco.dextra.salefood.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import com.taco.dextra.salefood.decorators.LightDecorator;
import com.taco.dextra.salefood.decorators.MuchCheeseDecorator;
import com.taco.dextra.salefood.decorators.MuchHamburguerDecorator;
import com.taco.dextra.salefood.interfaces.IProduct;
import com.taco.dextra.salefood.singletons.SequenceSingleton;

public class Purchase {
	
	private int id;
	private float amount;
	private String name;
	private Date date;
	private boolean delivered;

	public void setId() {
		this.id = SequenceSingleton.instance.getValue();
	}
	
	public int getId() {
		return this.id;
	}

	private List<IProduct> itemCartList = new ArrayList<IProduct>();
	
	public List<IProduct> getItemcartList() {
		return this.itemCartList;
	}
	
	public Purchase add(IProduct item) {
		this.itemCartList.add(item);
		return this;
	}
	
	public float getAmount() {
		Iterator<IProduct> it = this.itemCartList.iterator();
		this.amount = 0f;
		while (it.hasNext()) {
			IProduct prod = it.next();
			prod = new MuchCheeseDecorator(prod);
			prod = new MuchHamburguerDecorator(prod);
			prod = new LightDecorator(prod);
			this.amount += prod.getValue();
		}
		return this.amount;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public boolean isDelivered() {
		return delivered;
	}

	public void setDelivered(boolean delivered) {
		this.delivered = delivered;
	}
}
