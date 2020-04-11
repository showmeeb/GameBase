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
	public boolean delete(int id) {
		Session session = sessionFactory.getCurrentSession();
		try {
			Product p1 = session.get(Product.class, id);
			System.out.println(p1.getProductId()+"11111");
			 Query<Game> query = session.createQuery("from Game where productId=?1",Game.class);
			 query.setParameter(1, p1.getProductId());
			 List<Game> list = query.getResultList();
			 for(Game g2:list) {
				 session.delete(g2);
			 }
			
			
//			System.out.println(g1.getGameId());
//			System.out.println(g1.getGameLevel());
//			System.out.println(g1.getGameType());
			
//			System.out.println(p1.getProductId());
//			System.out.println(p1.getProductName());
//			System.out.println(p1.getProductPrice());
			session.delete(p1);
			session.flush();
			System.out.println("123");
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean update(JSONObject jobj) {
		Session session = sessionFactory.getCurrentSession();
		try {
			System.out.println(Integer.valueOf((String) jobj.get("0")));
			Product p1 = session.get(Product.class, Integer.valueOf((String) jobj.get("0")));
			Session session2 = sessionFactory.getCurrentSession();
			p1.setProductImg("IMG");// (String) jobj.get("1")
			p1.setProductName((String) jobj.get("2"));
			p1.setProductType((String) jobj.get("3"));
			p1.setInventory((Integer.valueOf((String) jobj.get("4"))));
			p1.setProductPrice((Integer.valueOf((String) jobj.get("5"))));
			p1.setProductTag((String) jobj.get("6"));
			p1.setProductInfo((String) jobj.get("7"));
			session2.update(p1);
			System.out.println("update success" + p1.getProductName());
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

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
		Query<String> query = session.createQuery("select productName from Product where productName like'%"+a+"%'",String.class);
		List<String> list = query.list();
		System.out.println(list.size());
		if (list.size()>0) {
			for (String beans : list) {
				Query<Product> query1 = session.createQuery("from Product where productName =?1", Product.class);
				query1.setParameter(1, beans);
				List<Product> list1 = query1.getResultList();
				for (Product beans1 : list1) {
					JSONObject jobj = new JSONObject();
					jobj.put("productId", beans1.getProductId());
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
}
