package com.taco.dextra.salefood.resources;

import java.util.ArrayList;
import java.util.List;

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

import com.taco.dextra.salefood.dto.ItemCartDTO;
import com.taco.dextra.salefood.models.ItemCart;
import com.taco.dextra.salefood.resources.repository.ItemCartRepository;

@RestController
@RequestMapping(value="/api")
public class ItemCartResource {

	public ItemCartResource() {}

	@PostMapping("/itemcart")
	public ResponseEntity<ItemCart> createItem(@RequestBody ItemCartDTO dto) {
		ItemCart icart = dto.dtoToItemCart();
		ItemCartRepository.instance.add(icart);
		return new ResponseEntity<ItemCart>(icart, HttpStatus.CREATED);
	}

	@GetMapping("/itemscart")
	public ResponseEntity<List<ItemCart>> getAllItems() {
		return new ResponseEntity<List<ItemCart>>(
			new ArrayList<ItemCart>(ItemCartRepository.instance.getItemCartMap().values()),
			HttpStatus.OK
		);
	}

	@GetMapping("/itemcart/{id}")
	public ResponseEntity<ItemCart> getItem(@PathVariable int id) {
		ItemCart icart = ItemCartRepository.instance.getItemCart(id);
		if (icart == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<ItemCart>(icart, HttpStatus.OK);
	}

	@PutMapping("/itemcart/{id}")
	public ResponseEntity<ItemCart> updateItem(@PathVariable("id") int id, @RequestBody ItemCartDTO itCartDTO) {
		ItemCart icart = ItemCartRepository.instance.getItemCart(id);
		if (icart == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		icart = itCartDTO.dtoToItemCart();
		ItemCartRepository.instance.add(icart);
		return new ResponseEntity<ItemCart>(icart, HttpStatus.OK);
	}

	@DeleteMapping("/itemcart/{id}")
	public ResponseEntity<Object> delete(@PathVariable int id) {
		boolean removedElem = ItemCartRepository.instance.remover(id);
		if (removedElem == false) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(HttpStatus.PERMANENT_REDIRECT);
	}
}
