package com.gamebase.tradesystem.controller;



import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.gamebase.general.model.service.GeneralService;
import com.gamebase.general.model.service.TagSearchService;
import com.gamebase.tradesystem.model.Product;
import com.gamebase.tradesystem.model.service.ProductService;


import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
public class ProductController {
	@Autowired
	private ProductService productService;
	@Autowired
	private GeneralService generalService;
	@Autowired
	private TagSearchService tsService;


	public ProductController(ProductService productService) {
		this.productService = productService;
	}

	@RequestMapping(value = "/mallHome", method = RequestMethod.GET)
	public String mallHome() {

		return "mallHome";
	}
	
	@RequestMapping(path = "/tradesystem/img", method = RequestMethod.POST)
	@ResponseBody
	public String img(@RequestPart(value = "img") MultipartFile formData) {
		System.out.println(formData);
		return generalService.uploadToImgur(formData);
	}
	
	@RequestMapping(path = "/tradesystem/add", method = RequestMethod.POST)
	@ResponseBody
	public JSONObject tsAdd(@RequestParam(value = "form") String form) {
		// System.out.println(productService.add(form));
		
		System.out.println(form);
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
		System.out.println("tsUpdate");
		System.out.println("b:"+b);
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
	public Set<Product> getSearch(@RequestParam(value = "search") String a) {
		
		return tsService.searchProductBackend(a);
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
