package com.gamebase.config.websocket;

import java.util.List;



import com.gamebase.general.model.WebSocketMessage;

public interface MessagePublisher {

	public void publish(String message);
	public void publish(WebSocketMessage message);
	public void publish(List<String> onlineUserList);
}
