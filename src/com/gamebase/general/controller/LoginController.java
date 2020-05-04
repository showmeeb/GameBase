package com.gamebase.general.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.mapping.Array;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.gamebase.general.model.Webflow;
import com.gamebase.member.model.UserData;
import com.gamebase.member.model.UsersInfo;
import com.gamebase.member.model.service.UserDataService;

@Controller
@SessionAttributes(names = "loginUser")
public class LoginController {
	private UserDataService uService;


	
	@Autowired
	public LoginController(UserDataService uService) {
		this.uService = uService;
	}

	@PostMapping(path = "/Users/{userAcc}", produces = "application/json")
	@ResponseBody
	public UsersInfo login(@PathVariable String userAcc, String pwd, Model model) {
		System.out.println(userAcc);
		System.out.println(pwd);
		Map<String, String> errors = new HashMap<String, String>();
		if (userAcc == null || userAcc.length() == 0) {
			errors.put("uID", "Wrong userID format");
			System.out.println("Wrong userID format");
			return null;
		}
		if (pwd == null || pwd.length() == 0) {
			errors.put("pwd", "Wrong password format");
			System.out.println("Wrong password format");
			return null;
		}

		if (errors != null && !errors.isEmpty()) {
			return null;
		}
		UsersInfo usersInfo = null;
		UserData userData = null;
		userData = uService.getByLogin(userAcc, pwd);
		if (userData != null) {
			usersInfo = uService.showUserData(userData.getAccount());
			model.addAttribute("loginUser", usersInfo);
//			System.out.println(userData);
//			System.out.println(usersInfo);
			return usersInfo;
		}
		errors.put("loginerr", "Account or password error");
		return null;
	}

	@DeleteMapping(value = "/Users/{account}")
	@ResponseBody
	public String logout(@PathVariable String account, SessionStatus sessionStatus) {
		sessionStatus.setComplete();
//		System.out.println("logout");
		return "logout";
	}
	
	@RequestMapping(value = "/analytic", method = RequestMethod.GET)
	public String analytic() {
		return "analytic";
	}
	
	@RequestMapping(path = "/GameBase/getip", produces = "application/json", method = RequestMethod.POST)
	@ResponseBody
	public boolean getip(HttpServletRequest request) {
			
		String ip = uService.getIp(request);
		Webflow status = uService.checkIpByIpNDate(ip,new SimpleDateFormat("yyyy/MM/dd").format(new Date()));
		System.out.println(ip);
		if(status!=null) {//隞予????>??->?湔
			System.out.println("來過了");
		Webflow Webflow = uService.updateTimes(status);
//		System.out.println("ip:"+Webflow.getIp());
//		System.out.println("times:"+Webflow.getLogtime());
		}else {
			System.out.println("今天第一次");
		Webflow Webflow = uService.insertIp(new Webflow(ip));
//		System.out.println("ip:"+Webflow.getIp());
//		System.out.println("times:"+Webflow.getLogtime());
		}		
		return true;
	}
	
	@RequestMapping(path = "/GameBase/getIpWeek", produces = "application/json", method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> getIpWeek() {
			
//		System.out.println("getNoRepeatIpWeek-----");
		String date = new SimpleDateFormat("yyyy/MM/dd").format(new Date());
		List<Webflow> norepeat = uService.IpnoRepeatWeek(date);
		List<Webflow> repeat = uService.IpRepeatWeek(date);
		Map<String,Object> map= new HashMap<String,Object>();
		map.put("norepeat", norepeat);
		map.put("totalnum", repeat);
		
		return map;
	}
	
	@RequestMapping(path = "/GameBase/getIpDay", produces = "application/json", method = RequestMethod.POST)
	@ResponseBody
	public Map<String,ArrayList> getIpDay() {
			
		System.out.println("getNoRepeatIpDAY-----");
//		String date = new SimpleDateFormat("yyyy/MM/dd").format(new Date());
		ArrayList<String> daylist=new ArrayList<>();
		ArrayList<Integer> timelist=new ArrayList<>();
		Map<String, ArrayList> map= new HashMap<String, ArrayList>();  
		for(int i=6;i>=0;i--) {
			Calendar   cal   =   Calendar.getInstance();
			cal.add(Calendar.DATE, -i);
			String day = new SimpleDateFormat( "yyyy/MM/dd").format(cal.getTime());
			
			List<Webflow> DayNorepeat = uService.IpRepeatDay(day);
			System.out.println("day------"+day);
			Integer times=0;
			Map<String,Integer> data= new HashMap<String,Integer>();  
			for(int j=0;j<DayNorepeat.size();j++) {	
				System.out.println("DayNorepeat.size()"+DayNorepeat.size());
				times+=DayNorepeat.get(j).getLogtime();
			}
			daylist.add(day);
			timelist.add(times);
				
		}
		
		map.put("day", daylist);
		map.put("time", timelist);
		

		
		return map;
	}
}

