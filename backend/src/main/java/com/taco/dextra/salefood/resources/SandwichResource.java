package com.taco.dextra.salefood.resources;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.taco.dextra.salefood.composite.SandwichComposite;
import com.taco.dextra.salefood.dto.SandwichDTO;
import com.taco.dextra.salefood.enumeration.IngredientEnum;
import com.taco.dextra.salefood.resources.repository.IngredientsRepository;

@RestController
@RequestMapping(value="/api")
public class SandwichResource {

	private Map<Integer, SandwichComposite> sandwichMap;

	public SandwichResource() {
		sandwichMap = new HashMap<Integer, SandwichComposite>();

		sandwichMap.put(11, 
			new SandwichComposite()
				.setId(11)
				.setName("X-Bacon")
				.add(IngredientEnum.BACON.getId())
				.add(IngredientEnum.HAMBURGUER.getId())
				.add(IngredientEnum.CHEESE.getId())
		);
		sandwichMap.put(12, 
			new SandwichComposite()
				.setId(12)
				.setName("X-Burguer")
				.add(IngredientEnum.HAMBURGUER.getId())
				.add(IngredientEnum.CHEESE.getId())
		);
		sandwichMap.put(13, 
			new SandwichComposite()
				.setId(13)
				.setName("X-Egg")
				.add(IngredientEnum.EGG.getId())
				.add(IngredientEnum.HAMBURGUER.getId())
				.add(IngredientEnum.CHEESE.getId())
		);
		sandwichMap.put(14, 
			new SandwichComposite()
				.setId(14)
				.setName("X-Egg Bacon")
				.add(IngredientEnum.EGG.getId())
				.add(IngredientEnum.BACON.getId())
				.add(IngredientEnum.HAMBURGUER.getId())
				.add(IngredientEnum.CHEESE.getId())
		);
	}
	
	@PostMapping("/sandwich")
	public ResponseEntity<SandwichComposite> create(@RequestBody SandwichDTO dto) {
		SandwichComposite sc = dto.dtoToSandwich();
		this.sandwichMap.put(sc.getId(), sc);
		return new ResponseEntity<SandwichComposite>(sc, HttpStatus.CREATED);
	}

	@GetMapping("/sandwiches")
	public ResponseEntity<List<SandwichComposite>> findAll() {
		return new ResponseEntity<List<SandwichComposite>>(new ArrayList<SandwichComposite>(this.sandwichMap.values()), HttpStatus.OK);
	}

	@GetMapping("/sandwich/{id}")
	public ResponseEntity<SandwichComposite> getSandwichById(@PathVariable("id") int id) {
		SandwichComposite sandwich = this.sandwichMap.get(id);
		if (sandwich == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<SandwichComposite>(sandwich, HttpStatus.OK);
	}

	@PutMapping("/sandwich/{id}")
	public ResponseEntity<SandwichComposite> update(@PathVariable("id") int id, @RequestBody SandwichDTO dto) {
		SandwichComposite sandwich = this.sandwichMap.get(id);
		if (sandwich == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		sandwich = dto.dtoToSandwich();
		return new ResponseEntity<SandwichComposite>(sandwich, HttpStatus.OK);
	}

}
