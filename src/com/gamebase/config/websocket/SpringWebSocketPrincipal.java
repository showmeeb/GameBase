package com.gamebase.config.websocket;

import java.security.Principal;

public class SpringWebSocketPrincipal implements Principal {

	private String name;

	public SpringWebSocketPrincipal() {
		this.name = null;
	}

	public SpringWebSocketPrincipal(String name) {
		this.name = name;
	}

	@Override
	public String getName() {
		return this.name;
	}

}
