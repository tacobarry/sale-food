package com.taco.dextra.salefood.resources.repository;

import java.util.HashMap;
import java.util.Map;

import com.taco.dextra.salefood.composite.SandwichComposite;

public class SandwichRepository {

	public static SandwichRepository instance = new SandwichRepository();

	private Map<Integer, SandwichComposite> sandwichMap;

	public SandwichRepository() {
		this.sandwichMap = new HashMap<Integer, SandwichComposite>();
	}

	public SandwichRepository add(SandwichComposite sandwich) {
		if (this.sandwichMap.containsKey(sandwich.getId())) {
			this.sandwichMap.replace(sandwich.getId(), sandwich);
		} else {
			this.sandwichMap.put(sandwich.getId(), sandwich);
		}
		return this;
	}

	public Map<Integer, SandwichComposite> getSandwichMap() {
		return this.sandwichMap;
	}

	public SandwichComposite getSandwich(Integer id) {
		return this.sandwichMap.get(id);
	}

	public boolean remove(Integer id) {
		SandwichComposite sandwich = this.sandwichMap.remove(id);
		if (sandwich == null) {
			return false;
		}
		return true;
	}
}
