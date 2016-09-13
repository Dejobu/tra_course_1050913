package org.zkoss.training.shop.controller;

import org.zkoss.bind.BindContext;
import org.zkoss.bind.BindUtils;
import org.zkoss.bind.Converter;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.GlobalCommand;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.training.shop.model.bean.CartItem;
import org.zkoss.training.shop.model.bean.Product;
import org.zkoss.training.shop.service.ShoppingCartManager;
import org.zkoss.zk.ui.Desktop;
import org.zkoss.zk.ui.Session;
import org.zkoss.zul.ListModel;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listcell;

public class ShoppingCartVM {

	private WorkbenchContext context;
	private CartItem selectedCartItem;
	private String totalFooter;
	private String comment = "Note for this order.";
	private boolean disabled;
	private Converter<String, Float, Listcell> subTotalConverter;
	private boolean editMode = false;
	private ListModelList<CartItem> cartModel;
	private Session session;
	public static final String ADD = "add";
	public static final String REMOVE = "remove";
	public static final String CLEAR = "clear";
	public static final String UPDATE = "update";
	private Product product=null;
	private int quantity=0;

	@Init
	public void init(@ContextParam(ContextType.DESKTOP) Desktop desktop) {
		session = desktop.getSession();
		context = WorkbenchContext.getInstance(desktop);
		cartModel = new ListModelList<CartItem>(getCartManager().listItems());
		refreshUIStatus(null);
	}

	//TODO pass Product, quantity
	public void addToCart() {
		getCartManager().add(new CartItem(product, quantity));
		//clear the model and add new items by getCartManager().listItems()
		refreshUIStatus(ShoppingCartVM.ADD);
	}

	@Command //default command name
	@NotifyChange("selectedCartItem")
	public void remove(@BindingParam("item") CartItem item) {
		getCartManager().remove(item.getProduct().getId());
		cartModel.remove(item);
		refreshUIStatus(ShoppingCartVM.REMOVE);
	}

	//specify command name
	@Command(CLEAR)
	//TODO notify  2 properties
	public void clear() {
		getCartManager().clear();
		cartModel.clear();
		comment = "";
		refreshUIStatus(ShoppingCartVM.CLEAR);
	}

	//TODO
	public void submitCart() {
		context.getOrderService().submitNewOrder(cartModel, comment);
		clear();
	}

	public Converter<String, Float, Listcell> getSubTotalConverter() {
		if (subTotalConverter == null) {
			subTotalConverter = new Converter<String, Float, Listcell>(){
				@Override
				public String coerceToUi(Float subtotal, Listcell cell, BindContext ctx) {
					return "$ " + String.format("%.2f", subtotal);
				}
				@Override
				public Float coerceToBean(String label, Listcell cell, BindContext ctx) {
					return null;
				}
			};
		}
		return subTotalConverter;
	}

	//TODO 
	public void edit(){
		editMode = !editMode;
	}
	
	public ShoppingCartManager getCartManager() {
		ShoppingCartManager cartManager = (ShoppingCartManager) session.getAttribute("ShoppingCartManager");
		if (cartManager == null) {
			session.setAttribute("ShoppingCartManager", cartManager = new ShoppingCartManager());
		}
		return cartManager;
	}
	
	private void refreshUIStatus(String status) {
		
		if (ShoppingCartVM.REMOVE.equals(status)
				|| ShoppingCartVM.CLEAR.equals(status)) {
			selectedCartItem = null;
		}
		totalFooter = "$ " + String.format("%.2f", getCartManager().getTotalPrice());
		disabled = getCartManager().size() == 0;
		
		BindUtils.postNotifyChange(null, null, this, "cartItemImage");
		BindUtils.postNotifyChange(null, null, this, "totalFooter");
		BindUtils.postNotifyChange(null, null, this, "disabled");
	}

	public ListModel<CartItem> getCartModel() {
		return cartModel;
	}

	public CartItem getSelectedCartItem() {
		return selectedCartItem;
	}

	public void setSelectedCartItem(CartItem selectedCartItem) {
		this.selectedCartItem = selectedCartItem;
	}

	public String getTotalFooter() {
		return totalFooter;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
	
	public boolean isDisabled() {
		return disabled;
	}

	public boolean isEditMode() {
		return editMode;
	}

	public void setEditMode(boolean editMode) {
		this.editMode = editMode;
	}

}
