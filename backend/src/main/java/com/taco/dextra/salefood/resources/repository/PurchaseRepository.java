package com.taco.dextra.salefood.resources.repository;

import java.util.HashMap;
import java.util.Map;

import com.taco.dextra.salefood.models.Purchase;

public class PurchaseRepository {

	public static PurchaseRepository instance = new PurchaseRepository();

	public Map<Integer, Purchase> purchaseMap;

	public PurchaseRepository() {
		this.purchaseMap = new HashMap<Integer, Purchase>();
	}

	public PurchaseRepository add(Purchase purchase) {
		if (this.purchaseMap.containsKey(purchase.getId())) {
			this.purchaseMap.replace(purchase.getId(), purchase);
		}
		this.purchaseMap.put(purchase.getId(), purchase);
		return this;
	}

	public Map<Integer, Purchase> getPurchaseMap() {
		return this.purchaseMap;
	}

	public Purchase getPurchase(Integer id) {
		return this.purchaseMap.get(id);
	}

	public boolean remove(Integer id) {
		Purchase p = this.purchaseMap.remove(id);
		if (p == null) {
			return false;
		}
		return true;
	}

}
