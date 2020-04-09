package com.gamebase.config.websocket;

import java.security.Principal;
import java.util.Map;

import org.springframework.http.server.ServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.DefaultHandshakeHandler;

import com.gamebase.member.model.UserData;

public class SpringWebSocketHandler extends DefaultHandshakeHandler {

	@Override
	protected Principal determineUser(ServerHttpRequest request, WebSocketHandler wsHandler,
			Map<String, Object> attributes) {
		String userId="-1";
		
		if(attributes.containsKey("UserData")) {
			userId = ((UserData) attributes.get("UserData")).getUserId().toString();
		}
		
		
		return new SpringWebSocketPrincipal(userId);
	}

	
}
