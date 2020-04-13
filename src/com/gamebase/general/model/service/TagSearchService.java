package com.gamebase.general.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gamebase.general.model.dao.TagSearchDAO;

@Service
public class TagSearchService {

	private TagSearchDAO tagSearchDAO;

	@Autowired
	public TagSearchService(TagSearchDAO tagSearchDAO) {
		this.tagSearchDAO = tagSearchDAO;
	}

	public String tagSearch(String looking, String keyword) {
		return tagSearchDAO.tagSearch(looking, keyword);

	}

	public String tagSearchByFuzzy(String tableName, String fieldName, String keyword) {
		return tagSearchDAO.tagSearchByFuzzy(tableName, fieldName, keyword);

	}
}
