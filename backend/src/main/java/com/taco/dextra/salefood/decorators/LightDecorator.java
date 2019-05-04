package com.taco.dextra.salefood.decorators;

import java.util.Iterator;

import com.taco.dextra.salefood.enumeration.IngredientEnum;
import com.taco.dextra.salefood.interfaces.IProduct;
import com.taco.dextra.salefood.models.Ingredient;
import com.taco.dextra.salefood.models.ItemCart;
import com.taco.dextra.salefood.resources.repository.IngredientsRepository;

public class LightDecorator extends DiscountDecorator {

	public LightDecorator(ItemCart ic) {
		super(ic);
	}

	public float getDiscount() {
		Iterator<Integer> it = itemCart.getAdditionalIds().iterator();
		boolean haveLetuce = false;
		boolean haveBacon = false;
		while (it.hasNext() && !haveBacon) {
			Integer ingredientId = it.next();
			IProduct product = IngredientsRepository.instance.getIngredientMap().get(ingredientId);
			if (!haveLetuce) {
				haveLetuce = (IngredientEnum.LETUCE.getId() == product.getId());
			}
			if (!haveBacon) {
				haveBacon = (IngredientEnum.BACON.getId() == product.getId());
			}
		}
		Iterator<Ingredient> itIng = itemCart.getProduct().getIngredients().iterator();
		
		while (itIng.hasNext() && !haveBacon) {
			Ingredient product = itIng.next();
			if (!haveLetuce) {
				haveLetuce = (IngredientEnum.LETUCE.getId() == product.getId());
			}
			if (!haveBacon) {
				haveBacon = (IngredientEnum.BACON.getId() == product.getId());
			}
		}
		
		if (haveLetuce && !haveBacon) {
			discount = itemCart.getValue() * 0.1f;
		}
		return discount;
	}

}
