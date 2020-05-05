package com.gamebase.tradesystem.model.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gamebase.general.model.dao.TagSearchDAO;
import com.gamebase.member.model.dao.MailSenderDAO;
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
	
	@Autowired
	private MailSenderDAO mailSenderDao;
	
	
	public JSONObject shoppingCartAdds(String form ,int id) {
		return shoppingCartDao.shoppingCartAdds(form,id);
	}
	
	public JSONArray shoppingCartQuerys(int id) {
		return shoppingCartDao.shoppingCartQuerys(id);
	}
	public JSONObject shoppingCartDeletes(int id) {
		
		return shoppingCartDao.shoppingCartDeletes(id);
	}
	
	public String shoppingCartUpdate(String data) {
		return shoppingCartDao.shoppingCartUpdate(data);
	}

	public JSONArray showProduct(String keyword) {
		return tagSearchDAO.showProduct(keyword);
	}
	
	
	
	public String processOrder(String obj,String items) {
		
		return userOrderDao.processOrder(obj,items);
		
	}
	
	public JSONObject orderStatus(int rtnCode,int orderId) {
		
		 return userOrderDao.orderStatus(rtnCode,orderId);
		
	}
	
	public JSONArray orderQuery(int id) {
		
		return userOrderDao.orderQuery(id);
	}
	
	public void sendOrderDetail(int orderId) {
		mailSenderDao.sendOrderDetail(orderId);
	}
	
	public List<UserOrder> allOrders(){
		return userOrderDao.allOrders();
	}
	
	public boolean checkRank(int userId) {
		return userOrderDao.checkRank(userId);
	}
	
	}
