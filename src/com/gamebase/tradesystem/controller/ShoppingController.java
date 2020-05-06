package com.gamebase.tradesystem.controller;


import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gamebase.member.model.UserData;
import com.gamebase.member.model.UsersInfo;
import com.gamebase.member.model.service.UserDataService;
import com.gamebase.tradesystem.model.OrderDetail;
import com.gamebase.tradesystem.model.UserOrder;
import com.gamebase.tradesystem.model.service.ProductService;
import com.gamebase.tradesystem.model.service.ShoppingService;
import com.google.gson.Gson;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
public class ShoppingController {
	@Autowired
	private ShoppingService  shoppingService;
	@Autowired
	private ProductService productService;
	@Autowired
	private UserDataService userDataService;
	

	
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
		System.out.println("userId:"+userId);
		return shoppingService.shoppingCartAdds(b,Integer.valueOf(userId));
	}
	
	@RequestMapping(path = "/shopping/showCartProduct", method = RequestMethod.POST)
	@ResponseBody
	public JSONArray showCartProduct(@RequestParam(value = "id") String id) {
		System.out.println(id);
		return shoppingService.shoppingCartQuerys(Integer.valueOf(id));
	}
	
	@RequestMapping(path = "/shopping/deleteCartProduct", method = RequestMethod.POST)
	@ResponseBody
	public JSONObject deleteCartProduct(@RequestParam(value = "d") String d) {
		System.out.println("d:"+d);
		return shoppingService.shoppingCartDeletes(Integer.valueOf(d));
	}
	
	@RequestMapping(path = "/shopping/shoppingCartUpdate", method = RequestMethod.POST)
	@ResponseBody
	public String shoppingCartUpdate(@RequestParam(value = "data") String data) {
		System.out.println("data:"+data);
		return shoppingService.shoppingCartUpdate(data);
	}
	
	@RequestMapping(path = "/shoppingCart/payBill", method = RequestMethod.POST)
	@ResponseBody
	public String payBill(@RequestParam(value = "form") String form,@RequestParam(value = "items1") String items1) {
		System.out.println("payBill");
		System.out.println("form:"+form);
		System.out.println("items1:"+items1);
		return shoppingService.processOrder(form,items1);
	}
//	@RequestMapping(path = "/shoppingCart/test", method = RequestMethod.POST)
//	@ResponseBody
//	public String test(@RequestParam(value ="form") String form,@RequestParam(value = "items1") String items1) {
//		System.out.println("form:"+form);
//		System.out.println("items1:"+items1);
//		return "yes";
//	}
	
	@RequestMapping(path = "/shoppingCart/orderStatus", method = RequestMethod.POST)
	
	public String orderStatus(HttpServletRequest request,Model model) {

		String rtnCode = request.getParameter("RtnCode");
		String orderId = request.getParameter("CustomField4");
		String uuid = request.getParameter("MerchantTradeNo");
		System.out.println("RtnCode:"+rtnCode);
		System.out.println("orderName:"+orderId);
//		System.out.println("orderPhone:"+orderPhone);
//		System.out.println("orderAddress:"+orderAddress);
//		System.out.println("userId:"+userId);
//		System.out.println("uuId:"+uuId);
//		System.out.println("orderDate:"+orderDate);
//		System.out.println("orderPrice:"+orderPrice);
		
		shoppingService.sendOrderDetail(Integer.parseInt(orderId));
		JSONObject jobj=shoppingService.orderStatus(Integer.parseInt(rtnCode),Integer.parseInt(orderId));
		if((boolean)jobj.get("t")==true) {
			UserData ud=userDataService.getByUserId((int)jobj.get("userId"));
			if(ud.getRankId().equals(2)) {
			ud.setRankId(3);
			userDataService.saveUserData(ud);
			UsersInfo uinfo = userDataService.showUserData(ud.getAccount());
			model.addAttribute("loginUserEpay", uinfo);
			System.out.println("123");
			}
			System.out.println("456");
		}
		productService.updateFreq(Integer.parseInt(orderId));
		
		System.out.println("付款成功!!");
		return "indexPage";
	}
	
	@RequestMapping(path = "/orderPage/showOrder", method = RequestMethod.POST)
	@ResponseBody
	public JSONArray showOrder(@RequestParam(value = "id") String id) {
		System.out.println(id);
		return shoppingService.orderQuery(Integer.valueOf(id));
	}
	
	@RequestMapping(path = "/epay", method ={RequestMethod.POST,RequestMethod.GET} )
	public String epay(HttpServletRequest request,Model model) {
		String orderId = request.getParameter("CustomField4");
		System.out.print(orderId);
		
		return "indexPage";
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
	
	@RequestMapping(path = "/orderPage/getMoneyWeek", method = RequestMethod.POST)
	@ResponseBody
	public List<UserOrder> getMoneyWeek() {
		
		return shoppingService.allOrders();
	}
	
}
