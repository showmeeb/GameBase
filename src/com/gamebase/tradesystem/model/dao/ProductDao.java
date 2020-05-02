package com.gamebase.tradesystem.model.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gamebase.tradesystem.model.Game;
import com.gamebase.tradesystem.model.OrderDetail;
import com.gamebase.tradesystem.model.Product;
import com.gamebase.tradesystem.model.UserOrder;
import com.google.gson.Gson;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Repository
public class ProductDao implements IProductDao {
	private SessionFactory sessionFactory;

	@Autowired
	public ProductDao(@Qualifier(value = "sessionFactory") SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;

	}

	private Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public JSONObject add(String form) {
		JSONObject result = new JSONObject();
		try {
			Product pd = new Gson().fromJson(form, Product.class);
			getSession().save(pd);
			if (pd.getProductType().equals("game")) {
				Game game = new Gson().fromJson(form, Game.class);
				game.setProductId(pd.getProductId());
				getSession().save(game);
				result.put("t", true);
				return result;
			}
			Game game = new Game(pd.getProductId(), "null", null, null);
			getSession().save(game);
			result.put("t", true);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			result.put("t", false);
			return result;
		}

	}

	@Override
	public JSONArray query() {

		Session session = sessionFactory.getCurrentSession();
		Query<Product> queryP = session.createQuery("from Product", Product.class);
		// Query<Game> queryG = getSession().createQuery("from Game",Game.class);
		List<Product> listP = queryP.getResultList();
		// List<Game> listG = queryG.getResultList();
		JSONArray jsonArray = new JSONArray();

		for (Product beans : listP) {
			JSONObject jobj = new JSONObject();
			jobj.put("productId", beans.getProductId());
			jobj.put("productVideo", beans.getProductVideo());
			jobj.put("productImg", beans.getProductImg());
			jobj.put("productName", beans.getProductName());
			jobj.put("productType", beans.getProductType());
			jobj.put("inventory", beans.getInventory());
			jobj.put("productPrice", beans.getProductPrice());
			jobj.put("productTag", beans.getProductTag());
			jobj.put("productInfo", beans.getProductInfo());
			jsonArray.add(jobj);
		}
		System.out.println(jsonArray);

		return jsonArray;
	}

	@Override
	public JSONObject delete(int id) {
		JSONObject result = new JSONObject();
		Session session = sessionFactory.getCurrentSession();
		try {
			Query query = session.createQuery("delete from Game where productId=?1");
			query.setParameter(1, id);
			query.executeUpdate();
			Product p1 = session.get(Product.class, id);

			session.delete(p1);
			session.flush();
			result.put("t", true);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			result.put("t", false);
			return result;
		}
	}

	@Override
	public JSONObject update(String b) {
		Session session = sessionFactory.getCurrentSession();
		JSONObject result = new JSONObject();
		try {
			Product p1 = this.translateKey(b);
			session.update(p1);
//			System.out.println("update success");
			result.put("t", true);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			result.put("t", false);
			return result;
		}
	}
	
	public void updateFreq(int orderId) {
		Session session = sessionFactory.getCurrentSession();
		
		Query<OrderDetail> query = session.createQuery("from OrderDetail where orderId=?1",OrderDetail.class);
		query.setParameter(1, orderId);
		List<OrderDetail> list = query.getResultList();
		
		for(OrderDetail beans:list) {
			Product pd=session.get(Product.class, beans.getProductId());
			System.out.println("1"+pd.getSearchFreq());
			pd.setSearchFreq(pd.getSearchFreq()+beans.getAmount());
			System.out.println("2"+pd.getSearchFreq());
			session.save(pd);
		}
		System.out.println("修改銷售度");
	}

	@Override
	public JSONArray search(String a) {

		JSONArray jarray = new JSONArray();
		Session session = sessionFactory.getCurrentSession();

		Query<String> query = session.createQuery("select productName from Product where productName LIKE '" + a + "%'",
				String.class);

		List<String> list = query.list();
		for (int i = 0; i <= list.size() - 1; i++) {
			JSONObject jobj = new JSONObject();
			jobj.put("value", list.get(i));
			System.out.println(jobj);
			jarray.add(jobj);
		}
		System.out.println(jarray);
		return jarray;
	}

	@Override
	public JSONArray getSearch(String a) {
		JSONArray jarray = new JSONArray();
		Session session = sessionFactory.getCurrentSession();
		Query<String> query = session.createQuery("select productName from Product where productName like'%" + a + "%'",
				String.class);
		List<String> list = query.list();
		System.out.println(list.size());
		if (list.size() > 0) {
			for (String beans : list) {
				Query<Product> query1 = session.createQuery("from Product where productName =?1", Product.class);
				query1.setParameter(1, beans);
				List<Product> list1 = query1.getResultList();
				for (Product beans1 : list1) {
					JSONObject jobj = new JSONObject();
					jobj.put("productId", beans1.getProductId());
					jobj.put("productVideo", beans1.getProductVideo());
					jobj.put("productImg", beans1.getProductImg());
					jobj.put("productName", beans1.getProductName());
					jobj.put("productType", beans1.getProductType());
					jobj.put("inventory", beans1.getInventory());
					jobj.put("productPrice", beans1.getProductPrice());
					jobj.put("productTag", beans1.getProductTag());
					jobj.put("productInfo", beans1.getProductInfo());
					jarray.add(jobj);
				}
			}
		}
		System.out.println(jarray + "1");
		return jarray;
	}

	public Product translateKey(String b) {
		JSONObject jobj = JSONObject.fromObject(b);
		System.out.println("b:"+b);
		System.out.println(jobj);
		Product p1 = new Product();
		p1.setProductId(Integer.valueOf((String) jobj.get("0")));
		p1.setProductVideo((String) jobj.get("1"));
		p1.setProductImg((String) jobj.get("2"));// (String) jobj.get("1")
		p1.setProductName((String) jobj.get("3"));
		p1.setProductType((String) jobj.get("4"));
		p1.setInventory((Integer.valueOf((String) jobj.get("5"))));
		p1.setProductPrice((Integer.valueOf((String) jobj.get("6"))));
		p1.setProductTag((String) jobj.get("7"));
		p1.setProductInfo((String) jobj.get("8"));
		
		return p1;
	}

	public String getProductById(String productId) {
		Product product = sessionFactory.getCurrentSession().get(Product.class, Integer.valueOf(productId));
	//	Game game = sessionFactory.getCurrentSession().createQuery("Form Game where productId=" + productId, Game.class).uniqueResult();

		String jsonString = "";
		try {
			jsonString = new ObjectMapper().writeValueAsString(product);
	//		String gameString = new ObjectMapper().writeValueAsString(game);
	//		System.out.println(jsonString+gameString);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		return jsonString;
	}
}
