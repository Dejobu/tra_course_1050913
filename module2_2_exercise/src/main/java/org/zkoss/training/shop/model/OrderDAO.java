package org.zkoss.training.shop.model;

import static org.zkoss.training.shop.model.bean.Order.CANCELED;
import static org.zkoss.training.shop.model.bean.Order.COMPLETE;
import static org.zkoss.training.shop.model.bean.Order.PROCESSING;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.zkoss.training.shop.model.bean.CartItem;
import org.zkoss.training.shop.model.bean.Order;
import org.zkoss.training.shop.model.bean.OrderItem;
import org.zkoss.training.shop.model.bean.Product;

public class OrderDAO {
	private static final Map<Long, Order> dbModel = new HashMap<Long, Order>();

	private static volatile long orderId = 0L;
	private static volatile long orderItemId = 0L;

	static {
		long time = System.currentTimeMillis();
		long user1 = 1L;
		long user2 = 2L;
		ProductDAO prodDAO = new ProductDAO();
		Product prod1 = prodDAO.findById(1L);
		Product prod2 = prodDAO.findById(2L);
		Product prod3 = prodDAO.findById(3L);
		Product prod4 = prodDAO.findById(4L);
		Product prod5 = prodDAO.findById(5L);
		Product prod6 = prodDAO.findById(6L);

		Order order = null;
		order = new Order(orderId++, user1, COMPLETE, new Date(time - 995420000L), "Best receiving time is 9:00 PM, by Ian Tsai");
		dbModel.put(order.getId(), order);
		order.addItem(new OrderItem(orderItemId++, orderId, prod1.getId(), prod1.getName(), prod1.getPrice(), 3));
		order.addItem(new OrderItem(orderItemId++, orderId, prod2.getId(), prod2.getName(), prod2.getPrice(), 3));
		order.addItem(new OrderItem(orderItemId++, orderId, prod3.getId(), prod3.getName(), prod3.getPrice(), 3));
		order.addItem(new OrderItem(orderItemId++, orderId, prod4.getId(), prod4.getName(), prod4.getPrice(), 3));
		order.addItem(new OrderItem(orderItemId++, orderId, prod5.getId(), prod5.getName(), prod5.getPrice(), 3));
		order.addItem(new OrderItem(orderItemId++, orderId, prod6.getId(), prod6.getName(), prod6.getPrice(), 3));

		order = new Order(orderId++, user2, PROCESSING, new Date(time - 965420000L), "Best receiving time is 9:00 PM");
		dbModel.put(order.getId(), order);
		order.addItem(new OrderItem(orderItemId++, orderId, prod1.getId(), prod1.getName(), prod1.getPrice(), 5));
		order.addItem(new OrderItem(orderItemId++, orderId, prod2.getId(), prod2.getName(), prod2.getPrice(), 10));
		order.addItem(new OrderItem(orderItemId++, orderId, prod4.getId(), prod4.getName(), prod4.getPrice(), 4));
		order.addItem(new OrderItem(orderItemId++, orderId, prod5.getId(), prod5.getName(), prod5.getPrice(), 5));
		order.addItem(new OrderItem(orderItemId++, orderId, prod6.getId(), prod6.getName(), prod6.getPrice(), 1));

		order = new Order(orderId++, user1, PROCESSING, new Date(time - 962420000L), " do not ship the order at morning");
		dbModel.put(order.getId(), order);
		order.addItem(new OrderItem(orderItemId++, orderId, prod1.getId(), prod1.getName(), prod1.getPrice(), 3));
		order.addItem(new OrderItem(orderItemId++, orderId, prod2.getId(), prod2.getName(), prod2.getPrice(), 3));
		order.addItem(new OrderItem(orderItemId++, orderId, prod3.getId(), prod3.getName(), prod3.getPrice(), 3));
		order.addItem(new OrderItem(orderItemId++, orderId, prod4.getId(), prod4.getName(), prod4.getPrice(), 3));
		order.addItem(new OrderItem(orderItemId++, orderId, prod5.getId(), prod5.getName(), prod5.getPrice(), 3));
		order.addItem(new OrderItem(orderItemId++, orderId, prod6.getId(), prod6.getName(), prod6.getPrice(), 3));
	}

	public List<Order> findAll() {
		return new ArrayList<Order>(dbModel.values());
	}

	public List<Order> findByUser(long userId) {
		ArrayList<Order> list = new ArrayList<Order>();
		for (Order order : dbModel.values()) {
			if (order.getUserId() == userId) {
				list.add(order);
			}
		}
		return list;
	}

	private void add(Order order) {
		order.setId(orderId++);
		dbModel.put(order.getId(), order);
	}

	private void add(OrderItem oItem) {
		// FAKE impl...
	}

	public Order createOrder(long userId, List<CartItem> items, String description) {

		// SIMULATE transaction start

		Order order = new Order(null, userId, Order.PROCESSING, new Date(), description);

		add(order);

		for (CartItem item : items) {
			Product prod = item.getProduct();
			OrderItem oItem = new OrderItem(orderItemId++, order.getId(), prod.getId(), prod.getName(), prod.getPrice(), item.getAmount());
			order.addItem(oItem);
			add(oItem);
		}

		// SIMULATE transaction end
		return order;
	}

	public Order findById(long orderId) {
		return dbModel.get(orderId);
	}

	public Order cancelOrder(long orderId) {
		Order order = findById(orderId);
		order.setStatus(CANCELED);
		return order;
	}

}
