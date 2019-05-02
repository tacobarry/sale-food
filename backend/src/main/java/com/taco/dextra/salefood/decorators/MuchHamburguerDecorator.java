package com.taco.dextra.salefood.decorators;

import java.util.Iterator;

import com.taco.dextra.salefood.enumeration.IngredientEnum;
import com.taco.dextra.salefood.interfaces.IProduct;
import com.taco.dextra.salefood.models.ItemCart;
import com.taco.dextra.salefood.resources.repository.IngredientsRepository;

public class MuchHamburguerDecorator extends DiscountDecorator {
	private static int QUANTITY_OF_HAMBURGUER_FOR_DISCOUNT = 3;

	public MuchHamburguerDecorator(ItemCart ip) {
		super(ip);
	}

	public float getDiscount() {
		return this.discount;
	}

	@Override
	public float getValue() {
//		this.value = 0f;
		Iterator<Integer> it = super.additionalIds.iterator();
		int countHamburguer = 0;
		IProduct hamburguer = null;
		while (it.hasNext()) {
			Integer ingredientId = it.next();
			IProduct product = IngredientsRepository.instance.getIngredientMap().get(ingredientId);
			this.value += product.getValue();
			if (product.getId() == IngredientEnum.HAMBURGUER.getId()) {
				countHamburguer++;
				hamburguer = product;
			}
		}
		if (hamburguer != null) {
			float discountValue = (float) (hamburguer.getValue() * (Math.floor(new Float(countHamburguer/QUANTITY_OF_HAMBURGUER_FOR_DISCOUNT).doubleValue())));
			this.value = (float) ( discountValue);
			this.discount = discountValue;
		}
		return this.value;
	}

}
