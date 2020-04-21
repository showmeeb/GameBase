package com.gamebase.general.controller;

import java.sql.Timestamp;
import java.util.ArrayList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.user.SimpUser;
import org.springframework.messaging.simp.user.SimpUserRegistry;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.socket.messaging.SessionConnectEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import com.gamebase.general.model.ChatRoom;

import com.gamebase.general.model.WebSocketMessage;
import com.gamebase.general.model.dao.ChatRoomCrudRepository;
import com.gamebase.general.model.service.ChatRoomService;

@Controller
public class ChatController {
	@Autowired
	private SimpMessagingTemplate simpMessagingTemplate;
	@Autowired
	private SimpUserRegistry simpUserRegistry;
	@Autowired
	private ChatRoomService cService;
	@Autowired
	private ChatRoomCrudRepository chatroom;

	@MessageMapping("/chat")
	public void sendBySimpSingle(@RequestBody WebSocketMessage message) {

		ArrayList<String> userList = new ArrayList<String>();
		Map<String, String> userMap = new HashMap<>();

		for (SimpUser user : simpUserRegistry.getUsers()) {
			String userName = user.getName();
			userList.add(userName);
//			System.out.println("ChatController_user.getName: " + user.getName());
			userMap.put(userName, "online");
		}

//		System.out.println("ChatController_message.getTo[0]: " + message.getTo()[0]);
		String toMessage = message.getTo()[0];
		if ("regist".equals(toMessage)) {
			// convertAndSend = send a message to the given user.
			simpMessagingTemplate.convertAndSend("/regist/messages", userList);
		} else if ("logout".equals(toMessage)) {	
			simpMessagingTemplate.convertAndSend("/topic/messages", message.getFrom());
			cService.redisToSql(message);
//			cService.findAll();
//			System.out.println(chatroom.findById(0).get().toString());
			
		} else if ("broadcast".equals(toMessage)) {
			simpMessagingTemplate.convertAndSend("/topic/messages", message);
		} else {

			for (String to : message.getTo()) {
				// offline routine message
				if (!userMap.containsKey(to)) {
					String replyMsg = "使用者目前離線中";
//					if("0".equals(to)) {
//						replyMsg = generalService.adminChatBot(message.getMessage());
//					}
					WebSocketMessage msg = new WebSocketMessage(to, replyMsg, new String[] { message.getFrom() });
					// Convert the given Object to serialized form, possibly using a
					// MessageConverter, wrap it as a message and send it to the given destination.
					simpMessagingTemplate.convertAndSendToUser(message.getFrom(), "/queue/messages", msg);
//					System.out.println("message.getFrom(): " + message.getFrom());
					cService.saveToRedis(message);
					
				} else {
					simpMessagingTemplate.convertAndSendToUser(to, "/queue/messages", message);
//					history.save(message.getFrom(),message.getTo()[0],message.getMessage());
//					System.out.println("message.getMessage(): " + message.getMessage());
					cService.saveToRedis(message);

				}
			}
		}
	}

	@EventListener
	public void onConnectEvent(SessionConnectEvent event) {
		System.out.println("Client with username " + event.getUser().getName() + " connected");

	}

	@EventListener
	public void onDisconnectEvent(SessionDisconnectEvent event) {
		System.out.println("Client with username " + event.getUser().getName() + " disconnected");
		
	}
}
