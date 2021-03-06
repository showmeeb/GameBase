package com.gamebase.config;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.BeanNameViewResolver;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = { "com.gamebase.general.controller", "com.gamebase.article.controller",
		"com.gamebase.member.controller", "com.gamebase.tradesystem.controller" })

//SpringMVCJavaConfig設定與MVC有關的操作
//註冊ViewJavaConfig

@Import({ com.gamebase.config.view.GeneralViewJavaConfig.class, com.gamebase.config.view.ArticleViewJavaConfig.class,
		com.gamebase.config.view.TradeSystemViewJavaConfig.class, com.gamebase.config.view.MemberViewJavaConfig.class })
public class SpringMVCJavaConfig implements WebMvcConfigurer {
	@Autowired
	ServletContext servletContext;

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/css/**").addResourceLocations("/WEB-INF/static/css/");
		registry.addResourceHandler("/js/**").addResourceLocations("/WEB-INF/static/js/");
		registry.addResourceHandler("/img/**").addResourceLocations("/WEB-INF/static/img/");
	}

	// 集中管理View Java Config，只要呼叫方法名稱即能導向路徑
	@Bean
	public ViewResolver beanNameViewResolver() {
		BeanNameViewResolver bnvr = new BeanNameViewResolver();
		bnvr.setOrder(10);
		return bnvr;
	}

	// for file upload
	/*
	@Bean
	public MultipartResolver multipartResolver() {
		StandardServletMultipartResolver resolver = new StandardServletMultipartResolver();
		return resolver;
	}
	*/
	
	@Bean
	public CommonsMultipartResolver multipartResolver() {
	    CommonsMultipartResolver resolver=new CommonsMultipartResolver();
	    resolver.setDefaultEncoding("utf-8");
	    return resolver;
	}
	
	// 傳統作法Internal 仍然要呼叫路徑，且不易集中管理
//	@Bean
//	public InternalResourceViewResolver viewResolver() { // 設定viewResolver取代xml
//		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
//		viewResolver.setPrefix("/WEB-INF/pages/");
//		viewResolver.setSuffix(".jsp");
//		viewResolver.setOrder(2);
//		System.out.println("viewResolver proceeding...");
//		return viewResolver;
//	}
//	@Override
//	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
//		configurer.enable();
//	}

}
