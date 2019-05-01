package com.taco.dextra.salefood.decorators;

import java.util.Iterator;

import com.taco.dextra.salefood.enumeration.IngredientEnum;
import com.taco.dextra.salefood.interfaces.IProduct;

public class LightDecorator extends DiscountDecorator {

	public LightDecorator(IProduct ip) {
		super(ip);
	}

	@Override
	public float getValue() {
		Iterator it = super.aditionalList.iterator();
		boolean haveLetuce = false;
		boolean haveBacon = false;
		while (it.hasNext() || !haveBacon) {
			IProduct product = (IProduct) it.next();
			if (!haveLetuce) {
				haveLetuce = (IngredientEnum.LETUCE.getId() == product.getId());
			}
			if (!haveBacon) {
				haveBacon = (IngredientEnum.BACON.getId() == product.getId());
			}
		}
		
		return (float) ((haveLetuce && !haveBacon) ? (0.9 * super.value) : super.value);
	}

}
