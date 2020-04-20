package com.gamebase.general.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.gamebase.general.model.service.TagSearchService;

@Controller
public class TagSearchController {

	@Autowired
	private TagSearchService tagSearchService;

	@RequestMapping(value = "/homePage", method = RequestMethod.GET)
	public String topBar() {

		return "homePage";
	}

	@RequestMapping(value = "/tagSearch", method = RequestMethod.GET)
	public String tagSearch(@RequestParam("looking") String looking, @RequestParam("keyword") String keyword,
			Model model) {

//		String jsonStr = tagSearchService.tagSearch(looking, keyword);
		String jsonStr = tagSearchService.tagSearchByFuzzy("Product", "productName productTag productInfo", keyword);

		model.addAttribute("results", jsonStr);
		model.addAttribute("keyword", keyword);
		model.addAttribute("looking", looking);

		return "searchResults";
	}

	@RequestMapping(value = "/autoComple", method = RequestMethod.GET)
	public void autoComple(Model model) {
		Set<String> returnSet = tagSearchService.autoComple();
		model.addAttribute("autoComple",returnSet);
		
	}
}
