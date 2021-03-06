package com.gamebase.config.websocket;

import java.security.Principal;
import java.util.Map;

import org.springframework.http.server.ServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.DefaultHandshakeHandler;


import com.gamebase.member.model.UsersInfo;

public class SpringWebSocketHandler extends DefaultHandshakeHandler {

	@Override
	protected Principal determineUser(ServerHttpRequest request, WebSocketHandler wsHandler,
			Map<String, Object> attributes) {
		String userNo="-1";
		
		/*拿取userId存成Principal的name*/
		if(attributes.containsKey("loginUser")) {
			userNo = ((UsersInfo) attributes.get("loginUser")).getUserId().toString();
		}
		
		
		return new SpringWebSocketPrincipal(userNo);
	}

	
}
