package com.gamebase.general.model.dao;

import java.util.List;

import com.gamebase.general.model.ChatRoom;

public interface IChatRoom {

	public void insert(ChatRoom chatroom);
	public List<ChatRoom> queryTenData(Integer sender, Integer receiver);
	public List<ChatRoom> queryTenDataNext(Integer sender, Integer receiver, Integer pageSize, Integer page);

}
