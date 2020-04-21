package com.gamebase.general.model.service;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gamebase.general.model.ChatRoom;
import com.gamebase.general.model.WebSocketMessage;
import com.gamebase.general.model.dao.ChatRoomDAO;

@Service
@Transactional
public class ChatRoomService {

	private Integer redisId = 100;
	@Autowired
	private ChatRoomDAO cDao;

	public void saveToRedis(WebSocketMessage msg) {
		Integer sender = Integer.parseInt(msg.getFrom());
		Integer receiver = Integer.parseInt(msg.getTo()[0]);
		String history = msg.getMessage();
		Timestamp time = msg.getTime();

		ChatRoom bean = new ChatRoom(redisId, sender, receiver, history, time);
		cDao.save(bean);
		redisId++;
		System.out.println("cDao.save: " + bean);
	}

	public void selectByRedis(WebSocketMessage msg) {
		System.out.println("===================");
		System.out.println("selectByRedis");
		System.out.println(msg.getFrom());
		System.out.println(msg.getTo()[0]);

		try {
			Integer username = Integer.parseInt(msg.getFrom());
			List<ChatRoom> history = cDao.findBySenderOrReceiver(username, username);
			System.out.println("===================");
			System.out.println("history: " + history);
			for (ChatRoom record : history) {
				System.out.println("record: " + record);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void findAll() {
		try {
			Iterable<ChatRoom> findAll = cDao.findAll();
			System.out.println("===================");
			System.out.println("findAll:");
			System.out.println(findAll);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
