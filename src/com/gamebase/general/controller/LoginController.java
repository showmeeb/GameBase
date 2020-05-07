package com.gamebase.general.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
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

import com.gamebase.article.model.ArticleListView;
import com.gamebase.article.model.ArticleTitle;
import com.gamebase.article.model.service.ArticleService;
import com.gamebase.general.model.Webflow;
import com.gamebase.member.model.UserData;
import com.gamebase.member.model.UsersInfo;
import com.gamebase.member.model.service.UserDataService;
import com.gamebase.tradesystem.model.UserOrder;
import com.gamebase.tradesystem.model.service.ProductService;
import com.gamebase.tradesystem.model.service.ShoppingService;

@Controller
@SessionAttributes(names = "loginUser")
public class LoginController {
	private UserDataService uService;
	
	@Autowired
	public ShoppingService sService;
	@Autowired
	public ArticleService aService;
	@Autowired
	public ProductService pService;
	
	
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
		ArrayList<Integer> notimelist=new ArrayList<>();
		Map<String, ArrayList> map= new HashMap<String, ArrayList>();  
		for(int i=6;i>=0;i--) {
			Calendar cal =   Calendar.getInstance();
			cal.add(Calendar.DATE, -i);
			String day = new SimpleDateFormat("yyyy/MM/dd").format(cal.getTime());
			
			List<Webflow> Dayrepeat = uService.IpRepeatDay(day);
			System.out.println("day------"+day);
			Integer times=0;
			Integer noTimes=0;
			List<Webflow> dayNoRepeat = uService.IpnoRepeatDay(day);
			
			for(int j=0;j<Dayrepeat.size();j++) {	
				System.out.println("DayNorepeat.size()"+Dayrepeat.size());
				times+=Dayrepeat.get(j).getLogtime();
				noTimes+=1;
			}
			notimelist.add(noTimes);
			daylist.add(day);
			timelist.add(times);
				
		}
		
		map.put("day", daylist);
		map.put("time", timelist);
		map.put("noTimes", notimelist);

		
		return map;
	}
	
	@RequestMapping(path = "/GameBase/analizeData", produces = "application/json", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> analizeData() {
		// System.out.println("got chekcAcc "+account.getAccount());
		System.out.println("取得數據們");
		List<UserData> dataList = uService.getUserWithoutAdmin();//全部會員數
		List<ArticleTitle> ArticleTitleList = aService.queryAllArticleTitle();//全部文章標題(文章量)
//		List aa = aService.queryEverydayArticle();  SQL先統計完再傳回(失敗) 
//		System.out.println("list : " + list);
		List<UserOrder> notMemOrList = sService.notMemberOrders();//非會員購買數
		List<UserOrder> MemOrList = sService.MemberOrders();//會員訂單數
		List<ArticleListView> MemPosted = aService.getNumOfUserPosteed();//發文會員數
		Integer NumOfXbox = pService.getNumOfXbox();
		Integer NumOfSwitch=pService.getNumOfSwitch();
		Integer getNumOfPs4=pService.getNumOfPs4();
		Integer getNumOfProduct=pService.getNumOfProduct();			
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("members", dataList.size());//全部會員數
		map.put("ArticleTitle", ArticleTitleList.size());//全部文章標題(文章量)
		map.put("notMemOr", notMemOrList.size());//非會員購買數
		map.put("MemOr", MemOrList.size());//會員訂單數
		map.put("MemPosted", MemPosted.size());//發文會員數
		
		map.put("NumOfXbox",NumOfXbox);//xbox商品數
		map.put("NumOfSwitch",NumOfSwitch);//Switch商品數
		map.put("getNumOfPs4",getNumOfPs4);//Ps4商品數
		map.put("getNumOfProduct",getNumOfProduct);//全部商品數
		
//		map.put("members", dataList.size());
		
		return map;
	}
}

