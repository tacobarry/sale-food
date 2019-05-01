package com.taco.dextra.salefood.resources;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.taco.dextra.salefood.composite.SandwichComposite;
import com.taco.dextra.salefood.models.ItemCart;

@RestController
@RequestMapping(value="/api")
public class ItemCartResource {
	private Map<Integer, ItemCart> itemCartMap;
	
	public ItemCartResource() {
		this.itemCartMap = new HashMap<Integer, ItemCart>();
	}
	
	@PostMapping("/itemcart")
	public ResponseEntity<ItemCart> createItem(@RequestBody SandwichComposite prod) {
		if (this.itemCartMap == null) {
			this.itemCartMap = new HashMap<Integer, ItemCart>();
		}
		ItemCart icart = new ItemCart(prod);
		itemCartMap.put(icart.getId(), icart);
		return new ResponseEntity<ItemCart>(icart, HttpStatus.CREATED);
	}
	
	@GetMapping("/itemscart")
	public ResponseEntity<List<ItemCart>> getAllItems() {
		return new ResponseEntity<List<ItemCart>>(new ArrayList<ItemCart>(this.itemCartMap.values()), HttpStatus.OK);
	}

	@GetMapping("/itemcart/{id}")
	public ResponseEntity<ItemCart> getItem(@PathVariable int id) {
		ItemCart icart = itemCartMap.get(id);
		if (icart == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<ItemCart>(icart, HttpStatus.OK);
	}
	
	@PutMapping("/itemcart/{id}")
	public ResponseEntity<ItemCart> updateItem(@PathVariable("id") int id, @RequestBody ItemCart itCart) {
		ItemCart icart = itemCartMap.get(id);
		if (icart == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		icart.setProduct(icart.getProduct());
		icart.setAdditionalList(icart.getAdditionalList());
		
		return new ResponseEntity<ItemCart>(itCart, HttpStatus.OK);
	}
	
	@DeleteMapping("/itemcart/{id}")
	public ResponseEntity<Object> delete(@PathVariable int id) {
		ItemCart icart = itemCartMap.get(id);
		if (icart == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(HttpStatus.PERMANENT_REDIRECT);
	}
}
