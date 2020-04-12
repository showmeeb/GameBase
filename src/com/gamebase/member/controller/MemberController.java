package com.gamebase.member.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.gamebase.member.model.UserData;
import com.gamebase.member.model.service.UserDataService;

@Controller
@SessionAttributes(names = "UserData")
public class MemberController {

	private UserDataService uService;

	@Autowired
	public MemberController(UserDataService uService) {
		this.uService = uService;
	}

	@RequestMapping(value = "/gosuccess/{id}")
	public String checkRole(@PathVariable("id") Integer uid, ModelMap model) {
//		UserData myUserData = uService.getByUserId(uid);
//		model.addAttribute("UserData",myUserData);
		return "LoginSuccessViewPage";
	}

	@RequestMapping(value = "/loginact", method = RequestMethod.POST)
	public String loginAction(@RequestParam("account") String acc, @RequestParam("password") String pwd,
			Map<String, Object> map, ModelMap model) {
		if (acc == null || acc.length() == 0) {
			map.put("accerr", "account is required");
		}
		if (pwd == null || pwd.length() == 0) {
			map.put("pwderr", "password is required");
		}

		if (map != null && !map.isEmpty()) {
			return "LoginFailedViewPage";
		}
		UserData userData = uService.getByLogin(acc, pwd);
		if (userData != null) {
			model.addAttribute("UserData", userData);
			return "LoginSuccessViewPage";
		}
		map.put("loginerr", "Account or password error");
		return "LoginFailedViewPage";
	}

	
	@RequestMapping(value = "/gologin", method = RequestMethod.GET)
	public String showLoginPage() {		
		return "LoginFailedViewPage";
	}
	@RequestMapping(value = "/goregister")
	public String showRegisterPage() {
		return "RegisterViewPage";
	}

	@RequestMapping(value = "/registact", method = RequestMethod.POST)
	public String insertData(@RequestParam("account") String acc, @RequestParam("password") String pwd,
			@RequestParam("email") String email, Map<String, Object> map, ModelMap model) {
		System.out.println(acc + pwd + email);
		if (acc == null || acc.length() == 0) {
			map.put("accerr", "account is required");
		}
		if (pwd == null || pwd.length() == 0) {
			map.put("pwderr", "password is required");
		}
		if (email == null || email.length() == 0) {
			map.put("emailerr", "email is required");
		}
		if (map != null && !map.isEmpty()) {
			return "RegistFailedViewPage";
		}
		UserData ud = new UserData();
		ud.setAccount(acc);
		ud.setPassword(pwd);
		ud.setEmail(email);
		uService.saveUserData(ud);

		map.put("registerr", "Some column is null!");
		return "RegistFailedViewPage";
	}
}
