package com.gamebase.member.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
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

import com.gamebase.article.model.ArticleContent;
import com.gamebase.article.model.ArticleListView;
import com.gamebase.article.model.ArticleTitle;
import com.gamebase.article.model.service.ArticleService;
import com.gamebase.general.model.service.GeneralService;
import com.gamebase.member.model.ChangePwd;
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
	@Autowired
	public GeneralService gService;
	@Autowired
	public ArticleService aService;

	@PostMapping(value = "/regetrank", produces = "application/json")
	@ResponseBody
	public Map<String,Object> reget(ModelMap model){
		Map<String, Object> map = new HashMap<String, Object>();
		UsersInfo login = (UsersInfo) model.get("loginUser");
		UsersInfo regetlogininfo = uService.showUserData(login.getAccount());
//		System.out.println(regetlogininfo.getRankId());
		model.addAttribute("loginUser", regetlogininfo);
		map.put("loginUser", regetlogininfo);
		return map;
	}
	
	@PostMapping(value = "/changePwd", produces = "application/json")
	@ResponseBody
	public Map<String,Object> pwdChange2(@RequestBody ChangePwd pwddata, ModelMap model){
//		System.out.println("new pwd="+pwddata.getNewPwd()+", check pwd="+pwddata.getChPwd());
		Map<String, Object> map = new HashMap<String, Object>();
		if(pwddata.getNewPwd().equals(pwddata.getChPwd())) {			
			UsersInfo login = (UsersInfo) model.get("loginUser");
			UserData loginUserData = uService.getByUserId(login.getUserId());
			String encryptPwd = uService.encryptString(pwddata.getNewPwd());
			loginUserData.setPassword(encryptPwd);
			uService.saveUserData(loginUserData);
			map.put("status", true);
			return map;
		}
		map.put("status", true);
		return map;
		
	}
	
	@PostMapping(value = "/updatePassword", produces = "application/json")
	@ResponseBody
	public Map<String, Object> pwdchange(@RequestBody ChangePwd pwdupdate, ModelMap model) {
		System.out.println("oldpwd:" + pwdupdate.getOldPwd()+" newpwd:" + pwdupdate.getNewPwd());
		Map<String, Object> map = new HashMap<String, Object>();
		String encryptoldpwd = uService.encryptString(pwdupdate.getOldPwd());
		String encryptnewpwd = uService.encryptString(pwdupdate.getNewPwd());
		UsersInfo login = (UsersInfo) model.get("loginUser");
		UserData loginUserData = uService.getByUserId(login.getUserId());
		if(encryptoldpwd.equals(loginUserData.getPassword())) {
			loginUserData.setPassword(encryptnewpwd);
			uService.saveUserData(loginUserData);
			map.put("status", true);
			return map;
		}else {
			map.put("status",false);
		}
		return map;
	}

	@PostMapping(value = "/resetPwd", produces = "application/json")
	@ResponseBody
	public Map<String, Object> resetPwd(@RequestBody UserData reset) {
		Map<String, Object> map = new HashMap<String, Object>();
		UserData result = uService.checkAccWithEmail(reset);
		if (result != null) {
			Map<String, String> backmap = uService.randomPwd();
			result.setPassword(backmap.get("encryptpwd"));
			uService.saveUserData(result);
			reset.setPassword(backmap.get("pwd"));
			uService.sendPwdEmail(reset);
			map.put("status", true);
			return map;
		}
		map.put("status", false);
		return map;
	}

	@GetMapping(value = "/userInfo/{ID}", produces = "application/json")
	@ResponseBody
	public Map<String, Object> gotInfoByUserId(@PathVariable("ID") Integer userId) {
//		System.out.println(userId);
		Map<String, Object> map = new HashMap<String, Object>();
		UsersInfo fUserInfo = uService.showUserData(userId);
		System.out.println(fUserInfo.getAccount());
		map.put("fUserInfo", fUserInfo);
		return map;
	}

	@PostMapping(value = "/Users/GoogleLogin", produces = "application/json")
	@ResponseBody
	public UsersInfo googleLogin(String idTokenStr, Model model) {
		UsersInfo usersLoginBean = uService.googleLogin(idTokenStr);
		if (usersLoginBean != null) {
			model.addAttribute("loginUser", usersLoginBean);
			return usersLoginBean;
		} else {
			return null;
		}
	}

	@DeleteMapping(value = "/logout")
	@ResponseBody
	public String logoutAction(HttpServletRequest request, SessionStatus sessionStatus) {
		sessionStatus.setComplete();
		HttpSession session = request.getSession();
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
	public Map<String, Object> loginAction(@RequestBody UserData logindata, @PathVariable("save") boolean save,
			Model model, HttpServletRequest request, HttpServletResponse response) {
		uService.setCookie(logindata.getAccount(), logindata.getPassword(), save, request, response);
		String pwd = uService.encryptString(logindata.getPassword());
		Map<String, Object> map = uService.getLogin(logindata.getAccount(), pwd);
		int numpwd = 0;
		try {
			numpwd = Integer.parseInt(logindata.getPassword());
			map.put("resetPwd",true);
		}catch(Exception e) {
			
		}
		if ((boolean) map.get("status")) {
			model.addAttribute("loginUser", (UsersInfo) map.get("loginUser"));
			return map;
		}
		return map;
	}


	@RequestMapping(path = "/loginAjax", method = RequestMethod.GET)
	public String showLoginPage() {
		return "LoginViewPageAjax";
	}


	@RequestMapping(path = "/loginAjax", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> loginPageShow(HttpServletRequest request) {
		Cookie[] cookies = request.getCookies();

		String acc="",pwd="",rm="";

		if (cookies != null) {
			for (Cookie cookie : cookies) {
				String name = cookie.getName();

				if ("account".equals(name)) {
					acc = cookie.getValue();
					request.getSession().setAttribute("account", acc);

				} else if ("password".equals(name)) {
					pwd = cookie.getValue();
					request.getSession().setAttribute("password", pwd);

				} else if("rm".equals(name)) {
					rm = cookie.getValue();

					
				}
			}
		}

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("account", acc);
		map.put("password",pwd);
		map.put("rm",rm);

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
	
	@RequestMapping(value = "/memberCenter", method = RequestMethod.GET)
	public String memberCenter() {
		return "memberCenter";
	}

	@RequestMapping(value = "/goOp", method = RequestMethod.GET)
	public String goop() {
		return "opPage";
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
		List<UserData> list = uService.getUserByAcInOneRank(ac, Integer.valueOf(rank));
		System.out.println("list : " + list);
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
		UserData userdata = uService.getByUserId(Integer.valueOf(id));
		List<ArticleListView> articles = aService.queryMyArticle(Integer.valueOf(id));
		List<ArticleListView> contents = aService.queryMyContent(Integer.valueOf(id));
		System.out.println(profile);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("profile", profile);
		map.put("articles", articles);
		map.put("contents", contents);
		map.put("userdata", userdata);
		
		return map;
	}

	@RequestMapping(path = "/saveName", produces = "application/json", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> addName(@RequestBody UserProfile userProfile, HttpServletRequest request) {

		UserProfile up = (UserProfile) request.getSession().getAttribute("userProfile");
		System.out.println("upId: " + up.getUserId());
		String name = userProfile.getName();
		up.setName(userProfile.getName());
		uService.saveUserPrfile(up);
		System.out.println("updateName=" + userProfile.getName());

		request.setAttribute("userProfile", up);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("name", name);
		return map;

	}

	@RequestMapping(path = "/savenickName", produces = "application/json", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> addNickName(@RequestBody UserProfile userProfile, HttpServletRequest request) {

		UserProfile up = (UserProfile) request.getSession().getAttribute("userProfile");
		String nickName = userProfile.getNickName();
		up.setNickName(userProfile.getNickName());
		System.out.println("NName: " + userProfile.getNickName());
		uService.saveUserPrfile(up);
		System.out.println("updatenickName=" + userProfile.getNickName());

		request.setAttribute("userProfile", up);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("nickName", nickName);
		return map;

	}

	@RequestMapping(path = "/saveGender", produces = "application/json", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> addGender(@RequestBody UserProfile userProfile, HttpServletRequest request) {

		UserProfile up = (UserProfile) request.getSession().getAttribute("userProfile");
		String gender = userProfile.getGender();
		up.setGender(userProfile.getGender());
		System.out.println("Gender: " + userProfile.getGender());
		uService.saveUserPrfile(up);

		request.setAttribute("userProfile", up);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("gender", gender);
		return map;

	}

	@RequestMapping(path = "/saveAddress", produces = "application/json", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> addAddress(@RequestBody UserProfile userProfile, HttpServletRequest request) {

		UserProfile up = (UserProfile) request.getSession().getAttribute("userProfile");
		String address = userProfile.getAddress();
		up.setAddress(userProfile.getAddress());
		System.out.println("Address: " + userProfile.getAddress());
		uService.saveUserPrfile(up);

		request.setAttribute("userProfile", up);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("address", address);
		return map;

	}

	@RequestMapping(path = "/savePhone", produces = "application/json", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> addPhone(@RequestBody UserProfile userProfile, HttpServletRequest request) {

		UserProfile up = (UserProfile) request.getSession().getAttribute("userProfile");
		String phone = userProfile.getPhone();
		up.setPhone(userProfile.getPhone());
		System.out.println("Phone: " + userProfile.getPhone());
		uService.saveUserPrfile(up);

		request.setAttribute("userProfile", up);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("phone", phone);
		return map;

	}

	@RequestMapping(path = "/saveAge", produces = "application/json", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> addAge(@RequestBody UserProfile update, HttpServletRequest request) {

		UserProfile myUp = (UserProfile) request.getSession().getAttribute("userProfile");
		String age = String.valueOf(update.getAge());
		myUp.setAge(update.getAge());
		System.out.println("Age: " + update.getAge());
		uService.saveUserPrfile(myUp);

		request.setAttribute("userProfile", myUp);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("age", age);
		return map;

	}

	@RequestMapping(value = "/userProfileCreate", method = RequestMethod.GET)
	public String goProfile() {
		return "ProfilePage";
	}

	@RequestMapping(value = "/updateProfile", produces = "application/json", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> updateProfile(ModelMap model, HttpServletRequest request) {

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


	@RequestMapping(path = "/GameBase/getNewMemberWeek", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public Map<String, Object> getNewMemberWeek() {
//		System.out.println("-------------------got new users ");
		List<UserData> data = uService.getAllUserData();//要改!!!!!!!!!!!!!!!
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("users", data);
//		System.out.println("new users: " + map.get("users"));
		return map;
	}
	
	@RequestMapping(path = "/GameBase/MemberUpgradeRank", method = RequestMethod.POST)
	@ResponseBody
	public String payBill(@RequestParam("userId") String userId) {

		return uService.processRankOrder(Integer.valueOf(userId));
	}
//	@RequestMapping(path = "/shoppingCart/test", method = RequestMethod.POST)
//	@ResponseBody
//	public String test(@RequestParam(value ="form") String form,@RequestParam(value = "items1") String items1) {
//		System.out.println("form:"+form);
//		System.out.println("items1:"+items1);
//		return "yes";
//	}
	
	@RequestMapping(path = "/GameBase/rankOrderStatus", method = RequestMethod.POST)
	@ResponseBody
	public void orderStatus(HttpServletRequest request) {
		String rtnCode = request.getParameter("RtnCode");
		String uuid = request.getParameter("MerchantTradeNo");
		String userId = request.getParameter("CustomField1");
		System.out.println("RtnCode:"+rtnCode);
//		System.out.println("orderPhone:"+orderPhone);
//		System.out.println("orderAddress:"+orderAddress);
//		System.out.println("userId:"+userId);
//		System.out.println("uuId:"+uuId);
//		System.out.println("orderDate:"+orderDate);
//		System.out.println("orderPrice:"+orderPrice);
		
		uService.rankOrderStatus(Integer.parseInt(rtnCode),Integer.parseInt(userId));
		System.out.println("付款成功!!");
	}
	
	@RequestMapping(path = "/GameBase/getUserWithRank", produces = "application/json", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getUserWithRank() {
		// System.out.println("got chekcAcc "+account.getAccount());
		System.out.println("查詢管理員以外的會員");
		List<UserData> list = uService.getUserWithoutAdmin();
//		System.out.println("list : " + list);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("members", list);
		return map;
	}
	

}
