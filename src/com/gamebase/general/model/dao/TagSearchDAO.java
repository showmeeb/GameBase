package com.gamebase.general.model.dao;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gamebase.tradesystem.model.Product;

@Repository
public class TagSearchDAO {

	@Autowired
	private SessionFactory sessionFactory;

	public String tagSearch(String lookingFor, String keyword) {

		String jsonString = null;

		if (lookingFor.equals("Product")) {
			jsonString = searchProduct(keyword);
		} else if (lookingFor.equals("")) {
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
		System.out.println(jsonString);

		return jsonString;

	}

}
