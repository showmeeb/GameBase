package com.gamebase.config.websocket;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Service;

import com.gamebase.general.controller.ChatController;
import com.gamebase.general.model.WebSocketMessage;

@Service
public class RedisMessageSubscriber implements MessageListener {
	@Autowired
	private ChatController chatController;
	private Logger logger = Logger.getLogger(RedisMessageSubscriber.class);

	@Override
	public void onMessage(Message message, byte[] pattern) {

		byte[] mBody = message.getBody();
		try {
			ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(mBody));
			WebSocketMessage msg = (WebSocketMessage) ois.readObject();
			chatController.sendBySimpSingle(msg);
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
			logger.debug(e.getMessage());
		}

	}

}
