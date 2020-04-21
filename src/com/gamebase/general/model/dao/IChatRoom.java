package com.gamebase.general.model.dao;

import com.gamebase.general.model.ChatRoom;

public interface IChatRoom {

	public void insert(ChatRoom chatroom);
	public void select(Integer id);

}
