package com.gamebase.member.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.gamebase.member.model.Rank;
import com.gamebase.member.model.UserData;
import com.gamebase.member.model.UserProfile;
import com.gamebase.member.model.UsersInfo;
import com.gamebase.member.model.service.UserDataService;

@Controller
@SessionAttributes(names = { "loginUser", "userProfile" })
public class MemberController {

	@Autowired
	private UserDataService uService;

//	@RequestMapping(value = "/createProfile/{userId}")
//	public String createProfile(@PathVariable("userId") Integer userId, UserProfile userProfile) {
//		System.out.println("Create");
//		if (userProfile.getProfileId() == null) {
//			UserProfile up = new UserProfile();
//			up.setUserId(userId);
//			uService.saveUserPrfile(up);
//		}
//		return "ProfilePage";
//
//	}

	@RequestMapping(value = "/userProfileCreate", method = RequestMethod.GET)
	public String goProfile() {
		return "ProfilePage";
	}

	@RequestMapping(value = "/updateProfile", produces = "application/json", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> updateProfile(ModelMap model,HttpServletRequest request) {
//		System.out.println("Update123" + userId);

		UsersInfo myUser = (UsersInfo) model.getAttribute("loginUser");
		UserProfile myUp = uService.getProfileByUserId(myUser.getUserId());
		String url = "/GameBase/userProfileCreate";
		if (myUp == null) {
			myUp = new UserProfile();
			myUp.setUserId(myUser.getUserId());

			uService.saveUserPrfile(myUp);
			request.getSession().setAttribute("userProfile", myUp);
			model.addAttribute("userProfile", myUp);

		}
		request.getSession().setAttribute("userProfile", myUp);
		model.addAttribute("userProfile", myUp);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("url", url);
		return map;
	}

//	@RequestMapping(value="/saveProfile",method=RequestMethod.POST)
//	public String saveProfileAction(@RequestParam("userId") Integer userId, @RequestParam("name") String name,
//			@RequestParam("nickName") String nickName, @RequestParam("address") String address,
//			@RequestParam("age") Integer age, @RequestParam(value = "gender", required = false) String gender,
//			@RequestParam("img") String img, @RequestParam("phone") String phone, Map<String, Object> map,
//			ModelMap model,HttpServletResponse response) throws IOException {
//		
//		if(uService.getProfileIdByUserId(userId)==null) {
//			UserProfile sa = new UserProfile(userId, name, gender, nickName, phone, age, address, img);
//			uService.saveUserPrfile(sa);
//			System.out.println("save");
//
//		}else {
//		UserProfile up = uService.getProfileByUserId(userId);		
//		up.setName(name);
//		up.setNickName(nickName);
//		up.setAddress(address);
//		up.setGender(gender);
//		up.setImg(img);
//		up.setPhone(phone);
//		up.setUserId(userId);
//		up.setAge(age);
//		uService.saveUserPrfile(up);
//		System.out.println("update");
//		}
//		model.remove("ProfileId");
//		model.addAttribute("ProfileId", uService.getProfileIdByUserId(userId));
//		System.out.println("success");
//		return "indexPage";
//
//	}

	@RequestMapping(value = "/loginact", method = RequestMethod.POST)
	public String loginAction(@RequestParam("account") String acc, @RequestParam("password") String pwd,
			Map<String, Object> map, ModelMap model, HttpServletRequest request, HttpServletResponse response) {
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
		String save = request.getParameter("save");

//		uService.setCookie(acc, pwd, save, request, response);

		if (userData != null) {
			model.addAttribute("UserData", userData);
			request.getSession().setAttribute("UserData", userData);
			return "indexPage";
		}
		map.put("loginerr", "Account or password error");
		return "LoginViewPage";
	}

	@RequestMapping(value = "/gotologin", method = RequestMethod.GET)
	public String showLoginPage() {
		return "LoginViewPage";
	}

	@RequestMapping(value = "/gotoregister")
	public String showRegisterPage() {
		return "RegisterViewPage";
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
		Rank rk = new Rank();
		rk.setRankId(1);
		UserData ud = new UserData();
		ud.setAccount(acc);
		String encryptPwd = uService.encryptString(pwd);
		ud.setPassword(encryptPwd);
		ud.setEmail(email);

		ud.setRankId(1);
		uService.saveUserData(ud);

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
		System.out.println("rName: " + registerName);
		if (registerName == null || registerName.equals("")) {
			return "indexPage";
		}

		UserData userdata = uService.getByAccount(registerName);
		userdata.setRankId(2);
		uService.saveUserData(userdata);
//		Role role = uService.getRoleByUserId(uService.getByAccount(registerName).getUserId());
//		role.setRank(uService.getByRankId(2));
//		uService.changeRole(role);

		request.getSession().invalidate();
		return "indexPage";
	}

	@RequestMapping(value = "/logout")
	public String logout(HttpServletRequest request, HttpServletResponse response, SessionStatus status) {
		HttpSession session = request.getSession();
		session.removeAttribute("UserData");
		session.removeAttribute("userProfile");
		status.setComplete();

		return "indexPage";
	}

}
