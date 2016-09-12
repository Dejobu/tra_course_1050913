package org.zkoss.training.shop.controller;

import java.util.List;

import org.zkoss.bind.annotation.Init;
import org.zkoss.training.shop.model.ProductDAO;
import org.zkoss.training.shop.model.bean.Product;

public class ProductVM {

	private ProductDAO prodDAO = new ProductDAO();
	private List<Product> prodModel;

	@Init
	public void init() {
		prodModel = prodDAO.findAllAvailable();
	}

	public List<Product> getProdModel() {
		return prodModel;
	}
}
