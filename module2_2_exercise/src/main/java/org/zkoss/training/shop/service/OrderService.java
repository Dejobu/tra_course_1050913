package org.zkoss.training.shop.service;

import java.util.List;

import org.zkoss.training.shop.model.OrderDAO;
import org.zkoss.training.shop.model.bean.CartItem;
import org.zkoss.training.shop.model.bean.Order;
import org.zkoss.zk.ui.Session;
import org.zkoss.zul.ListModel;
import org.zkoss.zul.ListModelList;

/**
 * session scoped bean to perform ordering logic and maintain a order list  
 * @author hawk
 *
 */
public class OrderService {

	private final Session session;
	private final OrderDAO orderDAO;
	private ListModelList<Order> orderModel;

	public OrderService(Session session) {
		this.session = session;
		orderDAO = new OrderDAO();
	}

	public Order submitNewOrder(List<CartItem> items, String comment) {
		Order order = orderDAO.createOrder(getCurrentUserId(), items, comment);
		orderModel.add(order);
		return order;
	}

	public ListModel<Order> getOrderListModel() {
		if (orderModel == null) {
			List<Order> orders = orderDAO.findByUser(getCurrentUserId());
			orderModel = new ListModelList<Order>(orders);
		}
		return orderModel;
	}

	public void cancelOrder(Long orderId) {
		// 1. Do the update using DAO
		Order order = orderDAO.cancelOrder(orderId);
		// 2. update UI
		int index = orderModel.indexOf(order);
		orderModel.set(index, order);
	}

	private long getCurrentUserId() {
		return UserCredentialManager.getInstance(session).getUser().getId();
	}

}
