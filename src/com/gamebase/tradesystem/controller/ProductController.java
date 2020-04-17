package com.gamebase.tradesystem.controller;



import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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


	public ProductController(ProductService productService) {
		this.productService = productService;
	}

	@RequestMapping(path = "/tradesystem/add", method = RequestMethod.POST)
	@ResponseBody
	public JSONObject tsAdd(@RequestParam(value = "form") String form) {
		// System.out.println(productService.add(form));
		return productService.add(form);
	}

	@RequestMapping(path = "/tradesystem/query", method = RequestMethod.POST)
	@ResponseBody
	public JSONArray tsQuery() {
		System.out.println("tsQuery");
		return productService.query();
	}

	@RequestMapping(path = "/tradesystem/delete", method = RequestMethod.POST)
	@ResponseBody
	public JSONObject tsDelete(@RequestParam(value = "d") String d) {
		return productService.delete(Integer.valueOf(d));
	}

	@RequestMapping(path = "/tradesystem/update", method = RequestMethod.POST)
	@ResponseBody
	public JSONObject tsUpdate(@RequestParam(value = "b") String b) {
		return productService.update(b);
	}

	@RequestMapping(path = "/tradesystem/search", method = RequestMethod.GET)
	@ResponseBody
	public JSONArray search(@RequestParam(value = "sh") String a) {
		System.out.println(a);
		return productService.search(a);
	}

	@RequestMapping(path = "/tradesystem/getSearch", method = RequestMethod.POST)
	@ResponseBody
	public JSONArray getSearch(@RequestParam(value = "search") String a) {
		System.out.println(a);
		return productService.getSearch(a);
	}

	@RequestMapping(path = "/tradesystem", method = RequestMethod.GET)
	public String showAddPage() {
		System.out.println("2");
		return "addProductPage";
	}

	@RequestMapping(path = "/mainProduct", method = RequestMethod.GET)
	public String showMainPage() {
		System.out.println("3");
		return "mainProductPage";
	}

	@RequestMapping(path = "/productDetail", method = RequestMethod.GET)
	public String productDetail(@RequestParam("prodId") String prodId, Model model) {
		String product = productService.getProductById(prodId);
		model.addAttribute("product", product);
		
		System.out.println(product);

		return "productDetail";
	}
}
