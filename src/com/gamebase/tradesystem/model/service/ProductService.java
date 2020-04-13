package com.gamebase.tradesystem.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gamebase.tradesystem.model.Product;
import com.gamebase.tradesystem.model.dao.ProductDao;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Service
@Transactional
public class ProductService {
	private ProductDao productDao;

	@Autowired
	public ProductService(ProductDao productDao) {
		this.productDao = productDao;
	}

	public boolean add(JSONObject jobj) {
		return productDao.add(jobj);
	}

	public JSONArray query() {
		return productDao.query();
	}

	public boolean delete(int id) {
		boolean t = productDao.delete(id);
		return t;
	}

	public boolean update(JSONObject jobj) {
		boolean t = productDao.update(jobj);
		return t;
	}

	public JSONArray search(String a) {
		return productDao.search(a);
	}

	public JSONArray getSearch(String a) {
		return productDao.getSearch(a);
	}

	public void addProduct(Product product) {
		productDao.addProduct(product);
	}
}
