package com.gamebase.general.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.annotation.MultipartConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.gamebase.general.model.service.UploadImgService;

@Controller
@MultipartConfig
public class UploadImgController {

	@Autowired
	private UploadImgService uploadImgService;

	@RequestMapping(value = "/uploadImg", method = RequestMethod.POST)

	public String tagSearch(@RequestParam("theFile") MultipartFile theFile, Model model) throws Exception {

		String strURL = uploadImgService.uploadImg(theFile);
		System.out.println(strURL);

		return "searchResults";
	}

}
