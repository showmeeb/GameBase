package com.gamebase.general.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gamebase.general.model.service.TagSearchService;

@Controller
public class TagSearchController {

	@Autowired
	private TagSearchService tagSearchService;

	@RequestMapping(value = "/indexBackup", method = RequestMethod.GET)
	public String indexBackup() {

		return "indexBackup";
	}

	@RequestMapping(value = "/tagSearch", method = RequestMethod.GET)
	public String tagSearch(@RequestParam("looking") String looking, @RequestParam("keyword") String keyword,
			Model model) {

		//String jsonStr = tagSearchService.tagSearch(looking, keyword);
		
		if(!keyword.equals(null) && keyword!="") {
		String jsonStr = tagSearchService.tagSearchByFuzzy("Product", "productName productTag productInfo", keyword);

		model.addAttribute("results", jsonStr);
		model.addAttribute("keyword", keyword);
		model.addAttribute("looking", looking);
		}
		
		return "searchResults";
	}

	@RequestMapping(value = "/autoComplete", method = RequestMethod.POST)
	@ResponseBody
	public Set<String> autoComplete(Model model) {
		Set<String> returnSet = tagSearchService.autoComplete();
		return returnSet;
	}
	
	
	@RequestMapping(value = "/searchFreq", method = RequestMethod.GET)
	@ResponseBody
	public String searchFreq() {
		String returnString = tagSearchService.searchFreq();

		System.out.println(returnString);
		return returnString;
	}
}
