package com.taco.dextra.salefood.models;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import com.taco.dextra.salefood.decorators.DiscountDecorator;
import com.taco.dextra.salefood.decorators.LightDecorator;
import com.taco.dextra.salefood.decorators.MuchCheeseDecorator;
import com.taco.dextra.salefood.decorators.MuchHamburguerDecorator;
import com.taco.dextra.salefood.interfaces.IProduct;
import com.taco.dextra.salefood.resources.repository.ItemCartRepository;
import com.taco.dextra.salefood.singletons.SequenceSingleton;

public class Purchase {
	
	private int id;
	private float value;
	private String where;
	private Date date;
	private boolean delivered;

	private List<Integer> itemCartList = new ArrayList<Integer>();

	public void setId() {
		this.id = SequenceSingleton.instance.getValue();
	}
	
	public int getId() {
		return this.id;
	}
	
	public List<Integer> getItemcartList() {
		return this.itemCartList;
	}
	
	public Purchase add(Integer item) {
		if (this.itemCartList == null) {
			this.itemCartList = new ArrayList<Integer>();
		}
		this.itemCartList.add(item);
		return this;
	}
	
	public void addAll(Collection<Integer> idsList) {
		if (this.itemCartList == null) {
			this.itemCartList = new ArrayList<Integer>();
		}
		this.itemCartList.addAll(idsList);
	}

	public float getValue() {
		Iterator<Integer> it = this.itemCartList.iterator();
		this.value = 0f;
		while (it.hasNext()) {
			Integer itemCartId = it.next();
			ItemCart prod = ItemCartRepository.instance.getItemCart(itemCartId);
			if (prod == null) {
				continue;
			}
//			System.out.println("prod 1:" + prod.getValue());
			MuchCheeseDecorator cheese = new MuchCheeseDecorator(prod);
			System.out.println("prod 2:" + cheese.getValue());
			MuchHamburguerDecorator hambDiscount = new MuchHamburguerDecorator(prod);
			System.out.println("prod 3:" +  hambDiscount.getValue());
			LightDecorator light = new LightDecorator(prod);
			this.value += prod.getValue() - cheese.getValue() - hambDiscount.getValue() - light.getValue();
			System.out.println("prod 4:" + this.value);
		}
		return this.value;
	}

	public String getWhere() {
		return where;
	}

	public void setWhere(String where) {
		this.where = where;
	}

	public Date getDate() {
		if (this.date == null) {
			this.date = new Date(System.currentTimeMillis());
		}
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
