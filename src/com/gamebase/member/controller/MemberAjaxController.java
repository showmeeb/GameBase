package com.gamebase.member.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

//import com.gamebase.member.model.Role;
import com.gamebase.member.model.UserData;
import com.gamebase.member.model.UserProfile;
import com.gamebase.member.model.UsersInfo;
import com.gamebase.member.model.service.UserDataService;

@Controller
@SessionAttributes(names = { "loginUser", "ProfileId", "UserData" })
public class MemberAjaxController {

	@Autowired
	private UserDataService uService;
	
	@GetMapping(value = "/userInfo/{ID}",produces = "application/json")
	@ResponseBody
	public Map<String,Object> gotInfoByUserId(@PathVariable("ID") Integer userId){
//		System.out.println(userId);
		Map<String,Object> map = new HashMap<String,Object>();
		UsersInfo fUserInfo = uService.showUserData(userId);
		System.out.println(fUserInfo.getAccount());
		map.put("fUserInfo",fUserInfo);
		return map;
	}

	@PostMapping(value = "/Users/GoogleLogin", produces = "application/json")
	@ResponseBody
	public UsersInfo googleLogin(String idTokenStr, Model model) {
		UsersInfo usersLoginBean = uService.googleLogin(idTokenStr);
		if(usersLoginBean != null) {
			model.addAttribute("loginUser", usersLoginBean);
			return usersLoginBean;
		}else {
			return null;
		}
	}

	@DeleteMapping(value = "/logout")
	@ResponseBody
	public String logoutAction(HttpServletRequest request, SessionStatus sessionStatus) {
		sessionStatus.setComplete();
		HttpSession session = request.getSession();
		session.removeAttribute("UserData");
		session.removeAttribute("loginUser");
		return "indexPage";
	}

	@GetMapping(path = { "/Users/{authCode}" })
	public String authCodeCheck(@PathVariable String authCode, HttpServletRequest request) {
		String registerName = (String) request.getSession().getAttribute(authCode);
		UserData cUserData = uService.getByAccount(registerName);
		if (cUserData != null) {
			cUserData.setRankId(2);
			uService.saveUserData(cUserData);
			return "indexPage";
		}
		return "indexPage";
	}

	@GetMapping(path = { "/Authcode/{authCode}" })
	@ResponseBody
	public String checkAuth(@PathVariable String authCode, HttpServletRequest request) {
		String registerName = (String) request.getSession().getAttribute(authCode);
		UserData cUserData = uService.getByAccount(registerName);
		if (cUserData != null) {
			cUserData.setRankId(2);
			uService.saveUserData(cUserData);
			return "success";
		}
		return "failure";
	}

	@RequestMapping(path = "/loginAjax/{save}", produces = "application/json", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> loginAction(@RequestBody UserData logindata,@PathVariable("save") boolean save, Model model, HttpServletRequest request,
			HttpServletResponse response) {
		
		
		String pwd = uService.encryptString(logindata.getPassword());
		uService.setCookie(logindata.getAccount(), pwd, save, request, response);
		uService.GetCookie(logindata.getAccount(), pwd, request);
		
		Map<String, Object> map = uService.getLogin(logindata.getAccount(), pwd);
		if ((boolean) map.get("status")) {
			model.addAttribute("loginUser", (UsersInfo) map.get("loginUser"));
			model.addAttribute("UserData", (UserData) map.get("UserData"));
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
		Date regTime = new Date();
		userdata.setRegiestdate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(regTime.getTime()));
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

	@RequestMapping(path = "/getAllMembers", produces = "application/json", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getAllMembers() {
		// System.out.println("got chekcAcc "+account.getAccount());
		List<UserData> list = uService.getAllUserData();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("members", list);
		return map;
	}

	@RequestMapping(value = "/allMembers", method = RequestMethod.GET)
	public String allMembers() {
		return "allMembers";
	}
	
	@RequestMapping(path = "/GameBase/getuserbyacinallrank", produces = "application/json", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getuserbyacinallrank(@RequestParam("ac") String ac) {
		// System.out.println("got chekcAcc "+account.getAccount());
		System.out.println("c");
		List<UserData> list = uService.getUserByAcInAllRank(ac);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("members", list);
		return map;
	}
	@RequestMapping(path = "/GameBase/getuserbyacinonerank", produces = "application/json", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getuserbyacinonerank(@RequestParam("ac") String ac, @RequestParam("rank") String rank) {
		// System.out.println("got chekcAcc "+account.getAccount());
		System.out.println("b");
		List<UserData> list = uService.getUserByAcInOneRank(ac,Integer.valueOf(rank));
		System.out.println("list : "+list);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("members", list);
		return map;
	}
	
	@RequestMapping(path = "/GameBase/previewUserProfile", produces = "application/json", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> previewuserprofile(@RequestParam("id") String id) {
		// System.out.println("got chekcAcc "+account.getAccount());
		System.out.println("k");
		UserProfile profile = uService.getProfileByUserId(Integer.valueOf(id));
		System.out.println(profile);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("profile", profile);
		return map;
	}
	
}
