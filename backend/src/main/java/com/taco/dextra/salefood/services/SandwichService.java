package com.taco.dextra.salefood.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.taco.dextra.salefood.composite.SandwichComposite;
import com.taco.dextra.salefood.resources.SandwichResource;

@Service
public class SandwichService {

	@Autowired
	private SandwichResource sandwichResource;

	public ResponseEntity<SandwichComposite> getSandwichById(Integer id) {
		return sandwichResource.getSandwichById(id);
	}
}
