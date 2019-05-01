package com.taco.dextra.salefood.resources;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.taco.dextra.salefood.models.Purchase;

@RestController
@RequestMapping(value="/api")
public class PurchaseResource {
	private Map<Integer, Purchase> purchaseMap;
	
	public PurchaseResource() {
		this.purchaseMap = new HashMap<Integer, Purchase>();
	}

	@PostMapping("/purchase")
	public ResponseEntity<Purchase> createPurchase() {
		return null;
	}
	
	@GetMapping("/purchases")
	public ResponseEntity<Purchase> getAllPurchases() {
		return null;
	}
	
	@GetMapping("/purchase/{id}")
	public ResponseEntity<Purchase> getPurchaseById(@PathVariable int id) {
		return null;
	}
	
	@PutMapping("/purchase/{id}")
	public void update(@PathVariable int id, @RequestBody Purchase purchase) {
	}
	
	@DeleteMapping("/purchase/{id}")
	public void delete(@PathVariable int id) {
	}
}
