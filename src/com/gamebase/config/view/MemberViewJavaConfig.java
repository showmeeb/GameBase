package com.gamebase.config.view;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.InternalResourceView;

@Configuration
public class MemberViewJavaConfig {

	@Bean
	public View LoginSuccessViewPage() {
		InternalResourceView view = new InternalResourceView();
		view.setUrl("/WEB-INF/pages/index.jsp");
		return view;
	}
	@Bean
	public View LoginFailedViewPage() {
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
	public View RegistFailedViewPage() {
		InternalResourceView view = new InternalResourceView();
		view.setUrl("/WEB-INF/pages/Register.jsp");
		return view;
	}
	
	public View RegistSuccessViewPage() {
		InternalResourceView view = new InternalResourceView();
		view.setUrl("/WEB-INF/pages/index.jsp");
		return view;
	}
}
