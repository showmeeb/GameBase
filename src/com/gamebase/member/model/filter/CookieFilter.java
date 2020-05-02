package com.gamebase.member.model.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;


@WebFilter("/loginAjax")
public class CookieFilter implements Filter {

  
    public CookieFilter() {
       
    }

	
	public void destroy() {
		
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest)request;
		Cookie[] cookies = req.getCookies();
		String name="", acc="",pwd="";
		
		if(cookies!=null&&cookies.length>0) {
			for(Cookie cookie:cookies) {
				name = cookie.getName();
				System.out.println("cookieName: " + name);
				if("account".equals(name)) {
					acc = cookie.getValue();
					
				}else if("password".equals(name)){
					pwd = cookie.getValue();
					
				}
			}
		}
		req.setAttribute("account", acc);
		req.setAttribute("password", pwd);
	
		chain.doFilter(request, response);
	}

	
	public void init(FilterConfig fConfig) throws ServletException {
		
	}

}
