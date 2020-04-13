package com.gamebase.tradesystem.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gamebase.general.model.dao.TagSearchDAO;

import net.sf.json.JSONArray;

@Service
@Transactional
public class ShoppingService {
	private TagSearchDAO tagSearchDAO;
	
	@Autowired
	public ShoppingService(TagSearchDAO tagSearchDAO) {
	this.tagSearchDAO = tagSearchDAO;
	}

	
	public JSONArray showProduct(String keyword) {
		return tagSearchDAO.showProduct(keyword);
	}
	
	}
