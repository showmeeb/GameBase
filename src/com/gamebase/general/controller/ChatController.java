package com.gamebase.general.controller;

import java.sql.Timestamp;
import java.util.ArrayList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.annotation.MultipartConfig;

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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.socket.messaging.SessionConnectEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import com.gamebase.general.model.ChatRoom;

import com.gamebase.general.model.WebSocketMessage;
import com.gamebase.general.model.dao.ChatRoomCrudRepository;
import com.gamebase.general.model.service.ChatRoomService;
import com.gamebase.general.model.service.GeneralService;

@Controller
@MultipartConfig
public class ChatController {
	@Autowired
	private SimpMessagingTemplate simpMessagingTemplate;
	@Autowired
	private SimpUserRegistry simpUserRegistry;
	@Autowired
	private ChatRoomService cService;
	@Autowired
	private GeneralService generalService;
	

	@MessageMapping("/chat")
	public void sendBySimpSingle(@RequestBody WebSocketMessage message) {

		ArrayList<String> userList = new ArrayList<String>();
		Map<String, String> userMap = new HashMap<>();

		for (SimpUser user : simpUserRegistry.getUsers()) {
			String userName = user.getName();
			userList.add(userName);
			userMap.put(userName, "online");
		}
		String toMessage = message.getTo()[0];
		if ("regist".equals(toMessage)) {
			// convertAndSend = send a message to the given user.
			simpMessagingTemplate.convertAndSend("/regist/messages", userList);
		} else if ("logout".equals(toMessage)) {
			simpMessagingTemplate.convertAndSend("/topic/messages", message.getFrom());
			cService.redisToSql(message);


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
					simpMessagingTemplate.convertAndSendToUser(message.getFrom(), "/queue/messages", msg);
					cService.saveToRedis(message);

				} else {
					simpMessagingTemplate.convertAndSendToUser(to, "/queue/messages", message);
					System.out.println("senderToreceiver");
					cService.saveToRedis(message);
				}
			}
		}
	}

	@PostMapping(path = "/Imgur", produces = "application/json")
	@ResponseBody
	public Map<String, String> imgurUpload(@RequestParam(name = "sender") String sender,
			@RequestParam(name = "receiver") String receiver, @RequestPart(name = "file") MultipartFile image, WebSocketMessage message) {

		String fileName = image.getOriginalFilename();
		String imgURL = generalService.uploadToImgur(image);
		String type = fileName.substring(fileName.lastIndexOf(".") + 1);
		System.out.println(imgURL);
		System.out.println(fileName);
		System.out.println(type);
		message.setURL(imgURL);
		message.setType(type);
		Map<String, String> result = new HashMap<>();
		if (imgURL != null) {
			result.put("uploaded", "true");
			result.put("url", imgURL);
		} else {
			result.put("uploaded", "false");
			result.put("url", null);
		}
		return result;
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
