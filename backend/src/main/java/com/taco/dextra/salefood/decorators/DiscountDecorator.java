package com.taco.dextra.salefood.decorators;

import com.taco.dextra.salefood.models.ItemCart;

public abstract class DiscountDecorator extends ItemCart {
	
	public ItemCart itemCart;
	
	public float discount;
	
	public DiscountDecorator(ItemCart ic) {
		this.itemCart = ic;
		this.discount = 0f;
	}
	
	@Override
	public float getValue() {
		return (this.itemCart.getValue() + this.getValue());
	}

}
