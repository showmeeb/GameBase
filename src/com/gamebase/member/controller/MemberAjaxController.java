package com.gamebase.member.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

//import com.gamebase.member.model.Role;
import com.gamebase.member.model.UserData;
import com.gamebase.member.model.UsersInfo;
import com.gamebase.member.model.service.UserDataService;

@Controller
@SessionAttributes(names = {"loginUser","ProfileId","UserData"})
public class MemberAjaxController {

	@Autowired
	private UserDataService uService;

	@DeleteMapping(value = "/logout")
	@ResponseBody
	public String logoutAction(HttpServletRequest request,SessionStatus sessionStatus) {
		sessionStatus.setComplete();
		HttpSession session = request.getSession();
		session.removeAttribute("UserData");
		session.removeAttribute("loginUser");
		return "logout";
	}
	@GetMapping(path =  {"/Authcode/{authCode}"})
	@ResponseBody
	public String checkAuth(@PathVariable String authCode,HttpServletRequest request){
		String registerName = (String) request.getSession().getAttribute(authCode);
		UserData cUserData = uService.getByAccount(registerName);
		if(cUserData!=null) {
			cUserData.setRankId(2);
			uService.saveUserData(cUserData);
			return "success";
		}
		return "failure";
	}
	
	@RequestMapping(path = "/loginAjax", produces = "application/json", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> loginAction(@RequestBody UserData logindata,Model model,HttpServletRequest request, HttpServletResponse response) {
		String pwd = uService.encryptString(logindata.getPassword());
		Map<String, Object> map = uService.getLogin(logindata.getAccount(), pwd);
		uService.setCookie(logindata.getAccount(), pwd, request, response);
		uService.GetCookie(logindata.getAccount(), pwd, request);
		if((boolean)map.get("status")) {
			model.addAttribute("loginUser", (UsersInfo)map.get("loginUser"));
			model.addAttribute("UserData", (UserData)map.get("UserData"));

			return map;
			
		}
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
		Map<String, String> mailMap = uService.authAction(userdata.getAccount(), userdata.getEmail());
		session.setAttribute(mailMap.get("registerId"), userdata.getAccount());
		session.setMaxInactiveInterval(600);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("status", true);
//		map.put("sendPage", mailMap.get("sendPage"));
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
}
