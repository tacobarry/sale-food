package com.taco.dextra.salefood.resources;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.taco.dextra.salefood.dto.PurchaseDTO;
import com.taco.dextra.salefood.models.ItemCart;
import com.taco.dextra.salefood.models.Purchase;
import com.taco.dextra.salefood.resources.repository.PurchaseRepository;
import com.taco.dextra.salefood.services.ItemCartService;

@RestController
@RequestMapping(value="/api")
public class PurchaseResource {
	
	@Autowired
	ItemCartService itemCartService;

	public PurchaseResource() {}

	@PostMapping("/purchase")
	public ResponseEntity<Purchase> createPurchase(@RequestBody PurchaseDTO purchaseDTO) {
		Purchase purchase = purchaseDTO.dtoToPurchase();
		List<ItemCart> itemCartList = itemCartService.getAllItemCarts(purchaseDTO.getItemCartArr()).getBody();
		if (itemCartList.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		PurchaseRepository.instance.add(purchase);
		return new ResponseEntity<Purchase>(purchase, HttpStatus.CREATED);
	}

	@GetMapping("/purchases")
	public ResponseEntity<List<Purchase>> getAllPurchases() {
		return new ResponseEntity<List<Purchase>>(
			new ArrayList<Purchase>(PurchaseRepository.instance.getPurchaseMap().values()),
			HttpStatus.OK
		);
	}

	@GetMapping("/purchase/{id}")
	public ResponseEntity<Purchase> getPurchaseById(@PathVariable int id) {
		Purchase p = PurchaseRepository.instance.getPurchase(id);
		if (p == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Purchase>(p, HttpStatus.OK);
	}

	@PutMapping("/purchase/{id}")
	public ResponseEntity<Purchase> update(@PathVariable int id, @RequestBody PurchaseDTO purchaseDTO) {
		Purchase p = PurchaseRepository.instance.getPurchase(id);
		if (p == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		p = purchaseDTO.dtoToPurchase();
		PurchaseRepository.instance.add(p);
		return new ResponseEntity<Purchase>(p, HttpStatus.OK);
	}

	@DeleteMapping("/purchase/{id}")
	public ResponseEntity<Object> delete(@PathVariable int id) {
		boolean removedElem = PurchaseRepository.instance.remove(id);
		if (removedElem == false) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(HttpStatus.PERMANENT_REDIRECT);
	}
}
