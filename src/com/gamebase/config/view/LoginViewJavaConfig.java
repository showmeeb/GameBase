package com.gamebase.config.view;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.InternalResourceView;
import org.springframework.web.servlet.view.RedirectView;

@Configuration
public class LoginViewJavaConfig {

	@Bean
	public View LoginViewPage() {
		InternalResourceView view = new InternalResourceView();
		view.setUrl("/WEB-INF/pages/LoginPage.jsp");
		return view;
	}
	@Bean
	public View LoginSuccessPage() {
		RedirectView view = new RedirectView();
		view.setContextRelative(true);
		view.setUrl("/checkLogin");
		return view;
	}
}
