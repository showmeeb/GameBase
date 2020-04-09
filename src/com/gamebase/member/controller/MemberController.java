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
@SessionAttributes(names = {"Member",})
public class MemberController {

	private UserDataService uService;

	@Autowired
	public MemberController(UserDataService uService) {
		this.uService = uService;
	}
	
	@RequestMapping(value = "/gosuccess/{id}")
	public String checkRole(@PathVariable("id") Integer uid,ModelMap model) {
		UserData myUserData = uService.getByUserId(uid);
		model.addAttribute("UserData",myUserData);
		return "success";
	}
	
	@RequestMapping(value = "/loginact", method = RequestMethod.POST)
	public String loginAction(@RequestParam("account") String acc, @RequestParam("password") String pwd,
			Map<String, Object> map) {
		if (acc == null || acc.length() == 0) {
			map.put("accerr", "account is required");
		}
		if (pwd == null || pwd.length() == 0) {
			map.put("pwderr", "password is required");
		}
		if (map != null && !map.isEmpty()) {
			return "gologin";
		}
		UserData userData = uService.getByLogin(acc, pwd);
		if (userData != null) {
			return "gosuccess/" + userData.getUserId() ;
		}
		map.put("loginerr", "Account or password error");
		return "gologin";
	}
	
	@RequestMapping(value = "/gologin")
	public String showLoginPage() {		
		return "UserLogin";
	}
}
