package com.gamebase.article.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.gamebase.member.model.service.UserDataService;

@Controller
@SessionAttributes(names = "UserData")
public class TestUserDataController {

	private UserDataService uService;

	@Autowired
	public TestUserDataController(UserDataService uService) {
		this.uService = uService;
	}
	
	@RequestMapping(value = "/Testgosuccess/{id}")
	public String checkRole(@PathVariable("id") Integer uid,ModelMap model) {
//		UserData myUserData = uService.getByUserId(uid);
//		model.addAttribute("UserData",myUserData);
		return "LoginSuccessViewPage";
	}
	
}
