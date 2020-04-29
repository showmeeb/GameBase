package com.gamebase.general.controller;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
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
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
import com.google.gson.Gson;

import net.sf.json.JSONObject;

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
		System.out.println(message.getTime());
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
//					simpMessagingTemplate.convertAndSendToUser(message.getFrom(), "/queue/messages", message);
					cService.saveToRedis(message);
				}
			}
		}
	}

	@PostMapping(path = "/File", produces = "application/json")
	@ResponseBody
	public Map<String, String> fileUpload(@RequestParam(name = "sender") String sender,
			@RequestParam(name = "receiver") String receiver, @RequestPart(name = "file") MultipartFile file) {

		String fileName = file.getOriginalFilename();
		String type = fileName.substring(fileName.lastIndexOf(".") + 1);
		System.out.println(type);
		if (type.contentEquals("pdf")) {
			WebSocketMessage bean = new WebSocketMessage();
			bean.setFrom(sender);
			bean.setTo(new String[] { receiver });
			bean.setType(type);
			bean.setURL("img/PDF_file_icon.jpg");
//			System.out.println("123fffffffffffffff");
			bean.setTime(new Timestamp(System.currentTimeMillis()));
			sendMultiFile(bean, bean.getFrom(), bean.getTo()[0]);
		} else {
			String fileURL = generalService.uploadToImgur(file);
			System.out.println(fileURL);
			WebSocketMessage bean = new WebSocketMessage();
			bean.setFrom(sender);
			bean.setTo(new String[] { receiver });
			bean.setType(type);
			bean.setURL(fileURL);
			bean.setTime(new Timestamp(System.currentTimeMillis()));
			sendMultiFile(bean, bean.getFrom(), bean.getTo()[0]);
		}
		Map<String, String> result = new HashMap<>();
		if (file != null && file.getSize() != 0) {
			result.put("type", type);
		} else {
			result.put("type", null);
		}
		return result;
	}

	@PostMapping(path = "/Query/{chatHistoryPage}", produces = "application/json")
	@ResponseBody
	public List<ChatRoom> queryNext(@RequestParam(name = "sender") String sender,
			@RequestParam(name = "receiver") String receiver, @PathVariable Integer chatHistoryPage, Model model) {
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<ChatRoom> chatHistory = null;
		chatHistory = cService.queryHistoryNext(sender, receiver, chatHistoryPage);
		// reverse list
//		Collections.reverse(chatHistory);
		for (ChatRoom result : chatHistory) {
			WebSocketMessage bean = new WebSocketMessage();
			bean.setFrom(result.getSender().toString());
			bean.setTo(new String[] { result.getReceiver().toString() });
			bean.setMessage(result.getHistory());
			bean.setType(result.getType());
			bean.setURL(result.getURL());
			bean.setTime(result.getTime());
			sendMultiMessage(bean, bean.getFrom(), bean.getTo()[0]);
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (chatHistory != null) {
			return chatHistory;
		} else {
			return null;
		}
	}

	@PostMapping(path = "/Broadcast/{userId}", produces = "application/json")
	@ResponseBody
	public Map<String, String> sendTopicMessage(@PathVariable("userId") Integer userId,
			@RequestBody String body, Model model) {
		System.out.println(userId);
		System.out.println(body);
		
		Gson gson = new Gson();
		Map requestMap = gson.fromJson(body, Map.class);
		
		String broadcastMsg = (String) requestMap.get("broadcast");
		System.out.println(broadcastMsg);
		
		WebSocketMessage bean = new WebSocketMessage();
		bean.setFrom(userId.toString());
		bean.setMessage(broadcastMsg);
		sendMultiTopicMessage(bean, bean.getFrom());
		Map<String, String> result = new HashMap<>();
		if (body != null && body.length() != 0) {
			result.put("true", bean.getFrom());
		} else {
			result.put("false", null);
		}
		return result;
	}

	public void sendMultiFile(WebSocketMessage msg, String... receivers) {
		for (String receiver : receivers) {
			simpMessagingTemplate.convertAndSendToUser(receiver, "/queue/messages", msg);
		}
		cService.saveToRedis(msg);
	}

	public void sendMultiMessage(WebSocketMessage msg, String... receivers) {
		for (String receiver : receivers) {
			simpMessagingTemplate.convertAndSendToUser(receiver, "/queue/messages/history", msg);
		}
	}

	public void sendMultiTopicMessage(WebSocketMessage msg, String sender) {

		simpMessagingTemplate.convertAndSendToUser(sender, "/topic/messages/broadcast", msg);
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
