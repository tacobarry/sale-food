package com.taco.dextra.salefood.dto;

import java.util.Arrays;
import java.util.Date;

import com.taco.dextra.salefood.models.Purchase;

public class PurchaseDTO {

	private String where;
	private Date date;
	private boolean delivered;

	private Integer[] itemCartArr;

	public String getWhere() {
		return where;
	}

	public Date getDate() {
		return date;
	}

	public boolean isDelivered() {
		return delivered;
	}

	public Integer[] getItemCartArr() {
		return itemCartArr;
	}

	public void setWhere(String where) {
		this.where = where;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public void setDelivered(boolean delivered) {
		this.delivered = delivered;
	}

	public void setItemCartArr(Integer[] itemCartArr) {
		this.itemCartArr = itemCartArr;
	}

	public Purchase dtoToPurchase() {
		Purchase purchase = new Purchase();
		purchase.setDate(this.date);
		purchase.setDelivered(this.delivered);
		purchase.setWhere(this.where);
		if (this.itemCartArr != null && this.itemCartArr.length > 0) {
			purchase.addAll(Arrays.asList(this.itemCartArr));
		}
		System.out.println(purchase);
		return purchase;
	}
}
