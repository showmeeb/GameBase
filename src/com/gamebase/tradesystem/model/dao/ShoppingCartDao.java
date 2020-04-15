package com.gamebase.tradesystem.model.dao;
import java.util.List;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.hibernate.Session;

import com.gamebase.tradesystem.model.Product;
import com.gamebase.tradesystem.model.ShoppingCart;
import com.google.gson.Gson;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Repository
public class ShoppingCartDao {
	private SessionFactory sessionFactory;
	
	@Autowired
	public ShoppingCartDao(@Qualifier(value ="sessionFactory") SessionFactory sessionFactory) {
		this.sessionFactory=sessionFactory;
	}

	
	public JSONObject adds(String form) {
		Session session = sessionFactory.getCurrentSession();
		JSONObject result= new JSONObject();
		ShoppingCart sc = this.translateKey(form);
		sc.setShoppingCartId(3);
		session.save(sc);
		result.put("t", true);
		return result;
	}

	public JSONArray querys(int id) {
		Session session = sessionFactory.getCurrentSession();
		try {
		List<ShoppingCart> list = session.createQuery("from ShoppingCart",ShoppingCart.class).list();
		//query.setParameter(1,2);
		
		System.out.println("size:"+list.size());
		System.out.println("list:"+list);
		JSONArray jsonArray = new JSONArray();
		
		for(ShoppingCart beans:list) {
			JSONObject jobj = new JSONObject();
			System.out.println(beans.getProductName());
			jobj.put("shoppingCartId",beans.getShoppingCartId());
			jobj.put("productImg", beans.getProductImg());
			jobj.put("productId", beans.getProductId());
			jobj.put("productName", beans.getProductName());
			jobj.put("productPrice", beans.getProductPrice());
			jobj.put("amount", beans.getAmount());
			jsonArray.add(jobj);
		}
		System.out.println(jsonArray);
		return jsonArray;
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public ShoppingCart translateKey(String b) {
		JSONObject jobj = JSONObject.fromObject(b);
		ShoppingCart p1 = new ShoppingCart();
		p1.setProductId((Integer.valueOf((String) jobj.get("0"))));
		p1.setProductImg((String) jobj.get("1"));// (String) jobj.get("1")
		p1.setProductName((String) jobj.get("2"));
		p1.setProductPrice((Integer.valueOf((String) jobj.get("5"))));
		p1.setAmount(1);
		
		return p1;
	}

	

}
