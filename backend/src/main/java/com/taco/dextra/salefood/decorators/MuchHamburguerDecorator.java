package com.taco.dextra.salefood.decorators;

import java.util.Iterator;

import com.taco.dextra.salefood.enumeration.IngredientEnum;
import com.taco.dextra.salefood.interfaces.IProduct;

public class MuchHamburguerDecorator extends DiscountDecorator {
	public MuchHamburguerDecorator(IProduct ip) {
		super(ip);
	}

	private static int QUANTITY_OF_HAMBURGUER_FOR_DISCOUNT = 3;

	@Override
	public float getValue() {
		Iterator it = this.aditionalList.iterator();
		int countHamburguer = 0;
		IProduct hamburguer = null;
		while (it.hasNext()) {
			IProduct product = (IProduct) it.next();
			if (product.getId() == IngredientEnum.HAMBURGUER.getId()) {
				countHamburguer++;
				hamburguer = product;
			}
		}
		if (hamburguer != null) {
			return (float) (this.value - hamburguer.getValue() * (Math.floor(new Float(countHamburguer/QUANTITY_OF_HAMBURGUER_FOR_DISCOUNT).doubleValue())));			
		} else {
			return this.value;
		}
	}

}
