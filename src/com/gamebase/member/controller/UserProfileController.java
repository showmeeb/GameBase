package com.gamebase.member.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;

import com.gamebase.general.model.service.GeneralService;
import com.gamebase.member.model.UserProfile;
import com.gamebase.member.model.service.UserDataService;

@Controller
@SessionAttributes(names = { "UserData", "ProfileId" , "userProfile" })
public class UserProfileController {

	@Autowired
	public UserDataService uService;
	@Autowired
	public GeneralService gService;

	@RequestMapping(path = "/saveName", produces = "application/json", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> addName(@RequestBody UserProfile userProfile, HttpServletRequest request) {

		UserProfile up = (UserProfile)request.getSession().getAttribute("userProfile");
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
		
		UserProfile up = (UserProfile)request.getSession().getAttribute("userProfile");
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
		
		UserProfile up = (UserProfile)request.getSession().getAttribute("userProfile");
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
	
		UserProfile up = (UserProfile)request.getSession().getAttribute("userProfile");
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
		
		UserProfile myUp = (UserProfile)request.getSession().getAttribute("userProfile");
		String age= String.valueOf(update.getAge());
		myUp.setAge(update.getAge());
		System.out.println("Age: " + update.getAge());
		uService.saveUserPrfile(myUp);

		request.setAttribute("userProfile", myUp);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("age", age);
		return map;

	}

//	@RequestMapping(path = "/saveImg", method = RequestMethod.POST)
//	public Map<String, Object> addImg(@RequestBody UserProfile userProfile,@RequestParam("theFile") MultipartFile theFile,
//			HttpServletRequest request, Model model) throws IOException {
//		System.out.println("I am in imgaction");
//
//		String imgURL = gService.uploadToImgur(theFile);
////		System.out.println("myImg: " + imgURL);
//
//		UserProfile up = (UserProfile)request.getSession().getAttribute("userProfile");
//		
//		up.setImg(imgURL);
//		System.out.println("Img: " + userProfile.getImg());
//		uService.saveUserPrfile(up);
//
////		model.addAttribute("imgURL", imgURL);
//		request.setAttribute("img", userProfile.getImg());
//		Map<String, Object> map = new HashMap<String, Object>();
//		map.put("img", imgURL);
//		return map;
//
//	}

//	@RequestMapping(value = "/saveImg", method = RequestMethod.POST)
//	public String addImg(@RequestParam("theFile") MultipartFile theFile, Model model) throws Exception {
//		System.out.println("123");
//		String imgURL = gService.uploadToImgur(theFile);
//		System.out.println(imgURL);
//
//		model.addAttribute("imgURL", imgURL);
//
//		return "ProfilePage";
//	}
}
