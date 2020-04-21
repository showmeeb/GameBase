package com.gamebase.general.model;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.stereotype.Component;



@RedisHash
//@Component
//@Entity
//@Table(name = "ChatRoomHistory")
public class ChatRoomHistory {
	@Id
	private Integer chatRoomId;
	private String sender;
	private String receiver;
	private String history;
	private String type;
	private String URL;
	
	public ChatRoomHistory(String sender, String receiver, String history) {
		this.sender=sender;
		this.receiver=receiver;
		this.history=history;
	}
	public ChatRoomHistory() {
		
	}
//	@javax.persistence.Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	@Column(name = "CHATROOMID")
	public Integer getChatRoomId() {
		return chatRoomId;
	}

	public void setChatRoomId(Integer chatRoomId) {
		this.chatRoomId = chatRoomId;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	public String getHistory() {
		return history;
	}

	public void setHistory(String history) {
		this.history = history;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getURL() {
		return URL;
	}

	public void setURL(String uRL) {
		URL = uRL;
	}
}
