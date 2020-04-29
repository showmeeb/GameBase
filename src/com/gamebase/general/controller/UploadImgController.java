package com.gamebase.general.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;

import com.gamebase.general.model.service.GeneralService;
import com.gamebase.member.model.UserProfile;
import com.gamebase.member.model.UsersInfo;
import com.gamebase.member.model.service.UserDataService;

@Controller
@MultipartConfig
@SessionAttributes(names = { "userProfile" })
public class UploadImgController {

	@Autowired
	private GeneralService generalService;
	@Autowired
	private UserDataService uService;

	@RequestMapping(path = "/uploadImg", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> tagSearch(@RequestParam("theFile") MultipartFile theFile, ModelMap model,HttpServletRequest request) throws Exception {
		Map<String, Object> map = new  HashMap<String, Object>();
		UserProfile myUp = (UserProfile) request.getSession().getAttribute("userProfile");
		
//		String imgURL = generalService.uploadToImgur(theFile);
//		System.out.println(imgURL);
//
//		model.addAttribute("imgURL", imgURL);
//
		String img = generalService.UserProfileuploadToImgur(theFile);
		if (img.equals("false")) {
			map.put("status", false);
			return map;
		} else {
			System.out.println("imgUrl: " + img);
			
			if (myUp != null) {
				myUp.setImg(img);
				uService.saveUserPrfile(myUp);
				UsersInfo myInfo = uService.showUserData(myUp.getUserId());
				map.put("status", true);
				map.put("img", img);
				map.put("loginUser", myInfo);
				return map;
			}

			return map;

		}
	}

	@RequestMapping(path = "/test", method = RequestMethod.GET)
	public String testPage() {
		System.out.println("1");
		return "testPage";
	}

}
