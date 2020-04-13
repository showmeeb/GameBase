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
}
