package com.gamebase.general.model.dao;

import java.util.List;

import javax.persistence.NoResultException;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.gamebase.general.model.ChatRoom;

@Repository
public class ChatRoomDAO implements IChatRoom {

	@Autowired
	private SessionFactory sessionFactory;

	private final String SELECT_BY_SENDER_RECEIVER = "From com.gamebase.general.model.ChatRoom ChatRoom where (ChatRoom.sender = :sender and ChatRoom.receiver = :receiver) OR (ChatRoom.sender = :sender1 and ChatRoom.receiver = :receiver1) Order by ChatRoom.id Desc";

	@Override
	public void insert(ChatRoom chatroom) {
		try {
			sessionFactory.getCurrentSession().save(chatroom);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public List<ChatRoom> queryTenData(Integer sender, Integer receiver) {
		List<ChatRoom> result = null;
		try {
			result = (List<ChatRoom>) sessionFactory.getCurrentSession().createQuery(SELECT_BY_SENDER_RECEIVER)
					.setParameter("sender", sender).setParameter("receiver", receiver).setParameter("sender1", receiver)
					.setParameter("receiver1", sender).setFirstResult(0).setMaxResults(10).getResultList();
		} catch (NoResultException e) {
			e.printStackTrace();
			return null;
		}
		return result;
	}

}
