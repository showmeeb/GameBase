package com.gamebase.general.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import com.gamebase.general.model.WebSocketMessage;
import com.gamebase.general.model.OutputMessage;
@Controller
public class ChatController {

//	@MessageMapping("/chat")
//	@SendTo("/topic/messages")
//	public OutputMessage send(WebSocketMessage message) throws Exception {
//		String time = new SimpleDateFormat("HH:mm").format(new Date());
//		return new OutputMessage(message.getFrom(), message.getText(), time);
//	}
}
