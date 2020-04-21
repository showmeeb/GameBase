package com.gamebase.general.model.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.gamebase.general.model.ChatRoom;

@Repository
public class ChatRoomDAO implements IChatRoom {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void insert(ChatRoom chatroom) {
		try {
			sessionFactory.getCurrentSession().save(chatroom);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void select(Integer id) {
		// TODO Auto-generated method stub

	}

}
