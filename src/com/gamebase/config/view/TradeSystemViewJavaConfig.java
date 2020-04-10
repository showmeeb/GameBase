package com.gamebase.config.view;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.InternalResourceView;

@Configuration
public class TradeSystemViewJavaConfig {

	@Bean
	public View addProductPage() {
		InternalResourceView view = new InternalResourceView();
		view.setUrl("/WEB-INF/pages/addProduct.jsp");
		return view;
	}
	@Bean
	public View mainProductPage() {
		InternalResourceView view = new InternalResourceView();
		view.setUrl("/WEB-INF/pages/mainProduct.jsp");
		return view;
	}
	
}
