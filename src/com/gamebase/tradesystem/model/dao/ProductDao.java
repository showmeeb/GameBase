package com.gamebase.tradesystem.model.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.gamebase.tradesystem.model.Game;
import com.gamebase.tradesystem.model.Product;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Repository("PDD")
public class ProductDao {
	private SessionFactory sessionFactory;

	@Autowired
	public ProductDao(@Qualifier(value = "sessionFactory") SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;

	}

	private Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	public boolean add(JSONObject jobj) {

		try {
			Product pd = new Product("img", (String) jobj.get("productName"), (String) jobj.get("productType"),
					Integer.valueOf((String) jobj.get("inventory")), Integer.valueOf((String) jobj.get("productPrice")),
					(String) jobj.get("productTag"), (String) jobj.get("productInfo"));
			getSession().save(pd);
			Query<Product> query = getSession().createQuery("from Product where productName=?1", Product.class);
			query.setParameter(1, (String) jobj.get("productName"));

			Product pd1 = query.getSingleResult();
			if (jobj.getString("productType").equals("game")) {
				Game game = new Game(pd1.getProductId(), (String) jobj.get("gameType"),
						(String) jobj.get("gamePlatform"), (String) jobj.get("gameLevel"));
				getSession().save(game);
				return true;
			}
			Game game = new Game(pd1.getProductId(), null, null, null);
			getSession().save(game);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

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

	public boolean delete(int id) {
		Session session = sessionFactory.getCurrentSession();
		try {
			Product p1 = session.get(Product.class,id);
			System.out.println(p1.getProductId());
			System.out.println(p1.getProductName());
			System.out.println(p1.getProductPrice());
			
			session.delete(p1);
			session.flush();
			System.out.println("123");
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean update(JSONObject jobj) {
		Session session = sessionFactory.getCurrentSession();
		try {
			System.out.println(Integer.valueOf((String)jobj.get("0")));
			Product p1 = session.get(Product.class,Integer.valueOf((String)jobj.get("0")));
			Session session2 = sessionFactory.getCurrentSession();
			p1.setProductImg("IMG");//(String) jobj.get("1")
			p1.setProductName((String) jobj.get("2"));
			p1.setProductType((String) jobj.get("3"));
			p1.setInventory((Integer.valueOf((String) jobj.get("4"))));
			p1.setProductPrice((Integer.valueOf((String) jobj.get("5"))));
			p1.setProductTag((String) jobj.get("6"));
			p1.setProductInfo((String) jobj.get("7"));
			session2.update(p1);
			System.out.println("update success"+p1.getProductName());
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

}
