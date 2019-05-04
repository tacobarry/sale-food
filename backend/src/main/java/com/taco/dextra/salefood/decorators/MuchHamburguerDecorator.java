package com.taco.dextra.salefood.decorators;

import java.util.Iterator;

import com.taco.dextra.salefood.enumeration.IngredientEnum;
import com.taco.dextra.salefood.interfaces.IProduct;
import com.taco.dextra.salefood.models.Ingredient;
import com.taco.dextra.salefood.models.ItemCart;
import com.taco.dextra.salefood.resources.repository.IngredientsRepository;

public class MuchHamburguerDecorator extends DiscountDecorator {
	private static int QUANTITY_OF_HAMBURGUER_FOR_DISCOUNT = 3;

	public MuchHamburguerDecorator(ItemCart ic) {
		super(ic);
	}

	public float getDiscount() {
		Iterator<Integer> it = itemCart.getAdditionalIds().iterator();
		int countHamburguer = 0;
		IProduct hamburguer = null;
		while (it.hasNext()) {
			Integer ingredientId = it.next();
			IProduct product = IngredientsRepository.instance.getIngredientMap().get(ingredientId);
			if (product.getId() == IngredientEnum.HAMBURGUER.getId()) {
				countHamburguer++;
				hamburguer = product;
			}
		}
		Iterator<Ingredient> itIng = itemCart.getProduct().getIngredients().iterator();
		while (itIng.hasNext()) {
			Ingredient product = itIng.next();
			if(product.getId() == IngredientEnum.HAMBURGUER.getId()) {
				countHamburguer++;
				hamburguer = product;
			}
		}
		if (hamburguer != null) {
			discount = (float) (hamburguer.getValue() * (Math.floor(new Float(countHamburguer/QUANTITY_OF_HAMBURGUER_FOR_DISCOUNT).doubleValue())));
		}
		return discount;
	}

}
