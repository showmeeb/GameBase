package com.gamebase.member.model.dao;

import com.gamebase.member.model.Friends;

public interface IFriends {

	public Friends insert(Friends bean);
	public Friends selectByEachId(Integer userId, Integer friendId);
}
