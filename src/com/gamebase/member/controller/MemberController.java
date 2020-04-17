package com.gamebase.member.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.gamebase.member.model.Role;
import com.gamebase.member.model.UserData;
import com.gamebase.member.model.UserProfile;
import com.gamebase.member.model.service.UserDataService;

@Controller
@SessionAttributes(names = "UserData")
public class MemberController {

	@Autowired
	private UserDataService uService;

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
			return "LoginViewPage";
		}
		String encryptPwd = uService.encryptString(pwd);
		UserData userData = uService.getByLogin(acc, encryptPwd);

		if (userData != null) {
			model.addAttribute("UserData", userData);
			return "indexPage";
		}
		map.put("loginerr", "Account or password error");
		return "LoginViewPage";
	}

	@RequestMapping(value = "/gotologin", method = RequestMethod.GET)
	public String showLoginPage() {
		System.out.println("got");
		return "LoginViewPage";
	}

	@RequestMapping(value = "/gotoregister")
	public String showRegisterPage() {
		return "RegisterViewPage";
	}

	public String logout() {
		return "indexPage";
	}

	@RequestMapping(value = "/createProfile/{userId}", method = RequestMethod.GET)
	public String showCreateProfilePage(@PathVariable("userId") Integer userId, ModelMap model) {
		UserProfile userProfile = uService.getProfileByUserId(userId);
		model.addAttribute("userProfile", userProfile);
		return "CreateProfilePage";
	}

	@RequestMapping(value = "/updateProfile")
	public String showUpdateProfilePage() {
		return "UpdateProfilePage";
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpServletRequest request) {
		uService.logout(request);
		return "indexPage";
	}

	@RequestMapping(value = "/registact", method = RequestMethod.POST)
	public String insertData(@RequestParam("account") String acc, @RequestParam("password") String pwd,
			@RequestParam("email") String email, Map<String, Object> map, HttpServletRequest request) {
		if (uService.checkAccount(acc)) {
			map.put("registererr", "account already registered.");
		}
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
			return "RegisterViewPage";
		}
		UserData ud = new UserData();
		ud.setAccount(acc);
		String encryptPwd = uService.encryptString(pwd);
		ud.setPassword(encryptPwd);
		ud.setEmail(email);
		uService.saveUserData(ud);
		UserProfile up = new UserProfile();
		up.setUserId(ud.getUserId());
		uService.saveUserPrfile(up);
		// default rank 'Uncertified'
		Role role = new Role(uService.getByLogin(acc, encryptPwd), uService.getByRankId(1));
		uService.changeRole(role);

		HttpSession session = request.getSession();
		Map<String, String> mailMap = uService.mailAction(acc, email);
		session.setAttribute(mailMap.get("registerId"), acc);
		session.setMaxInactiveInterval(600);
		request.setAttribute("userName", acc);
		request.setAttribute("email", email);

		return "SendMailPage";
	}

	@RequestMapping(value = "/gmailregister", method = RequestMethod.GET)
	public String reSendMail(@RequestParam("account") String acc, @RequestParam("email") String email,
			HttpServletRequest request) {

		HttpSession session = request.getSession();
		Map<String, String> mailMap = uService.mailAction(acc, email);
		session.setAttribute(mailMap.get("registerId"), acc);
		session.setMaxInactiveInterval(600);
		request.setAttribute("userName", acc);
		request.setAttribute("email", email);

		return "SendMailPage";
	}

	@RequestMapping(value = "/mailback/{registerId}", method = RequestMethod.GET)
	public String mailback(@PathVariable("registerId") String registerId, HttpServletRequest request) {
		if (registerId == null) {
			return "indexPage";
		}
		String registerName = (String) request.getSession().getAttribute(registerId);
		if (registerName == null || registerName.equals("")) {
			return "indexPage";
		}
		Role role = uService.getRoleByUserId(uService.getByAccount(registerName).getUserId());
		role.setRank(uService.getByRankId(2));
		uService.changeRole(role);
		request.getSession().invalidate();
		return "indexPage";
	}

	@RequestMapping(value = "/insertProfile", method = RequestMethod.POST)
	public String insertProfile(@RequestParam("name") String name,
			@RequestParam(value = "gender", required = false) String gender, @RequestParam("nickName") String nickName,
			@RequestParam("phone") String phone, @RequestParam("age") Integer age,
			@RequestParam("address") String address, Map<String, Object> map, ModelMap model,
			HttpServletRequest request) {

		Map<String, String[]> upMap = request.getParameterMap();

		UserProfile upN = uService.updateUserProfile(upMap);
		model.addAttribute("userProfile", upN);

		System.out.println("瘝");
		return "CreateProfileSuccessPage";
	}

}
