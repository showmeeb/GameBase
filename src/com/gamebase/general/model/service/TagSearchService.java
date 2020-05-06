package com.gamebase.general.model.service;

import java.util.Set;

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

	public Object tagSearch(String looking, String keyword) {
		return tagSearchDAO.tagSearch(looking, keyword);

	}

	public String tagSearchByFuzzy(String tableName, String fieldName, String keyword) {
		return tagSearchDAO.tagSearchByFuzzy(tableName, fieldName, keyword);

	}

	public Set<String> autoComplete() {
		return tagSearchDAO.autoComplete();

	}
	
	public String searchFreq() {
		return tagSearchDAO.searchFreq();

	}
	
	public String searchArticleClick() {
		return tagSearchDAO.searchArticleClick();

	}
}
