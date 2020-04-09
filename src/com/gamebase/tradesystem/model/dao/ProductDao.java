package com.gamebase.tradesystem.model.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.gamebase.tradesystem.model.Game;
import com.gamebase.tradesystem.model.Host;
import com.gamebase.tradesystem.model.Product;
import com.gamebase.tradesystem.model.ProductType;

import net.sf.json.JSONObject;

@Repository("PDD")
public class ProductDao {
	private SessionFactory sessionFactory;
	
	
	@Autowired
	public ProductDao (@Qualifier(value="sessionFactory")SessionFactory sessionFactory) {
		this.sessionFactory=sessionFactory;
	}
	
	
	public boolean add(JSONObject jobj) {
		if(((String)jobj.getString("productType")).equals("game")) {
			Product pd = new Product((String)jobj.get("1"),Integer.valueOf((String)jobj.get("2")),Integer.valueOf((String)jobj.get("3")),(String)jobj.get("4"),(String)jobj.get("5"));
			Game game =new Game((String)jobj.get("1"),(String)jobj.get("1"),(String)jobj.get("1"),(String)jobj.get("1"),(String)jobj.get("1"));
			game.setProduct(pd);
			pd.setGame(game);
			sessionFactory.getCurrentSession().save(pd);
		}else {
			Product pd = new Product((String)jobj.get("1"),Integer.valueOf((String)jobj.get("2")),Integer.valueOf((String)jobj.get("3")),(String)jobj.get("4"),(String)jobj.get("5"));
			Host host =new Host((String)jobj.get("1"),(String)jobj.get("1"));
			host.setProduct(pd);
			pd.setHost(host);
			sessionFactory.getCurrentSession().save(pd);
		}
		
		return false;
	}

}
