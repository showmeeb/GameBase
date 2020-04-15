package com.gamebase.tradesystem.model.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gamebase.general.model.dao.TagSearchDAO;
import com.gamebase.tradesystem.model.dao.ShoppingCartDao;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Service
@Transactional
public class ShoppingService {
	@Autowired
	private TagSearchDAO tagSearchDAO;
	
	@Autowired
	private ShoppingCartDao shoppingCartDao;
	
	
	
	public JSONObject adds(String form) {
		return shoppingCartDao.adds(form);
	}
	
	public JSONArray querys(int id) {
		return shoppingCartDao.querys(id);
	}

	
	public JSONArray showProduct(String keyword) {
		return tagSearchDAO.showProduct(keyword);
	}
	
	}
