package com.taco.dextra.salefood.decorators;

import java.util.Iterator;

import com.taco.dextra.salefood.enumeration.IngredientEnum;
import com.taco.dextra.salefood.interfaces.IProduct;
import com.taco.dextra.salefood.models.ItemCart;
import com.taco.dextra.salefood.resources.repository.IngredientsRepository;

public class MuchCheeseDecorator extends DiscountDecorator {
	private static int QUANTITY_OF_CHEESE_FOR_DISCOUNT = 3;

	public MuchCheeseDecorator(ItemCart ip) {
		super(ip);
	}

	public float getDiscount() {
		return this.discount;
	}

	@Override
	public float getValue() {
//		this.value = 0f;
		Iterator<Integer> it = super.additionalIds.iterator();
		int countChese = 0;
		IProduct cheese = null;
		while (it.hasNext()) {
			Integer ingredientId = it.next();
			IProduct product = IngredientsRepository.instance.getIngredientMap().get(ingredientId);
			if (product.getId() == IngredientEnum.CHEESE.getId()) {
				countChese++;
				cheese = product;
			}
		}
		if (cheese != null) {
			float discountValue = (float) (cheese.getValue() * (Math.floor(new Float( countChese / QUANTITY_OF_CHEESE_FOR_DISCOUNT).doubleValue())));
			this.value = discountValue;
			this.discount = discountValue;
		}
		return this.value;
	}

}
