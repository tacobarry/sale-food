package com.taco.dextra.salefood.models;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import com.taco.dextra.salefood.decorators.LightDecorator;
import com.taco.dextra.salefood.decorators.MuchCheeseDecorator;
import com.taco.dextra.salefood.decorators.MuchHamburguerDecorator;
import com.taco.dextra.salefood.interfaces.IProduct;
import com.taco.dextra.salefood.resources.repository.ItemCartRepository;
import com.taco.dextra.salefood.singletons.SequenceSingleton;

public class Purchase implements IProduct {
	
	private int id;
	private float value;
	private String where;
	private Date date;
	private boolean delivered;

	private List<Integer> itemCartList = new ArrayList<Integer>();

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

	@Override
	public float getValue() {
		Iterator<Integer> it = this.itemCartList.iterator();
		this.value = 0f;
		while (it.hasNext()) {
			Integer itemCartId = it.next();
			ItemCart prod = ItemCartRepository.instance.getItemCart(itemCartId);
			if (prod == null) {
				continue;
			}
			ItemCart lightDecor = new LightDecorator(prod);
			ItemCart cheeseDecor = new MuchCheeseDecorator(prod);
			ItemCart hambDecor = new MuchHamburguerDecorator(prod);
			this.value += prod.getValue() - cheeseDecor.getDiscount() - hambDecor.getDiscount() - lightDecor.getDiscount();
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

	@Override
	public String getName() {
		return this.where;
	}
}
