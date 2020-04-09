package com.gamebase.tradesystem.controller;

import java.io.PrintStream;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gamebase.tradesystem.model.service.ProductService;

import net.sf.json.JSONObject;

@Controller
public class ProductController {
	private ProductService productService;
	private PrintStream out;
	public ProductController(ProductService productService) {
		this.productService=productService;
	}
	
	@RequestMapping(path="/tradesystem/add" , method = RequestMethod.POST)
	@ResponseBody
	public String tsAdd(@RequestParam(value ="form")String form) {
		JSONObject jo =JSONObject.fromObject(form);
		System.out.println(jo);
		System.out.println(form);

		boolean t=productService.add(jo);
//		out.print(t);

		return "addProductPage";
}
	
	@RequestMapping(path= "/tradesystem", method = RequestMethod.GET)
	public String showAddPage() {	
		System.out.println("123");
		return "addProductPage";
	}
}
