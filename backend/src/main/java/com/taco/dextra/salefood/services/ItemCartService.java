package com.taco.dextra.salefood.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.taco.dextra.salefood.models.ItemCart;
import com.taco.dextra.salefood.resources.ItemCartResource;

@Service
public class ItemCartService {

	@Autowired
	private ItemCartResource itemcartResource;

	public ResponseEntity<List<ItemCart>> getAllItemCarts(Integer[] idArray) {
		List<ItemCart> itemCartList = this.itemcartResource.getAllItems().getBody();
		Map<Integer, ItemCart> itemCartMap = new HashMap<Integer, ItemCart>();
		for (ItemCart ic: itemCartList) {
			itemCartMap.put(ic.getId(), ic);
		}
		itemCartList = new ArrayList<ItemCart>();
		for (Integer i: idArray) {
			ItemCart ic = itemCartMap.get(i);
			if (ic == null) {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
			itemCartList.add(ic);
		}
		return new ResponseEntity<List<ItemCart>>(itemCartList, HttpStatus.OK);
	}
}
