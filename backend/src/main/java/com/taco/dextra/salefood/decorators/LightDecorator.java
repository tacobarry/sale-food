package com.taco.dextra.salefood.decorators;

import java.util.Iterator;

import com.taco.dextra.salefood.enumeration.IngredientEnum;
import com.taco.dextra.salefood.interfaces.IProduct;
import com.taco.dextra.salefood.models.ItemCart;
import com.taco.dextra.salefood.resources.repository.IngredientsRepository;

public class LightDecorator extends DiscountDecorator {

	public LightDecorator(ItemCart ip) {
		super(ip);
	}

	public float getDiscount() {
		return this.discount;
	}

	@Override
	public float getValue() {
		Iterator<Integer> it = super.additionalIds.iterator();
		boolean haveLetuce = false;
		boolean haveBacon = false;
		while (it.hasNext() || !haveBacon) {
			Integer ingredientId = it.next();
			IProduct product = IngredientsRepository.instance.getIngredientMap().get(ingredientId);
			if (!haveLetuce) {
				haveLetuce = (IngredientEnum.LETUCE.getId() == product.getId());
			}
			if (!haveBacon) {
				haveBacon = (IngredientEnum.BACON.getId() == product.getId());
			}
		}
		
		if (haveLetuce && !haveBacon) {
			this.value = super.value * 0.1f;
		}
		this.value = 0f;
		return this.value;
	}

}
