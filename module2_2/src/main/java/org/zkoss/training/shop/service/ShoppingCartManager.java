package org.zkoss.training.shop.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.zkoss.training.shop.model.bean.CartItem;
import org.zkoss.training.shop.model.bean.Product;

/**
 * a service for accessing a database 
 * @author hawk
 *
 */
public class ShoppingCartManager {

	private Map<Long, CartItem> items = 
			Collections.synchronizedMap(new LinkedHashMap<Long, CartItem>());

	public List<CartItem> listItems() {
		return new ArrayList<CartItem>(items.values());
	}

	public CartItem getItem(long prodId) {
		return items.get(prodId);
	}

	public void add(CartItem newItem) {
		
		CartItem item = this.getItem(newItem.getProduct().getId());
		validate(item, newItem.getProduct(), newItem.getAmount());
		if (item == null) {
			items.put(newItem.getProduct().getId(), newItem);
		} else {
			item.add(newItem.getAmount());
		}
	}


	/**
	 * 
	 * @param item
	 *            could be null, if there's no corresponding cart item of this
	 *            add operation
	 * @param prod
	 *            the product which will be add to this cart
	 * @param amount
	 *            how many products user want to add to cart
	 * @throws OverQuantityException
	 *             throws while ( amount + cartitem.amount > product.amount)
	 */
	private static void validate(CartItem item, Product prod, int amount){
		int oriAmount = item == null ? 0 : item.getAmount();
		int total = oriAmount + amount;
		if (total > prod.getQuantity()) {
			String errMesg = "too much: " + oriAmount + " + " + amount + " > "
					+ prod.getQuantity();
			throw new RuntimeException(errMesg);
		}
	}

	public void remove(long id) {
		items.remove(id);
	}

	public void clear() {
		items.clear();
	}

	public float getTotalPrice() {
		float subTotal = 0;
		for (CartItem item : items.values()) {
			subTotal += item.getProduct().getPrice() * item.getAmount();
		}
		return subTotal;
	}

	public int size() {
		return items.size();
	}

}
