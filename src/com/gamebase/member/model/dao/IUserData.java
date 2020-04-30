package com.gamebase.member.model.dao;

import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gamebase.member.model.Friends;
import com.gamebase.member.model.UserData;

public interface IUserData {
	public UserData getByLogin(String account,String password);
	public List<UserData> getAllUserData();
	public boolean checkAccount(String account);
	public UserData getByUserId(Integer userId);
	public void deleteUserData(UserData userData);
	public void saveUserData(UserData userData);
	public void logout(HttpServletRequest request);
	public void GetCookie(String account, String password, HttpServletRequest request);
//	public void setCookie(String account, String password, boolean save, HttpServletRequest request,
//			HttpServletResponse response);
	public List<UserData> getuserbyacinallrank(String ac);
	public List<UserData> getuserbyacinonerank(Integer rank,String ac);
	public void setCookie(String account, String password, String save, HttpServletRequest request,
			HttpServletResponse response);

}
