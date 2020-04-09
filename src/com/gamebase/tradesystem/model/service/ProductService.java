package com.gamebase.tradesystem.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gamebase.tradesystem.model.dao.ProductDao;

import net.sf.json.JSONObject;

@Service("PDS")
public class ProductService {
	private ProductDao productDao;
	@Autowired
	public ProductService (ProductDao productDao) {
		this.productDao=productDao;
	}
	
	public boolean add(JSONObject jobj) {
		return productDao.add(jobj);
	}
}
