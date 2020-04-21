package com.gamebase.tradesystem.controller;


import javax.servlet.http.HttpServletRequest;

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
	public String payBill(@RequestParam(value = "form") String form) {
		System.out.println("payBill");
		return shoppingService.processOrder(form);
	}
	
	@RequestMapping(path = "/shoppingCart/orderStatus", method = RequestMethod.POST)
	@ResponseBody
	public void orderStatus(HttpServletRequest request) {
		String rtnCode = request.getParameter("RtnCode");
		String orderName = request.getParameter("CustomField1");
		String orderPhone = request.getParameter("CustomField2");
		String orderAddress = request.getParameter("CustomField3");
		String userId = request.getParameter("CustomField4");
		String uuId = request.getParameter("MerchantTradeNo");
		String orderDate = request.getParameter("TradeDate");
		String orderPrice = request.getParameter("TradeAmt");
//		System.out.println("RtnCode:"+rtnCode);
//		System.out.println("orderName:"+orderName);
//		System.out.println("orderPhone:"+orderPhone);
//		System.out.println("orderAddress:"+orderAddress);
//		System.out.println("userId:"+userId);
//		System.out.println("uuId:"+uuId);
//		System.out.println("orderDate:"+orderDate);
//		System.out.println("orderPrice:"+orderPrice);
		
		System.out.println("付款成功!!");
		shoppingService.orderStatus(Integer.parseInt(rtnCode),Integer.parseInt(userId),uuId,orderDate,orderName,orderPhone,orderAddress,Integer.parseInt(orderPrice));
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
