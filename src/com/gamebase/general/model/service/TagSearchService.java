package com.gamebase.general.model.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.gamebase.general.model.dao.TagSearchDAO;

public class TagSearchService {

	private TagSearchDAO tagSearchDAO;

	@Autowired
	public TagSearchService(TagSearchDAO tagSearchDAO) {
		this.tagSearchDAO = tagSearchDAO;
	}

	public String tagSearch(String looking, String keyword) {
		return tagSearchDAO.tagSearch(looking, keyword);

	}
}
