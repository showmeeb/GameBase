package com.gamebase.general.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@RedisHash
public class OnlineUser {
	@Id
	private String name;
	private String history;
	
	public OnlineUser(String name, String history) {
		this.name=name;
		this.history=history;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getHistory() {
		return history;
	}

	public void setHistory(String history) {
		this.history = history;
	}
}
