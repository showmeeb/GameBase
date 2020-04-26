package com.gamebase.general.model;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonFormat;

@RedisHash("History")
@Component
@Entity
@Table(name = "ChatRoom")
public class ChatRoom implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	@Indexed
	private Integer sender;
	@Indexed
	private Integer receiver;
	private String history;
	private String type;
	private String URL;
	private Timestamp time;

	public ChatRoom(Integer id, Integer sender, Integer receiver, String history, String type, String url, Timestamp time) {
		this.id=id;
		this.sender = sender;
		this.receiver = receiver;
		this.history = history;
		this.type=type;
		this.URL=url;
		this.time=time;
	}

	public ChatRoom() {

	}
	@org.springframework.data.annotation.Id //for redis
	@Id //fro sql
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	@Column(name = "SENDER")
	public Integer getSender() {
		return sender;
	}

	public void setSender(Integer sender) {
		this.sender = sender;
	}
	@Column(name = "RECEIVER")
	public Integer getReceiver() {
		return receiver;
	}

	public void setReceiver(Integer receiver) {
		this.receiver = receiver;
	}
	@Column(name = "HISTORY")
	public String getHistory() {
		return history;
	}

	public void setHistory(String history) {
		this.history = history;
	}
	@Column(name = "TYPE")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	@Column(name = "URL")
	public String getURL() {
		return URL;
	}

	public void setURL(String uRL) {
		URL = uRL;
	}
	@Column(name = "TIME")
	@JsonFormat(pattern = "HH:mm", timezone = "GTM+8")
	public Timestamp getTime() {
		return time;
	}

	public void setTime(Timestamp time) {
		this.time = time;
	}
}
