/* OrderVM.java

	Purpose:
		
	Description:
		
	History:
		2011/10/31 Created by Dennis Chen

Copyright (C) 2011 Potix Corporation. All Rights Reserved.
 */
package org.zkoss.training.mvvm.order;

import java.util.Calendar;
import java.util.Date;

import org.zkoss.bind.ValidationContext;
import org.zkoss.bind.Validator;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.bind.validator.AbstractValidator;
import org.zkoss.zul.ListModelList;


/**
 * @author Hawk Chen
 * 
 */
public class OrderVM{
	//the order list
	ListModelList<Order> orders;
	
	//the selected order
	Order selected;

	public ListModelList<Order> getOrders() {
		if (orders == null) {
			//init the list
			orders = new ListModelList<Order>(getService().list());
		}
		return orders;
	}

	public Order getSelected() {
		return selected;
	}

	public void setSelected(Order selected) {
		this.selected = selected;
	}

	//action command
	
	@NotifyChange({"selected","orders"})
	@Command
	public void newOrder(){
		Order order = new Order();
		getOrders().add(order);
		selected = order;//select the new one
	}
	
	@NotifyChange("selected")
	@Command
	public void saveOrder(){
		getService().save(selected);
	}
	
	


	public OrderService getService() {
		return FakeOrderService.getInstance();
	}
	
	//validators for prompt
	public Validator getPriceValidator(){
		return new AbstractValidator(){
			public void validate(ValidationContext ctx) {
				Double price = (Double)ctx.getProperty().getValue();
				if(price==null || price<=0){
					addInvalidMessage(ctx, "must large than 0");
				}
			}
		};
	}
	
	public Validator getQuantityValidator(){
		return new AbstractValidator(){
			public void validate(ValidationContext ctx) {
				Integer quantity = (Integer)ctx.getProperty().getValue();
				if(quantity==null || quantity<=0){
					addInvalidMessage(ctx, "must large than 0");
				}
			}
		};
	}
	//validators for command
	public Validator getCreationDateValidator(){
		return new AbstractValidator(){
			public void validate(ValidationContext ctx) {
				Date creation = (Date)ctx.getProperty().getValue();
				if(creation==null){
					addInvalidMessage(ctx,"must not null");
				}
			}
		};
	}
	public Validator getShippingDateValidator(){
		return new AbstractValidator(){
			public void validate(ValidationContext ctx) {
				Date shipping = (Date)ctx.getProperty().getValue();//the main property
				Date creation = (Date)ctx.getProperties("creationDate")[0].getValue();//the collected
				//do mixed validation, shipping date have to large than creation more than 3 days.
				if(!CaldnearUtil.isDayAfter(creation,shipping,3)){
					addInvalidMessage(ctx,"must large than creation date at least 3 days");
				}
			}
		};
	}
	
	static class CaldnearUtil{
		static public boolean isDayAfter(Date date, Date laterDay , int day) {
			if(date==null) return false;
			if(laterDay==null) return false;
			
			Calendar cal = Calendar.getInstance();
			Calendar lc = Calendar.getInstance();
			
			cal.setTime(date);
			lc.setTime(laterDay);
			
			int cy = cal.get(Calendar.YEAR);
			int ly = lc.get(Calendar.YEAR);
			
			int cd = cal.get(Calendar.DAY_OF_YEAR);
			int ld = lc.get(Calendar.DAY_OF_YEAR);
			
			return (ly*365+ld)-(cy*365+cd) >= day; 
		}
	}
	//message for confirming the deletion.
	String deleteMessage;
	
	public String getDeleteMessage(){
		return deleteMessage;
	}
	
	@NotifyChange({"selected","orders","deleteMessage"})
	@Command
	public void deleteOrder(){
		getService().delete(selected);//delete selected
		getOrders().remove(selected);
		selected = null; //clean the selected
		deleteMessage = null;
	}
	
	@NotifyChange("deleteMessage")
	@Command
	public void confirmDelete(){
		//set the message to show to user
		deleteMessage = "Do you want to delete "+selected.getId()+" ?";
	}
	
	@NotifyChange("deleteMessage")
	@Command
	public void cancelDelete(){
		//clear the message
		deleteMessage = null;
	}
	
}
