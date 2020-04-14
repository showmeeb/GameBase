package com.gamebase.tradesystem.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gamebase.tradesystem.model.service.ShoppingService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

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
	
	@RequestMapping(path = "/shopping/addProduct", method = RequestMethod.POST)
	@ResponseBody
	public JSONObject addProducts(@RequestParam(value = "b") String b) {
		System.out.println(b);
		return shoppingService.adds(b);
	}
	
	@RequestMapping(path = "/shopping/showCartProduct", method = RequestMethod.POST)
	@ResponseBody
	public JSONArray showCartProduct(@RequestParam(value = "id") String id) {
		System.out.println(id);
		return shoppingService.querys(Integer.valueOf(id));
	}
	
	@RequestMapping(path = "/shoppingPage", method = RequestMethod.GET)
	public String shoppingPage() {
		System.out.println("1");
		return "shoppingPage";
	}
	
	@RequestMapping(path = "/shoppingCartPage", method = RequestMethod.GET)
	public String shoppingCartPage() {
		System.out.println("1");
		return "shoppingCartPage";
	}
	
}
