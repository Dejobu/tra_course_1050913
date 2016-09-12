package org.zkoss.training.shop.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.zkoss.training.shop.model.bean.Product;

public class ProductDAO {

	private static final Map<Long, Product> dbModel = new HashMap<Long, Product>();

	private static final long time = System.currentTimeMillis();
	private static final long SEC = 1000;
	private static final long MIN = 60 * SEC;
	private static final long HOUR = 60 * MIN;
	private static final long DAY = 24 * HOUR;

	static {
		dbModel.put(1L, new Product(1L, "Cookies",       randDate(-3, 5, 30),    4F, 30, true, "/resources/img/cookie.jpg"));
		dbModel.put(2L, new Product(2L, "Toast",         randDate(-5, 3, 0),     3F, 43, true, "/resources/img/toast.jpg"));
		dbModel.put(3L, new Product(3L, "Chocolate",     randDate(-10, 5, 33), 5.1F, 12, true, "/resources/img/chocolate.jpg"));
		dbModel.put(4L, new Product(4L, "Butter",        randDate(-7, 8, 0),   2.5F, 60, true, "/resources/img/butter.jpg"));
		dbModel.put(5L, new Product(5L, "Milk",          randDate(-4, 3, 0),   3.1F, 71, true, "/resources/img/milk.jpg"));
		dbModel.put(6L, new Product(6L, "Coffee Powder", randDate(-4, 3, 0),  10.4F, 59, true, "/resources/img/coffee.jpg"));
	}

	private static Date randDate(int day, int hour, int min) {
		return new Date(time + day * DAY + hour * HOUR + min * MIN);
	}

	public Product findById(long id) {
		return dbModel.get(id);
	}

	public List<Product> findAll() {
		return new ArrayList<Product>(dbModel.values());
	}

	public List<Product> findAllAvailable() {
		ArrayList<Product> result = new ArrayList<Product>();
		for (Product prod : new ArrayList<Product>(dbModel.values())) {
			if (prod.isAvailable()) {
				result.add(prod);
			}
		}
		return result;
	}

	public Product remove(long productId) {
		Product prod = dbModel.get(productId);
		prod.setAvailable(false);
		return prod;
	}

	public Product putOn(long productId) {
		Product prod = dbModel.get(productId);
		prod.setAvailable(true);
		return prod;
	}

}
