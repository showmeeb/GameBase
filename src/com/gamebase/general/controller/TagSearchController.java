package com.gamebase.general.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.gamebase.general.model.service.TagSearchService;

@Controller
public class TagSearchController {

	private TagSearchService tagSearchService;
	
	@RequestMapping(value = "/topBar", method = RequestMethod.GET)
	public String topBar(Model model) {

		return "topBar";
	}

	
	/*
	@RequestMapping(value = "/tagSearch", method = RequestMethod.GET)
	public String tagSearch(@RequestParam("looking") String looking, @RequestParam("keyword") String keyword,
			Model model) {

		String json = tagSearchService.tagSearch(looking, keyword);
		
		return null;
	}
	*/
}
