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
	public JSONObject addProducts(@RequestParam(value = "b") String b,@RequestParam(value = "userId") String userId) {
		System.out.println(b);
		System.out.println(userId);
		return shoppingService.adds(b,Integer.valueOf(userId));
	}
	
	@RequestMapping(path = "/shopping/showCartProduct", method = RequestMethod.POST)
	@ResponseBody
	public JSONArray showCartProduct(@RequestParam(value = "id") String id) {
		System.out.println(id);
		return shoppingService.querys(Integer.valueOf(id));
	}
	
	@RequestMapping(path = "/shopping/deleteCartProduct", method = RequestMethod.POST)
	@ResponseBody
	public JSONObject deleteCartProduct(@RequestParam(value = "d") String d) {
		System.out.println("d:"+d);
		return shoppingService.deletes(Integer.valueOf(d));
	}
	
	@RequestMapping(path = "/shoppingCart/payBill", method = RequestMethod.POST)
	@ResponseBody
	public String payBill() {
		System.out.println("payBill");
		
		return shoppingService.processOrder();
	}
	
	
	
	@RequestMapping(path = "/shoppingPage", method = RequestMethod.GET)
	public String shoppingPage() {
		System.out.println("1");
		return "shoppingPage";
	}
	
	@RequestMapping(path = "/orderPage", method = RequestMethod.GET)
	public String orderPage() {
		System.out.println("1");
		return "orderPage";
	}
	
	@RequestMapping(path = "/shoppingCartPage", method = RequestMethod.GET)
	public String shoppingCartPage() {
		System.out.println("1");
		return "shoppingCartPage";
	}
	
}
