package com.gamebase.member.model.dao;

import java.util.List;

import com.gamebase.member.model.UsersInfo;

public interface IUsersInfoDAO {

	public abstract UsersInfo selectById(String account);
	public abstract UsersInfo selectByNo(Integer userId);
	public abstract List<UsersInfo> getFriendList(Integer userNo);
}
