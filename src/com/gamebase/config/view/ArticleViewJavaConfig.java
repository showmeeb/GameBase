package com.gamebase.config.view;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.InternalResourceView;

@Configuration
public class ArticleViewJavaConfig {

	/* example */
//	@Bean
//	public View LoginViewPage() {
//		InternalResourceView view = new InternalResourceView();
//		view.setUrl("/WEB-INF/pages/LoginPage.jsp");
//		return view;
//	}

	@Bean
	public View parentArticleViewPage() {
		InternalResourceView view = new InternalResourceView();
		view.setUrl("/WEB-INF/pages/parentArticle.jsp");
		return view;
	}

	@Bean
	public View forumViewPage() {
		InternalResourceView view = new InternalResourceView();
		view.setUrl("/WEB-INF/pages/forum.jsp");
		return view;
	}
	
	@Bean
	public View childArticleViewPage() {
		InternalResourceView view = new InternalResourceView();
		view.setUrl("/WEB-INF/pages/childArticle.jsp");
		return view;
	}
}
