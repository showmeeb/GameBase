package com.gamebase.member.model.dao;

import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.gamebase.member.model.UserData;

@Repository
public class UserDataDAO implements IUserData {

	private SessionFactory sessionFactory;

	@Autowired
	public UserDataDAO(@Qualifier(value = "sessionFactory") SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public UserData getByLogin(String account, String password) {
		Query<UserData> query = sessionFactory.getCurrentSession()
				.createQuery("From UserData where account=:acc and password=:pwd", UserData.class);
		query.setParameter("acc", account);
		query.setParameter("pwd", password);
		UserData myUserData = query.uniqueResult();
		if (myUserData != null) {
			return myUserData;
		}
		return null;
	}

	@Override
	public List<UserData> getAllUserData() {
		Query<UserData> allQuery = sessionFactory.getCurrentSession().createQuery("From UserData", UserData.class);
		List<UserData> list = allQuery.list();
		return list;
	}

	@Override
	public boolean checkAccount(String account) {
		Query<UserData> query = sessionFactory.getCurrentSession().createQuery("From UserData where account=:acc",
				UserData.class);
		query.setParameter("acc", account);
		UserData myAccount = query.uniqueResult();
		if (myAccount != null) {
			return true;
		}
		return false;
	}

	@Override
	public UserData getByUserId(Integer userId) {
		Query<UserData> query = sessionFactory.getCurrentSession().createQuery("From UserData where userId=:uid",
				UserData.class);
		query.setParameter("uid", userId);

		return null;
	}

	@Override
	public void deleteUserData(UserData userData) {
		sessionFactory.getCurrentSession().delete(userData);
	}

	@Override
	public void saveUserData(UserData userData) {
		sessionFactory.getCurrentSession().save(userData);
	}

	public UserData getByAccount(String account) {
		Query<UserData> query = sessionFactory.getCurrentSession().createQuery("From UserData where account=:acc",
				UserData.class);
		query.setParameter("acc", account);
		UserData myUserData = query.uniqueResult();
		if (myUserData != null) {
			return myUserData;
		}
		return null;
	}

	@Override
	public void logout(HttpServletRequest request) {
		HttpSession session = request.getSession();

		session.invalidate();
	}

	@Override
	public void setCookie(String account, String password, HttpServletRequest request, HttpServletResponse response) {

		Cookie accCookie = new Cookie("account", account);
		Cookie pwdCookie = new Cookie("password", password);
		System.out.println("accCook: " + accCookie);
		String save = request.getParameter("save");
		System.out.println("checked: " + save);
		if (save != null) {

			accCookie.setMaxAge(60 * 60 * 24 * 7);
			pwdCookie.setMaxAge(60 * 60 * 24 * 7);
			System.out.println("setMaxAge");
		} else {

			accCookie.setMaxAge(0);
			pwdCookie.setMaxAge(0);
			System.out.println("Not setMaxAge");
		}
		response.addCookie(accCookie);
		response.addCookie(pwdCookie);

	}

	@Override
	public void GetCookie(String account, String password, HttpServletRequest request) {

		HttpSession session = request.getSession();
		String sessionId = session.getId();
		System.out.println("sID: " + sessionId);
		
		request.setAttribute("account", account);
		request.setAttribute("password", password);
		
		String acc = (String)request.getAttribute("account");
		String pwd = (String)request.getAttribute("password");
		
		System.out.println("這是acc: " + acc);
		System.out.println("這是pwd: " + pwd);
		Cookie[] cookies = request.getCookies();
		System.out.println("我的cookie:" + cookies);
		if (cookies != null) {
			System.out.println("Cookie在哪裏?" + cookies);
			for (Cookie cookie : cookies) {
				String name = cookie.getName();
				System.out.println("Cookie的名字: " + name);
				System.out.println("我是一個acc: " + acc);
				System.out.println("我是一個pwd: " + pwd);
				if (acc.equals(name)) {
					acc = cookie.getValue();
					System.out.println("acc123" + acc);
				} else if (pwd.equals(name)) {
					pwd = cookie.getValue();
					System.out.println("pwd123" + pwd);
				}
			}
		}

	}
//	public void Cookies(UserData userData, HttpServletRequest request, HttpServletResponse response) {
//		response.setHeader("content-type", "text/html;charset=UTF-8");
//		String account = request.getParameter("account");
//		String password = request.getParameter("password");
//		String save = request.getParameter("save");
//		boolean success = "abc123456".equals(account) && "123456abC".equals(password);
//		
//		if(success) {
//			System.out.println("success");
//		}else {
//			System.out.println("failed");
//		}
//		if(save!=null&&success==true) {
//			Cookie acnt=new Cookie("account",account);
//			acnt.setMaxAge(60*60*24*7);
//			System.out.println("Account Cookie: " + acnt);
//			response.addCookie(acnt);
//			
//			Cookie pwd=new Cookie("password",password);
//			acnt.setMaxAge(60*60*24*7);
//			System.out.println("Pwd Cookie: " + pwd);
//			response.addCookie(pwd);
//			
//		}else {
//			Cookie[] cookies = request.getCookies();
//			if(cookies!=null) {
//				for(Cookie cookie:cookies) {
//					cookie.setMaxAge(0);
//					response.addCookie(cookie);
//				}
//			}
//		}
//	}




}
