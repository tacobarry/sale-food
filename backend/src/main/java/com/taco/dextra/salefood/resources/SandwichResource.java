package com.taco.dextra.salefood.resources;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.taco.dextra.salefood.composite.SandwichComposite;

public class SandwichResource {

	private Map<Integer, SandwichComposite> sandwichMap;
	private IngredientResource ingredientResource = new IngredientResource();
	
	public SandwichResource() {
		sandwichMap.put(1, 
			new SandwichComposite()
				.setId(1)
				.setName("X-Bacon")
				.add(this.ingredientResource.getIngredients().get(2))
				.add(this.ingredientResource.getIngredients().get(3))
				.add(this.ingredientResource.getIngredients().get(5))
		);
		sandwichMap.put(2, 
			new SandwichComposite()
				.setId(1)
				.setName("X-Burguer")
				.add(this.ingredientResource.getIngredients().get(3))
				.add(this.ingredientResource.getIngredients().get(5))
		);
		sandwichMap.put(3, 
			new SandwichComposite()
				.setId(1)
				.setName("X-Egg")
				.add(this.ingredientResource.getIngredients().get(4))
				.add(this.ingredientResource.getIngredients().get(3))
				.add(this.ingredientResource.getIngredients().get(5))
		);
		sandwichMap.put(4, 
			new SandwichComposite()
				.setId(1)
				.setName("X-Egg Bacon")
				.add(this.ingredientResource.getIngredients().get(4))
				.add(this.ingredientResource.getIngredients().get(2))
				.add(this.ingredientResource.getIngredients().get(3))
				.add(this.ingredientResource.getIngredients().get(5))
		);
	}
	
	@RequestMapping(value = "/sandwiches", method = RequestMethod.GET)
	public ResponseEntity<List<SandwichComposite>> list() {
		return new ResponseEntity<List<SandwichComposite>>(new ArrayList<SandwichComposite>(this.sandwichMap.values()), HttpStatus.OK);
	}
}
