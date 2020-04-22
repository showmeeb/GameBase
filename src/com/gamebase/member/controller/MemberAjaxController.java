package com.gamebase.member.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

//import com.gamebase.member.model.Role;
import com.gamebase.member.model.UserData;
import com.gamebase.member.model.service.UserDataService;

@Controller
public class MemberAjaxController {

	@Autowired
	private UserDataService uService;

	
	
	@RequestMapping(path = "/loginAjax", produces = "application/json", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> loginAction(@RequestBody UserData logindata) {
		String pwd = uService.encryptString(logindata.getPassword());
		Map<String, Object> map = uService.getLogin(logindata.getAccount(), pwd);
		return map;
	}

	@RequestMapping(path = "/checkAcc", produces = "application/json", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> checkAccount(@RequestParam("account") String account) {
		// System.out.println("got chekcAcc "+account.getAccount());
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result", uService.checkAccount(account));
		return map;
	}

	@RequestMapping(path = "/checkAcc", produces = "application/json", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> checkAccount(@RequestBody UserData account) {
		// System.out.println("got chekcAcc "+account.getAccount());
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result", uService.checkAccount(account.getAccount()));
		return map;
	}

	@RequestMapping(path = { "/registerAjax" }, produces = "application/json", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> registerAction(@RequestBody UserData userdata, HttpServletRequest request) {
//		System.out.println("got here"+userdata.getAccount()+userdata.getPassword()+userdata.getEmail());
		String encryptPwd = uService.encryptString(userdata.getPassword());
		userdata.setPassword(encryptPwd);
		userdata.setRankId(1);
		uService.saveUserData(userdata);
		HttpSession session = request.getSession();
		Map<String, String> mailMap = uService.mailAction(userdata.getAccount(), userdata.getEmail());
		session.setAttribute(mailMap.get("registerId"), userdata.getAccount());
		session.setMaxInactiveInterval(600);
		request.setAttribute("userName", userdata.getAccount());
		request.setAttribute("email", userdata.getEmail());
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("status", true);
		map.put("sendPage", mailMap.get("sendPage"));
		return map;
	}

	@RequestMapping(value = "/loginAjax", method = RequestMethod.GET)
	public String showLoginPage() {
		return "LoginViewPageAjax";
	}
	
	@RequestMapping(value = "/registerAjax", method = RequestMethod.GET)
	public String showRegisterPage() {
		return "RegisterViewPageAjax";
	}
	
	@RequestMapping(path = "/getAllMembers", produces = "application/json", method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> getAllMembers() {
		// System.out.println("got chekcAcc "+account.getAccount());
		List<UserData> list=uService.getAllUserData();
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("members", list);
		return map;
	}
	
	@RequestMapping(value = "/allMembers", method = RequestMethod.GET)
	public String allMembers() {
		return "allMembers";
	}
}
