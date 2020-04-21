package com.gamebase.general.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.gamebase.member.model.UserData;
import com.gamebase.member.model.UsersInfo;
import com.gamebase.member.model.service.UserDataService;

@Controller
@SessionAttributes(names = "loginUser")
public class LoginController {
	private UserDataService uService;
	
	@Autowired
	public LoginController(UserDataService uService) {
		this.uService = uService;
	}

	@PostMapping(path = "/Users/{userAcc}", produces = "application/json")
	@ResponseBody
	public UsersInfo login(@PathVariable String userAcc, String pwd, Model model) {
		System.out.println(userAcc);
		System.out.println(pwd);
		Map<String, String> errors = new HashMap<String, String>();
		if (userAcc == null || userAcc.length() == 0) {
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
		UsersInfo usersInfo = null;
		UserData userData = null;
		userData = uService.getByLogin(userAcc, pwd);
		if (userData != null) {
			usersInfo = uService.showUserData(userData.getAccount());
			model.addAttribute("loginUser", usersInfo);
//			System.out.println(userData);
//			System.out.println(usersInfo);
			return usersInfo;
		}
		errors.put("loginerr", "Account or password error");
		return null;
	}

	@DeleteMapping(value = "/Users/{account}")
	@ResponseBody
	public String logout(@PathVariable String account, SessionStatus sessionStatus) {
		sessionStatus.setComplete();
//		System.out.println("logout");
		return "logout";
	}

}
