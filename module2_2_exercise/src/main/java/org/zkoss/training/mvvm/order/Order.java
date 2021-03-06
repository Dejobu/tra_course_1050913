/* Item.java

	Purpose:
		
	Description:
		
	History:
		2011/10/25 Created by Dennis Chen

Copyright (C) 2011 Potix Corporation. All Rights Reserved.
 */
package org.zkoss.training.mvvm.order;

import java.util.Calendar;
import java.util.Date;

import org.zkoss.bind.annotation.DependsOn;

/**
 * @author dennis
 * 
 */
public class Order {

	String id;
	String description;
	Date creationDate;
	Date shippingDate;
	double price;
	int quantity;

	public Order() {
	}	
	
	Order(String id, String description, double price, int quantity, Date creationDate) {
		super();
		this.id = id;
		this.description = description;
		this.price = price;
		this.quantity = quantity;
		this.creationDate = creationDate;
		// default shipping date is 3 day after.
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, 3);
		shippingDate = cal.getTime();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public Date getShippingDate() {
		return shippingDate;
	}

	public void setShippingDate(Date shippingDate) {
		this.shippingDate = shippingDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@DependsOn( { "price", "quantity" })
	public Double getTotalPrice() {
		return price * quantity;
	}

}
