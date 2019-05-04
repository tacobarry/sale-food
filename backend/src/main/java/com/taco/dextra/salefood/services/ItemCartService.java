package com.taco.dextra.salefood.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.taco.dextra.salefood.models.ItemCart;
import com.taco.dextra.salefood.resources.ItemCartResource;

@Service
public class ItemCartService {

	@Autowired
	private ItemCartResource itemcartResource;

	public ResponseEntity<List<ItemCart>> getAllItemCarts(Integer[] idArray) {
		return this.itemcartResource.getAllItensByArray(idArray);
	}
}
