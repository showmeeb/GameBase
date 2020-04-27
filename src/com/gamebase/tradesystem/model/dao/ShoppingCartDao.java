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

	
	public JSONObject shoppingCartAdds(String form,int id) {
		Session session = sessionFactory.getCurrentSession();
		JSONObject result= new JSONObject();
		ShoppingCart sc = new Gson().fromJson(form, ShoppingCart.class);
		try {
			if(this.checkCartLimit(id)) {
				sc.setUserId(id);
				session.save(sc);
				result.put("t", true);
			}else {
				result.put("t", false);
			}
		}catch(Exception e) {
			e.printStackTrace();
			result.put("t", false);
		}finally{
			System.out.println("result:"+result);
			return result;
		}	
		
	}

	public JSONArray shoppingCartQuerys(int id) {
		Session session = sessionFactory.getCurrentSession();
		try {
		Query<ShoppingCart> query = session.createQuery("from ShoppingCart where userId=?1",ShoppingCart.class);
		query.setParameter(1,id);
		List<ShoppingCart> list = query.getResultList();
		
		System.out.println("size:"+list.size());
		System.out.println("list:"+list);
		JSONArray jsonArray = new JSONArray();
		
		for(ShoppingCart beans:list) {
			JSONObject jobj = new JSONObject();
			System.out.println(beans.getProductName());
			jobj.put("shoppingCartId", beans.getShoppingCartId());
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
	
	public JSONObject shoppingCartDeletes(int id) {
		Session session = sessionFactory.getCurrentSession();
		JSONObject result= new JSONObject();
		try {
		ShoppingCart sc = session.get(ShoppingCart.class, id);
		session.delete(sc);
		result.put("t", true);
		}catch(Exception e) {
			e.printStackTrace();
			result.put("t", false);
			
		}finally{
			return result;
		}
		
	}
	
	public ShoppingCart translateKey(String b) {
		JSONObject jobj = JSONObject.fromObject(b);
		ShoppingCart p1 = new ShoppingCart();
		p1.setProductId((Integer.valueOf((String) jobj.get("0"))));
		p1.setProductImg((String) jobj.get("1"));// (String) jobj.get("1")
		p1.setProductName((String) jobj.get("2"));
		p1.setProductPrice((Integer.valueOf((String) jobj.get("5"))));
		p1.setUserId((int)jobj.get("8"));
		p1.setAmount(1);
		
		return p1;
	}
	
	public boolean checkCartLimit(int id) {
		Session session = sessionFactory.getCurrentSession();
		boolean t =true;
		try {
		Query<ShoppingCart> query = session.createQuery("from ShoppingCart where userId=?1",ShoppingCart.class);
		query.setParameter(1, id);
		List<ShoppingCart> list = query.getResultList();
		System.out.println("會員購物車商品數量:"+list.size());
		if(list.size()>=50) {
			t= false;
		}
		System.out.println("t:"+t);
		return t;
		
		}catch(Exception e) {
			e.printStackTrace();
			t= true;
			System.out.println("t:"+t);
			return t;
		}
			
	}

	

}
