package org.zkoss.training.shop.model.bean;

import org.zkoss.bind.annotation.DependsOn;

public class CartItem {

	private Product product;
	private int amount;

	public CartItem(Product product) {
		super();
		this.product = product;
	}
	
	public CartItem(Product product, int amount) {
		super();
		this.product = product;
		this.amount = amount;
	}

	public Product getProduct() {
		return product;
	}

	public Integer getAmount() {
		return amount;
	}

	public void add(int amount) {
		this.amount += amount;
	}

	@DependsOn("amount")
	public float getSubTotal() {
		return product.getPrice() * amount;
	}
	
	public void setAmount(Integer amount){
		this.amount = amount;
	}
}
