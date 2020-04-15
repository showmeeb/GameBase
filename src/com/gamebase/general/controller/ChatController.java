package com.gamebase.general.controller;

import java.util.ArrayList;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.user.SimpUser;
import org.springframework.messaging.simp.user.SimpUserRegistry;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

import com.gamebase.general.model.WebSocketMessage;

@Controller
public class ChatController {
	@Autowired
	private SimpMessagingTemplate simpMessagingTemplate;
	@Autowired
	private SimpUserRegistry simpUserRegistry;

//	@MessageMapping("/chat")
//	@SendTo("/topic/messages")
//	public OutputMessage send(WebSocketMessage message) throws Exception {
//		String time = new SimpleDateFormat("HH:mm").format(new Date());
//		return new OutputMessage(message.getFrom(), message.getText(), time);
//	}
	@MessageMapping("/chat")
	public void sendBySimpSingle(@RequestBody WebSocketMessage message) {
		ArrayList<String> userList = new ArrayList<String>();
		Map<String, String> userMap = new HashMap<>();

		for (SimpUser user : simpUserRegistry.getUsers()) {
			String userName = user.getName();
			userList.add(userName);
			System.out.println("ChatController_user.getName: " + user.getName());
			userMap.put(userName, "online");
		}
		System.out.println("ChatController_message.getTo[0]: "+ message.getTo()[0]);
		String toMessage = message.getTo()[0];
		if ("regist".equals(toMessage)) {
			// convertAndSend = send a message to the given user.
			simpMessagingTemplate.convertAndSend("/regist/messages", userList);
		} else if ("logout".equals(toMessage)) {
			simpMessagingTemplate.convertAndSend("/topic/messages", message.getFrom());
		} else if ("broadcast".equals(toMessage)) {
			simpMessagingTemplate.convertAndSend("/topic/messages", message);
		} else {
			System.out.println("ChatController_message.getTo: "+ message.getTo());
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
					System.out.println("message.getFrom(): " + message.getFrom());
				} else {
					simpMessagingTemplate.convertAndSendToUser(to, "/queue/messages", message);
				}
			}
		}
	}
}
