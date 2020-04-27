package com.gamebase.member.model.service;

import java.util.List;

import com.gamebase.member.model.UserData;

public interface IUserDataService {
	public UserData getByLogin(String account,String password);
	public List<UserData> getAllUserData();
	public boolean checkAccount(String account);
	public UserData getByUserId(Integer userId);
	public void deleteUserData(UserData userData);
	public void saveUserData(UserData userData);
	public List<UserData> getFriendList(Integer userId);
	public List<UserData> getuserbyacinallrank(String ac);
	public List<UserData> getuserbyacinonerank(Integer rank,String ac);
}
