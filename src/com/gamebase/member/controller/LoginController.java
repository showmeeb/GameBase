package com.gamebase.member.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.gamebase.member.model.UserData;
import com.gamebase.member.model.service.UserDataService;

@Controller
@SessionAttributes(names = "UserData")
public class LoginController {
	private UserDataService uService;

	@Autowired
	public LoginController(UserDataService uService) {
		this.uService = uService;
	}

	@PostMapping(path = "/Users/{userID}", produces = "application/json")
	@ResponseBody
	public UserData login(@PathVariable String userID, String pwd, Model model) {
		System.out.println(userID);
		System.out.println(pwd);
		Map<String, String> errors = new HashMap<String, String>();
		if (userID == null || userID.length() == 0) {
			errors.put("uID", "Wrong userID format");
			System.out.println("Wrong userID format");
			return null;
		}
		if (pwd == null || pwd.length() == 0) {
			errors.put("pwd", "Wrong password format");
			System.out.println("Wrong password format");
			return null;
		}

		if (errors != null && !errors.isEmpty()) {
			return null;
		}
		UserData user = null;
		UserData userData = uService.getByLogin(userID, pwd);
		if (userData != null) {
			model.addAttribute("UserData", userData);
			System.out.println(userData);
			return userData;
		}
		errors.put("loginerr", "Account or password error");
		return null;
	}
}
