package com.gamebase.general.model.service;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gamebase.general.model.ChatRoom;
import com.gamebase.general.model.WebSocketMessage;
import com.gamebase.general.model.dao.ChatRoomCrudRepository;
import com.gamebase.general.model.dao.ChatRoomDAO;

@Service
@Transactional
public class ChatRoomService {

	private Integer redisId = 0;
	@Autowired
	private ChatRoomCrudRepository cRud;
	@Autowired
	private ChatRoomDAO cDao;

	public void saveToRedis(WebSocketMessage msg) {
		Integer sender = Integer.parseInt(msg.getFrom());
		Integer receiver = Integer.parseInt(msg.getTo()[0]);
		String history = msg.getMessage();
		String type = msg.getType();
		String url = msg.getURL();
		Timestamp time = msg.getTime();

		ChatRoom bean = new ChatRoom(redisId, sender, receiver, history, type, url, time);
		cRud.save(bean);
		redisId++;
		System.out.println(receiver);
		System.out.println("cDao.save: " + bean);
	}

	public void redisToSql(WebSocketMessage msg) {

		try {
			Integer username = Integer.parseInt(msg.getFrom());
			List<ChatRoom> history = cRud.findBySenderOrReceiver(username, username);
			cRud.deleteAll(history);
			System.out.println("Delete redis data");
			for (ChatRoom record : history) {
				record.setId(null);
				cDao.insert(record);
			}
			System.out.println("Data to SQL finished");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void deleteAll(WebSocketMessage msg) {
		try {
			Integer username = Integer.parseInt(msg.getFrom());
			List<ChatRoom> history = cRud.findBySenderOrReceiver(username, username);
			cRud.deleteAll(history);
			System.out.println("===================");
			System.out.println("Data Delete");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
