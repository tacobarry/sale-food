package com.taco.dextra.salefood.resources;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.taco.dextra.salefood.composite.SandwichComposite;
import com.taco.dextra.salefood.dto.ItemCartDTO;
import com.taco.dextra.salefood.models.Ingredient;
import com.taco.dextra.salefood.models.ItemCart;
import com.taco.dextra.salefood.resources.repository.ItemCartRepository;
import com.taco.dextra.salefood.services.IngredientService;
import com.taco.dextra.salefood.services.SandwichService;

@CrossOrigin(origins = "*", allowedHeaders = "*", exposedHeaders = "Access-Control-Allow-Origin")
@RestController
@RequestMapping(value="/api")
public class ItemCartResource {
	
	@Autowired
	private IngredientService ingredientService;
	@Autowired
	private SandwichService sandwichService;

	public ItemCartResource() {}

	@RequestMapping(value = "/itemcart", method={RequestMethod.OPTIONS,RequestMethod.POST})
	public ResponseEntity<ItemCart> createItem(@RequestBody ItemCartDTO dto) {
		ItemCart icart = dto.dtoToItemCart();
		SandwichComposite sandwich = sandwichService.getSandwichById(dto.getProductId()).getBody();
		if (sandwich == null) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		if (dto.getIngredientArray() != null) {
			List<Ingredient> ingredientList = ingredientService.getIngredientsByArray(dto.getIngredientArray()).getBody();
			if (ingredientList == null) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
		}
		ItemCartRepository.instance.add(icart);
		return new ResponseEntity<ItemCart>(icart, HttpStatus.CREATED);
	}

	@GetMapping("/itemcarts")
	public ResponseEntity<List<ItemCart>> getAllItems() {
		return new ResponseEntity<List<ItemCart>>(
			new ArrayList<ItemCart>(ItemCartRepository.instance.getItemCartMap().values()),
			HttpStatus.OK
		);
	}

//	@RequestMapping(value = "/itemcarts", method={RequestMethod.OPTIONS,RequestMethod.GET})
//	public ResponseEntity<List<ItemCart>> getAllItensByArray(@RequestBody Integer[] idArray) {
//		Map<Integer, ItemCart> itemCartMap = ItemCartRepository.instance.getItemCartMap();
//		if (idArray.length == 0) {
//			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//		}
//		List<ItemCart> itemCartList = new ArrayList<ItemCart>();
//		for (Integer i: idArray) {
//			ItemCart ic = itemCartMap.get(i);
//			if (ic == null) {
//				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//			}
//			itemCartList.add(ic);
//		}
//		return new ResponseEntity<List<ItemCart>>(itemCartList, HttpStatus.OK);
//	}

	@GetMapping("/itemcart/{id}")
	public ResponseEntity<ItemCart> getItem(@PathVariable int id) {
		ItemCart icart = ItemCartRepository.instance.getItemCart(id);
		if (icart == null) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<ItemCart>(icart, HttpStatus.OK);
	}

	@PutMapping("/itemcart/{id}")
	public ResponseEntity<ItemCart> updateItem(@PathVariable("id") int id, @RequestBody ItemCartDTO itCartDTO) {
		ItemCart icart = ItemCartRepository.instance.getItemCart(id);
		if (icart == null) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		icart = itCartDTO.dtoToItemCart();
		ItemCartRepository.instance.add(icart);
		return new ResponseEntity<ItemCart>(icart, HttpStatus.OK);
	}

	@DeleteMapping("/itemcart/{id}")
	public ResponseEntity<Object> delete(@PathVariable int id) {
		boolean removedElem = ItemCartRepository.instance.remover(id);
		if (removedElem == false) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(HttpStatus.PERMANENT_REDIRECT);
	}
}
