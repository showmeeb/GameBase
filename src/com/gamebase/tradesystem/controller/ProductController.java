package com.gamebase.tradesystem.controller;

import java.io.PrintStream;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gamebase.tradesystem.model.service.ProductService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
public class ProductController {
	private ProductService productService;
	private PrintStream out;

	public ProductController(ProductService productService) {
		this.productService = productService;
	}

	@RequestMapping(path = "/tradesystem/add", method = RequestMethod.POST)
	@ResponseBody
	public JSONObject tsAdd(@RequestParam(value = "form") String form) {
		JSONObject jo = JSONObject.fromObject(form);
		System.out.println(jo);
		System.out.println(form);
		boolean t = productService.add(jo);
		JSONObject result = new JSONObject();
		result.put("t", t);
		return result;
	}
	@RequestMapping(path = "/tradesystem/query", method = RequestMethod.POST)
	@ResponseBody
	public JSONArray tsQuery() {
		return productService.query();
	}
	@RequestMapping(path = "/tradesystem/delete", method = RequestMethod.POST)
	@ResponseBody
	public JSONObject tsDelete(@RequestParam(value = "d") String d) {
		JSONObject result = new JSONObject();
		boolean t=productService.delete(Integer.valueOf(d));
		result.put("t", t);
		return result;
	}
	@RequestMapping(path = "/tradesystem/update", method = RequestMethod.POST)
	@ResponseBody
	public JSONObject tsUpdate(@RequestParam(value = "b") String b) {
		System.out.println(b);
		JSONObject jobj = JSONObject.fromObject(b);
		System.out.println(jobj);
		boolean t=productService.update(jobj);
		JSONObject result = new JSONObject();
		result.put("t", t);
		return result;
	}

	@RequestMapping(path = "/tradesystem", method = RequestMethod.GET)
	public String showAddPage() {
		System.out.println("1");
		return "addProductPage";
	}

	@RequestMapping(path = "/mainProduct", method = RequestMethod.GET)
	public String showMainPage() {
		System.out.println("2");
		return "mainProductPage";
	}
}
