package com.gamebase.tradesystem.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gamebase.tradesystem.model.service.ShoppingService;

import net.sf.json.JSONArray;

@Controller
public class ShoppingController {
	private ShoppingService  shoppingService;
	
	
	public ShoppingController(ShoppingService shoppingService) {
		this.shoppingService=shoppingService;
	}
	
	@RequestMapping(path = "/shopping/switchProduct", method = RequestMethod.POST)
	@ResponseBody
	public JSONArray showProduct(@RequestParam(value = "type") String type) {
		System.out.println(type);
		return shoppingService.showProduct(type);
	}
	
	@RequestMapping(path = "/shoppingPage", method = RequestMethod.GET)
	public String showAddPage() {
		System.out.println("1");
		return "shoppingPage";
	}
	
}
