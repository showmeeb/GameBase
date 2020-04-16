package com.gamebase.member.model.dao;

import java.util.List;

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
	public String getCookies(String account, String password, HttpServletRequest request, HttpServletResponse response);
}
