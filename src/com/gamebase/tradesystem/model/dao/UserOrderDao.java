package com.gamebase.tradesystem.model.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.gamebase.tradesystem.model.Product;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class UserOrderDao implements IProductDao {
	private SessionFactory sessionFactory;
	
	@Autowired
	public UserOrderDao(@Qualifier(value = "sessionFactory") SessionFactory sessionFactory) {
		this.sessionFactory=sessionFactory;
	}
	

	@Override
	public JSONObject add(String obj) {
		Session session = sessionFactory.getCurrentSession();
		return null;
	}

	@Override
	public JSONArray query() {
		
		return null;
	}

	@Override
	public JSONObject delete(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JSONObject update(String b) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JSONArray search(String a) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JSONArray getSearch(String a) {
		// TODO Auto-generated method stub
		return null;
	}



}
