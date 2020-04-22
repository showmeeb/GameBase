package com.gamebase.general.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.annotation.MultipartConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.gamebase.general.model.service.UploadImgService;

@Controller
@MultipartConfig
public class UploadImgController {

	@Autowired
	private UploadImgService uploadImgService;

	@RequestMapping(value = "/uploadImg", method = RequestMethod.POST)
	public String tagSearch(@RequestParam("theFile") MultipartFile theFile, Model model) throws Exception {
		System.out.println("123");
		String imgURL = uploadImgService.uploadImg(theFile);
		System.out.println(imgURL);

		model.addAttribute("imgURL", imgURL);

		return "topBar";
	}

	@RequestMapping(path = "/test", method = RequestMethod.GET)
	public String testPage() {
		System.out.println("1");
		return "testPage";
	}



}
