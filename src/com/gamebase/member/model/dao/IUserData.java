package com.gamebase.member.model.dao;

import java.util.List;

import com.gamebase.member.model.Friends;
import com.gamebase.member.model.UserData;

public interface IUserData {
	public UserData getByLogin(String account,String password);
	public List<UserData> getAllUserData();
	public boolean checkAccount(String account);
	public UserData getByUserId(Integer userId);
	public void deleteUserData(UserData userData);
	public void saveUserData(UserData userData);
}
