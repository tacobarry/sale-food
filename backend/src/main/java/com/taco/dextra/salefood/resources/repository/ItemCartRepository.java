package com.taco.dextra.salefood.resources.repository;

import java.util.HashMap;
import java.util.Map;

import com.taco.dextra.salefood.models.ItemCart;

public class ItemCartRepository {

	public static ItemCartRepository instance = new ItemCartRepository();

	private Map<Integer, ItemCart> itemCartMap;

	public ItemCartRepository() {
		this.itemCartMap = new HashMap<Integer, ItemCart>();
	}

	public ItemCartRepository add(ItemCart ic) {
		if (this.itemCartMap.containsKey(ic.getId())) {
			this.itemCartMap.replace(ic.getId(), ic);
		} else {
			this.itemCartMap.put(ic.getId(), ic);
		}
		return this;
	}

	public Map<Integer, ItemCart> getItemCartMap() {
		return this.itemCartMap;
	}

	public ItemCart getItemCart(Integer id) {
		return this.itemCartMap.get(id);
	}

	public boolean remover(Integer id) {
		ItemCart ic = this.itemCartMap.remove(id);
		if (ic == null) {
			return false;
		}
		return true;
	}

}
