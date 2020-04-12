package com.gamebase.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.server.standard.ServletServerContainerFactoryBean;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;

import com.gamebase.config.websocket.SpringWebSocketHandler;

import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

@Configuration
@EnableWebSocketMessageBroker
public class SpringWebSocketJavaConfig implements WebSocketMessageBrokerConfigurer {

	
	//MessageBroker 是交換訊息的中間程序區塊；  registry=註冊
	@Override
	public void configureMessageBroker(MessageBrokerRegistry registry) {
		//topic=預設的廣播channel；queue=預設的一對一channel；regist=自己開一個讓大家知道自己上線的channel
		registry.enableSimpleBroker("/topic/","/queue/","/regist/");
		registry.setApplicationDestinationPrefixes("/app"); //Configure one or more prefixes to filter destinations targeting application annotated methods.
	}

	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {
		registry.addEndpoint("/chat").addInterceptors(new HttpSessionHandshakeInterceptor()) //從session拿attribute
				.setHandshakeHandler(new SpringWebSocketHandler()).withSockJS();
	}
	//tomcat config
	@Bean
	public ServletServerContainerFactoryBean createWebSocketContainer() {
		ServletServerContainerFactoryBean container = new ServletServerContainerFactoryBean();
		container.setMaxTextMessageBufferSize(8192);  //for tomcat memory config
		container.setMaxBinaryMessageBufferSize(8192); 
		return container;
	}
}
