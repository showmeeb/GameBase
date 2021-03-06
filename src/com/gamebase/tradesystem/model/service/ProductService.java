package com.gamebase.tradesystem.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import com.gamebase.tradesystem.model.Product;
import com.gamebase.tradesystem.model.dao.ProductDao;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Service
@Transactional
public class ProductService {
	@Autowired
	private ProductDao productDao;

	public JSONObject add(String form) {
		return productDao.add(form);
	}

	public JSONArray query() {
		return productDao.query();
	}

	public JSONObject delete(int id) {
		return productDao.delete(id);
	}

	public JSONObject update(String b) {

		return productDao.update(b);
	}
	
	public void updateFreq(int i) {
		
		productDao.updateFreq(i);
	}

	public JSONArray search(String a) {
		return productDao.search(a);
	}

	public JSONArray getSearch(String a) {
		return productDao.getSearch(a);
	}

	public String getProductById(String productId) {
		return productDao.getProductById(productId);
	}
	public int getNumOfXbox() {
		return productDao.getNumOfXbox();
	}
	public int getNumOfSwitch() {
		return productDao.getNumOfSwitch();
	}	
	public int getNumOfPs4() {
		return productDao.getNumOfPs4();
	}
	public int getNumOfProduct() {
		return productDao.getNumOfProduct();
	}
}
