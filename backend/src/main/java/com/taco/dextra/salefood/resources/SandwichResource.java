package com.taco.dextra.salefood.resources;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.taco.dextra.salefood.composite.SandwichComposite;
import com.taco.dextra.salefood.dto.SandwichDTO;
import com.taco.dextra.salefood.enumeration.IngredientEnum;
import com.taco.dextra.salefood.models.Ingredient;
import com.taco.dextra.salefood.resources.repository.SandwichRepository;
import com.taco.dextra.salefood.services.IngredientService;

@CrossOrigin(origins = "*", allowedHeaders = "*", exposedHeaders = "Access-Control-Allow-Origin")
@RestController
@RequestMapping(value="/api")
public class SandwichResource {
	
	@Autowired
	private IngredientService ingredientService;

	public SandwichResource() {
		SandwichRepository.instance.add( 
			new SandwichComposite()
				.setId(11)
				.setName("X-Bacon")
				.add(IngredientEnum.BACON.getId())
				.add(IngredientEnum.HAMBURGUER.getId())
				.add(IngredientEnum.CHEESE.getId())
		);
		SandwichRepository.instance.add(
			new SandwichComposite()
				.setId(12)
				.setName("X-Burguer")
				.add(IngredientEnum.HAMBURGUER.getId())
				.add(IngredientEnum.CHEESE.getId())
		);
		SandwichRepository.instance.add(
			new SandwichComposite()
				.setId(13)
				.setName("X-Egg")
				.add(IngredientEnum.EGG.getId())
				.add(IngredientEnum.HAMBURGUER.getId())
				.add(IngredientEnum.CHEESE.getId())
		);
		SandwichRepository.instance.add(
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
		List<Ingredient> ingredientList = ingredientService.getIngredientsByArray(dto.getIngredientArray()).getBody();
		if (ingredientList == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		SandwichRepository.instance.add(sc);
		return new ResponseEntity<SandwichComposite>(sc, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/sandwiches", method={RequestMethod.OPTIONS,RequestMethod.GET})
	public ResponseEntity<List<SandwichComposite>> findAll() {
		return new ResponseEntity<List<SandwichComposite>>(
			new ArrayList<SandwichComposite>(SandwichRepository.instance.getSandwichMap().values()),
			HttpStatus.OK
		);
	}

	@GetMapping("/sandwich/{id}")
	public ResponseEntity<SandwichComposite> getSandwichById(@PathVariable("id") int id) {
		SandwichComposite sandwich = SandwichRepository.instance.getSandwichMap().get(id);
		if (sandwich == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<SandwichComposite>(sandwich, HttpStatus.OK);
	}

	@PutMapping("/sandwich/{id}")
	public ResponseEntity<SandwichComposite> update(@PathVariable("id") int id, @RequestBody SandwichDTO dto) {
		SandwichComposite sandwich = SandwichRepository.instance.getSandwichMap().get(id);
		if (sandwich == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		sandwich = dto.dtoToSandwich();
		return new ResponseEntity<SandwichComposite>(sandwich, HttpStatus.OK);
	}

}
