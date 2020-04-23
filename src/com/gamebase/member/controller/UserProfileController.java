package com.gamebase.member.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;

import com.gamebase.general.model.service.UploadImgService;
import com.gamebase.member.model.UserData;
import com.gamebase.member.model.UserProfile;
import com.gamebase.member.model.service.UserDataService;

@Controller
@SessionAttributes(names = {"UserData","ProfileId"})
public class UserProfileController {
	
	@Autowired
	public UserDataService uService;
	
	@Autowired
	private UploadImgService uploadImgService;
	
	@RequestMapping(path = "/saveName", produces = "application/json", method = RequestMethod.POST)
	@ResponseBody
	public String addName( @RequestBody UserProfile userProfile,
			HttpServletRequest request) {
		UserData ud = (UserData)request.getSession().getAttribute("UserData");
		System.out.println("ud: " + ud.getUserId());
		
		UserProfile up = uService.getProfileByUserId(ud.getUserId());
		System.out.println("upId: " + up.getUserId());
		up.setName(userProfile.getName());
		uService.saveUserPrfile(up);
		System.out.println("updateName="+userProfile.getName());

		request.setAttribute("name", userProfile.getName());
		return "ProfilePage";

	}
	
	@RequestMapping(path = "/savenickName", produces = "application/json", method = RequestMethod.POST)
	@ResponseBody
	public String addNickName( @RequestBody UserProfile userProfile,
			HttpServletRequest request) {
		UserData ud = (UserData)request.getSession().getAttribute("UserData");
		UserProfile up = uService.getProfileByUserId(ud.getUserId());
		up.setNickName(userProfile.getNickName());
		System.out.println("NName: " + userProfile.getNickName());
		uService.saveUserPrfile(up);
		System.out.println("updatenickName="+userProfile.getNickName());
		
		request.setAttribute("nickName", userProfile.getNickName());
		return "ProfilePage";

	}
	
	@RequestMapping(path = "/saveGender", produces = "application/json", method = RequestMethod.POST)
	@ResponseBody
	public String addGender( @RequestBody UserProfile userProfile,
			HttpServletRequest request) {
		UserData ud = (UserData)request.getSession().getAttribute("UserData");
		UserProfile up = uService.getProfileByUserId(ud.getUserId());
		up.setGender(userProfile.getGender());
		System.out.println("Gender: " + userProfile.getGender());
		uService.saveUserPrfile(up);
		
		request.setAttribute("gender", userProfile.getGender());
		return "ProfilePage";

	}
	
	@RequestMapping(path = "/saveAddress", produces = "application/json", method = RequestMethod.POST)
	@ResponseBody
	public String addAddress( @RequestBody UserProfile userProfile,
			HttpServletRequest request) {
		UserData ud = (UserData)request.getSession().getAttribute("UserData");
		UserProfile up = uService.getProfileByUserId(ud.getUserId());
		up.setAddress(userProfile.getAddress());
		System.out.println("Address: " + userProfile.getAddress());
		uService.saveUserPrfile(up);
		
		request.setAttribute("address", userProfile.getAddress());
		return "ProfilePage";

	}
	
	@RequestMapping(path = "/savePhone", produces = "application/json", method = RequestMethod.POST)
	@ResponseBody
	public String addPhone( @RequestBody UserProfile userProfile,
			HttpServletRequest request) {
		UserData ud = (UserData)request.getSession().getAttribute("UserData");
		UserProfile up = uService.getProfileByUserId(ud.getUserId());
		up.setPhone(userProfile.getPhone());
		System.out.println("Phone: " + userProfile.getPhone());
		uService.saveUserPrfile(up);
		
		request.setAttribute("phone", userProfile.getPhone());
		return "ProfilePage";

	}
	
	@RequestMapping(path = "/saveAge", produces = "application/json", method = RequestMethod.POST)
	@ResponseBody
	public String addAge( @RequestBody UserProfile userProfile,
			HttpServletRequest request) {
		UserData ud = (UserData)request.getSession().getAttribute("UserData");
		UserProfile up = uService.getProfileByUserId(ud.getUserId());
		up.setAge(userProfile.getAge());
		System.out.println("Age: " + userProfile.getAge());
		uService.saveUserPrfile(up);
		
		request.setAttribute("age", userProfile.getAge());
		return "ProfilePage";

	}
	
	@RequestMapping(path = "/saveImg", produces = "application/json", method = RequestMethod.POST)
	public String addImg( @RequestBody UserProfile userProfile,
			HttpServletRequest request,Model model) {
		System.out.println("I am in imgaction");
//		String imgURL = uploadImgService.uploadImg(theFile);
		UserData ud = (UserData)request.getSession().getAttribute("UserData");
		UserProfile up = uService.getProfileByUserId(ud.getUserId());
		System.out.println("amgUP: " + up.getUserId());
		up.setImg(userProfile.getImg());
		System.out.println("Img: " + userProfile.getImg());
		uService.saveUserPrfile(up);
		
		
//		model.addAttribute("imgURL",imgURL);
		request.setAttribute("img", userProfile.getImg());
		return "ProfilePage";

	}
	
//	@RequestMapping(value ="/uploadImg", method = RequestMethod.POST)
//	public String tagSearch(@RequestParam("theFile") MultipartFile theFile, Model model,HttpServletRequest request) throws Exception {
//		System.out.println("123");
//		String imgURL = uploadImgService.uploadImg(theFile);
//		System.out.println(imgURL);
//		
//		model.addAttribute("imgURL",imgURL);
//		
//		return "ProfilePage";
//	}
}

