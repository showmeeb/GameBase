package com.gamebase.member.controller;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Collections;
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

import com.gamebase.member.model.MailInfo;
import com.gamebase.member.model.Role;
import com.gamebase.member.model.UserData;
import com.gamebase.member.model.dao.MailSender;
import com.gamebase.member.model.service.UserDataService;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken.Payload;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;

@Controller
@SessionAttributes(names = "UserData")
public class MemberController {
	private static String client_id = "982957556355-9h99fuvvivi52g599iucre1v04ktheh0.apps.googleusercontent.com";

	@Autowired
	private UserDataService uService;

	@RequestMapping(value = "/googleVerify", method = RequestMethod.POST)
	public boolean verifyToken(String idtokenstr,ModelMap model) {
		System.out.println(idtokenstr);
		GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(
				new NetHttpTransport(), JacksonFactory.getDefaultInstance())
				.setAudience(Collections.singletonList(client_id)).build();
		GoogleIdToken idToken = null;
		try {
			idToken = verifier.verify(idtokenstr);
		} catch (GeneralSecurityException e) {
			System.out.println("GeneralSecurityException");
		} catch (IOException e) {
			System.out.println("IOException");
		}
		if (idToken != null) {
			System.out.println("success.");
			Payload payload = idToken.getPayload();
			String userId = payload.getSubject();
			System.out.println("User ID: " + userId);
			String email = payload.getEmail();
			System.out.println("email: " + email);
			
			if(uService.checkAccount(userId)) {
				UserData gmailUser = uService.getByAccount(userId);
				model.addAttribute("UserData", gmailUser);
			}else {
				uService.saveUserData(new UserData(userId,email));
				UserData gmailUser = uService.getByAccount(userId);
				Role role = new Role(gmailUser, uService.getByRankId(2));
				uService.changeRole(role);
				
				model.addAttribute("UserData", gmailUser);
			}
			
			return true;
//			boolean emailVerified = Boolean.valueOf(payload.getEmailVerified());
//			String name = (String) payload.get("name");
//			System.out.println("name: " + name);
//			String pictureUrl = (String) payload.get("picture");
//			String locale = (String) payload.get("locale");
//			String familyName = (String) payload.get("family_name");
//			String givenName = (String) payload.get("given_name");
		} else {
			System.out.println("Invalid ID token.");
		}
		return false;
		
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
			return "LoginViewPage";
		}
		UserData userData = uService.getByLogin(acc, pwd);
		if (userData != null) {
			model.addAttribute("UserData", userData);
			return "indexPage";
		}
		map.put("loginerr", "Account or password error");
		return "LoginViewPage";
	}

	@RequestMapping(value = "/gologin", method = RequestMethod.GET)
	public String showLoginPage() {
		return "LoginViewPage";
	}

	@RequestMapping(value = "/goregister")
	public String showRegisterPage() {
		return "RegisterViewPage";
	}
	
	@RequestMapping(value = "/index")
	public String goindex() {
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
		ud.setPassword(pwd);
		ud.setEmail(email);
		uService.saveUserData(ud);
		// default rank 'Uncertified'
		Role role = new Role(uService.getByLogin(acc, pwd), uService.getByRankId(1));
		uService.changeRole(role);

		int i = (int) (Math.random() * (99999999 - 1000 + 1) + 1000);
		String registerId = "" + i;
		System.out.println(registerId);
		String url = "http://localhost:8080/GameBase/mailback/" + registerId;

		HttpSession session = request.getSession();
		session.setAttribute(registerId, acc);
		session.setMaxInactiveInterval(600);

		String content = acc + "(" + email + "),您好<br/>感谢您註冊GameBase!<br/>" + "<b>驗證您的註冊信箱</b><br/>請點擊鏈結來確認您的註冊<br/>"
				+ "<a href='" + url + "'>確認!請點擊這裡來驗證您的信箱</a><br/>"
				+ "如果您不能點擊上述標籤為“確認！”的鏈接，您還可以通過複製（或輸入）下面的URL到地址欄中來驗證您的郵件地址。" + "<a href='" + url + "'>" + url
				+ "</a><br/>" + "如果您認為這是垃圾郵件，請忽略此郵件。";

		MailInfo mailInfo = new MailInfo();

		mailInfo.setMailSmtpHost("smtp.gmail.com");
		mailInfo.setFromAddress("z0983177929@gmail.com");
		mailInfo.setToAddress(email);
		mailInfo.setMailSmtpPort("587");
		mailInfo.setUserName("z0983177929@gmail.com");
		mailInfo.setPassword("K98david");
		mailInfo.setValidate(true);
		mailInfo.setSubject("GameBase verification");
		mailInfo.setContent(content);

		MailSender.sendMail(mailInfo, true);
		request.setAttribute("userName", acc);
		request.setAttribute("email", email);

		return "SendMailPage";
	}

	@RequestMapping(value = "/gmailregister", method = RequestMethod.POST)
	public String reSendMail(@RequestParam("account") String acc, @RequestParam("email") String email,
			HttpServletRequest request) {

		String registerId = "" + Math.random() * Math.random();
		String url = "http://localhost:8080/GameBase/mailback/" + registerId;

		HttpSession session = request.getSession();
		session.setAttribute(registerId, acc);
		session.setMaxInactiveInterval(600);

		String content = acc + "(" + email + "),您好<br/>感谢您註冊GameBase!<br/>" + "<b>驗證您的註冊信箱</b><br/>請點擊鏈結來確認您的註冊<br/>"
				+ "<a href='" + url + "'>確認!請點擊這裡來驗證您的信箱</a><br/>"
				+ "如果您不能點擊上述標籤為“確認！”的鏈接，您還可以通過複製（或輸入）下面的URL到地址欄中來驗證您的郵件地址。" + "<a href='" + url + "'>" + url
				+ "</a><br/>" + "如果您認為這是垃圾郵件，請忽略此郵件。";

		MailInfo mailInfo = new MailInfo();

		mailInfo.setMailSmtpHost("smtp.gmail.com");
		mailInfo.setFromAddress("z0983177929@gmail.com");
		mailInfo.setToAddress(email);
		mailInfo.setMailSmtpPort("587");
		mailInfo.setUserName("z0983177929@gmail.com");
		mailInfo.setPassword("K98david");
		mailInfo.setValidate(true);
		mailInfo.setSubject("GameBase verification");
		mailInfo.setContent(content);

		MailSender.sendMail(mailInfo, true);
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
}
