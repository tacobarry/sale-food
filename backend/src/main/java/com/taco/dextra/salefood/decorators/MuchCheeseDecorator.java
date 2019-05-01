package com.taco.dextra.salefood.decorators;

import java.util.Iterator;
import java.util.List;

import com.taco.dextra.salefood.enumeration.IngredientEnum;
import com.taco.dextra.salefood.interfaces.IProduct;

public class MuchCheeseDecorator extends DiscountDecorator {
	public MuchCheeseDecorator(IProduct ip) {
		super(ip);
	}

	private static int QUANTITY_OF_CHEESE_FOR_DISCOUNT = 3;

	@Override
	public float getValue() {
		Iterator it = this.aditionalList.iterator();
		int countChese = 0;
		IProduct cheese = null;
		while (it.hasNext()) {
			IProduct product = (IProduct) it.next();
			if (product.getId() == IngredientEnum.CHEESE.getId()) {
				countChese++;
				cheese = product;
			}
		}
		if (cheese != null) {
			return (float) (this.value - cheese.getValue() * (Math.floor(new Float(countChese/QUANTITY_OF_CHEESE_FOR_DISCOUNT).doubleValue())));			
		} else {
			return this.value;
		}
	}

}
