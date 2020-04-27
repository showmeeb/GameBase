package com.gamebase.general.model.dao;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gamebase.tradesystem.model.Product;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Repository
public class TagSearchDAO {

	@Autowired
	private SessionFactory sessionFactory;

	public String tagSearch(String looking, String keyword) {

		String jsonString = null;

		if (looking.equals("forProduct")) {
			jsonString = searchProduct(keyword);
		} else if (looking.equals("")) {
		}

		return jsonString;
	}

	private String searchProduct(String keyword) {

		Session session = sessionFactory.getCurrentSession();

		Set<Product> searchSet = new LinkedHashSet<Product>();

		String[] keywordArray = keyword.split("\\s+");

		for (int i = 0; i < keywordArray.length; i++) {
			keyword = keywordArray[i];
			List<Product> list = session.createQuery("From Product where productName like '%" + keyword + "%'"
					+ " or productTag like '%" + keyword + "%'", Product.class).list();
			for (int j = 0; j < list.size(); j++) {
				searchSet.add(list.get(j));
			}
		}

		String jsonString = null;
		try {
			jsonString = new ObjectMapper().writeValueAsString(searchSet);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		return jsonString;

	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String tagSearchByFuzzy(String tableName, String fieldName, String keyword) {

		String jsonString = null;

		Session session = sessionFactory.getCurrentSession();

		Set searchSet = new LinkedHashSet();

		String[] fieldNameArray = fieldName.split("\\s+|,");
		String[] keywordArray = keyword.split("\\s+|,");

		String hqlStr = "From " + tableName + " where ";

		for (int i = 0; i < fieldNameArray.length; i++) {
			if (i + 1 == fieldNameArray.length) {
				hqlStr += fieldNameArray[i] + " like :field" + i + "";
				continue;
			}
			hqlStr += fieldNameArray[i] + " like :field" + i + " or ";
		}

		
		
		Query query = session.createQuery(hqlStr);

		for (int i = 0; i < keywordArray.length; i++) {
			for (int j = 0; j < fieldNameArray.length; j++) {
				query.setParameter("field" + j, "%" + keywordArray[i] + "%");
			}
			List list = query.list();

			for (int j = 0; j < list.size(); j++) {
				searchSet.add(list.get(j));
			}
		}

		try {
			jsonString = new ObjectMapper().writeValueAsString(searchSet);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		System.out.println(jsonString);
		
		return jsonString;
	}
	
	public JSONArray showProduct(String keyword) {

		Session session = sessionFactory.getCurrentSession();
		JSONArray jsonArray = new JSONArray();
			List<Product> list = session.createQuery("From Product where productTag like'%" + keyword + "%'", Product.class).list();
			for (Product beans:list) {
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
			System.out.println("jsonArray:"+jsonArray);
		return jsonArray;
	}
	
	public Set<String> autoComplete()  {
		List<Object[]> resultList =sessionFactory.getCurrentSession().createQuery("Select productName,productTag from Product").list();
		Set<String> returnSet = new LinkedHashSet<String>();

		for(Object[] object : resultList) {
			returnSet.add((String)object[0]);
			
			String[] tagSplit = ((String)object[1]).split(",");
			for(String tagItem:tagSplit) {
				returnSet.add(tagItem);
			}
		}
		return returnSet;
	}
}
