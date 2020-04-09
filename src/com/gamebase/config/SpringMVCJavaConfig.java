package com.gamebase.config;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.ViewResolver;

import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.BeanNameViewResolver;


@Configuration
@EnableWebMvc
@ComponentScan(basePackages= "com.gamer.controller")

//SpringMVCJavaConfig設定與MVC有關的操作
//註冊ViewJavaConfig
@Import(com.gamebase.config.view.LoginViewJavaConfig.class)
public class SpringMVCJavaConfig implements WebMvcConfigurer{
	@Autowired
	ServletContext servletContext;


//	public void addInterceptors(InterceptorRegistry registry) {
//		registry.addInterceptor(new DemoInterceptor());
//	}
	//集中管理View Java Config，只要呼叫方法名稱即能導向路徑
	@Bean
	public ViewResolver beanNameViewResolver() {
		BeanNameViewResolver bnvr = new BeanNameViewResolver();
		bnvr.setOrder(10);
		return bnvr;
	}
	// for file upload
	@Bean
    public MultipartResolver multipartResolver() {
		StandardServletMultipartResolver resolver = new StandardServletMultipartResolver();
		
        return resolver;
    }
	//傳統作法Internal 仍然要呼叫路徑，且不易集中管理
//	@Bean
//	public InternalResourceViewResolver viewResolver() { // 設定viewResolver取代xml
//		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
//		viewResolver.setPrefix("/WEB-INF/pages/");
//		viewResolver.setSuffix(".jsp");
//		viewResolver.setOrder(2);
//		System.out.println("viewResolver proceeding...");
//		return viewResolver;
//	}

}
