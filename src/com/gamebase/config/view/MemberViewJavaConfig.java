package com.gamebase.config.view;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.InternalResourceView;

@Configuration
public class MemberViewJavaConfig {

	@Bean
	public View LoginViewPage() {
		InternalResourceView view = new InternalResourceView();
		view.setUrl("/WEB-INF/pages/UserLogin.jsp");
		return view;
	}

	@Bean
	public View RegisterViewPage() {
		InternalResourceView view = new InternalResourceView();
		view.setUrl("/WEB-INF/pages/Register.jsp");
		return view;
	}
	
	@Bean
	public View SendMailPage() {
		InternalResourceView view = new InternalResourceView();
		view.setUrl("/WEB-INF/pages/sendPage.jsp");
		return view;
	}

}
