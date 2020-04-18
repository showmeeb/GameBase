package com.gamebase.general.model;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonFormat;

public class WebSocketMessage {

	private String from;
	private String message;
	private String[] to;
	private Timestamp time = new Timestamp(System.currentTimeMillis());
	private String serverName;

	public WebSocketMessage() {

	}

	public WebSocketMessage(String from, String message) {
		this.from = from;
		this.message = message;
	}

	public WebSocketMessage(String from, String message, String[] to) {
		this.from = from;
		this.message = message;
		this.to = to;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String[] getTo() {
		return to;
	}

	public void setTo(String[] to) {
		this.to = to;
	}

	@JsonFormat(pattern = "HH:mm", timezone = "GTM+8")
	public Timestamp getTime() {
		return time;
	}

	public void setTime(Timestamp time) {
		this.time = time;
	}

	public String getServerName() {
		return serverName;
	}

	public void setServerName(String serverName) {
		this.serverName = serverName;
	}
}
