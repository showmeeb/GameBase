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
//		if(jobj.getString("productType").equals("game")) {
		try {
			Product pd = new Product("img",(String)jobj.get("productName"),(String)jobj.get("productType"),Integer.valueOf((String)jobj.get("inventory")),Integer.valueOf((String)jobj.get("productPrice")),(String)jobj.get("gameTag"),(String)jobj.get("productInfo"));
			
			Game game =new Game(4,(String)jobj.get("gameType"),(String)jobj.get("gamePlatform"),(String)jobj.get("gameLevel"));
//			game.setProduct(pd);
//			pd.setGame(game);
//			sessionFactory.getCurrentSession().save(game);
			sessionFactory.getCurrentSession().save(pd);
			return true;
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
//		}else {
//			Product pd = new Product((String)jobj.get("productName"),(String)jobj.get("productType"),Integer.valueOf((String)jobj.get("inventory")),Integer.valueOf((String)jobj.get("productPrice")),(String)jobj.get("gameTag"),(String)jobj.get("productInfo"));
//			Host host =new Host("img",(String)jobj.get("productName"));
//			host.setProduct(pd);
//			pd.setHost(host);
//			sessionFactory.getCurrentSession().save(pd);
//		}
		
		
	}

}
