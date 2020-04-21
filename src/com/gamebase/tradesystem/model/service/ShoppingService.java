package com.gamebase.tradesystem.model.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gamebase.general.model.dao.TagSearchDAO;
import com.gamebase.tradesystem.model.UserOrder;
import com.gamebase.tradesystem.model.dao.ShoppingCartDao;
import com.gamebase.tradesystem.model.dao.UserOrderDao;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Service
@Transactional
public class ShoppingService {
	@Autowired
	private TagSearchDAO tagSearchDAO;
	
	@Autowired
	private ShoppingCartDao shoppingCartDao;
	
	@Autowired
	private UserOrderDao userOrderDao;
	
	
	public JSONObject adds(String form ,int id) {
		return shoppingCartDao.adds(form,id);
	}
	
	public JSONArray querys(int id) {
		return shoppingCartDao.querys(id);
	}
	public JSONObject deletes(int id) {
		
		return shoppingCartDao.deletes(id);
	}

	
	public JSONArray showProduct(String keyword) {
		return tagSearchDAO.showProduct(keyword);
	}
	
	
	
	public String processOrder(String obj) {
		
		return userOrderDao.processOrder(obj);
		
	}
	
	public void orderStatus(int rtnCode,int userId,String uuId,String orderDate,String orderName,String orderPhone,String orderAddress,int orderPrice) {
		
		 userOrderDao.orderStatus(rtnCode,userId,uuId,orderDate,orderName,orderPhone,orderAddress,orderPrice);
		
	}
	
	}
