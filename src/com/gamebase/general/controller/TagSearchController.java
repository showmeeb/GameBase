package com.gamebase.general.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gamebase.article.model.ArticleListView;
import com.gamebase.article.model.Forum;
import com.gamebase.general.model.service.TagSearchService;

@Controller
public class TagSearchController {

	@Autowired
	private TagSearchService tagSearchService;

	@RequestMapping(value = "/tagSearch", method = RequestMethod.GET)
	public String tagSearch(@RequestParam("looking") String looking, @RequestParam("keyword") String keyword,
			Model model) {

		String jsonStr = "", returnView = "indexPage";
		Set<ArticleListView> aLView=null;

		if (!keyword.equals(null) && keyword != "" && looking.equals("forProduct")) {
			jsonStr = tagSearchService.tagSearchByFuzzy("Product", "productName productTag", keyword);
			returnView="shoppingPage";
			model.addAttribute("results", jsonStr);
			
		}else if (!keyword.equals(null) && keyword != "" && looking.equals("foForumr")) {
			aLView = (Set<ArticleListView>) tagSearchService.tagSearch(looking, keyword);
			returnView="testTitleViewPage";		
			model.addAttribute("articleList", aLView);
			model.addAttribute("forum", new Forum("搜尋結果",""));
		}

		//articleList
		
		
		
		model.addAttribute("keyword", keyword);
		model.addAttribute("looking", looking);

		return returnView;
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

	@RequestMapping(value = "/searchArticleClick", method = RequestMethod.GET)
	@ResponseBody
	public String searchArticleClick() {
		String returnString = tagSearchService.searchArticleClick();

		System.out.println(returnString);
		return returnString;
	}
}
