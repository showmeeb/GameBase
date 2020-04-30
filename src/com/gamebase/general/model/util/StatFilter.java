package com.gamebase.general.model.util;

import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

import com.gamebase.general.model.Webflow;
import com.gamebase.general.model.dao.WebflowDAO;
import com.gamebase.general.model.service.GeneralService;



/**
 * Servlet Filter implementation class StatFilter
 */

@WebFilter(urlPatterns={"/"})
public class StatFilter implements Filter {
	
	@Autowired
	private GeneralService gService;
	private WebflowDAO webDAO=new WebflowDAO();
	public StatFilter() {
	}

	public void init(FilterConfig filtercfg) throws javax.servlet.ServletException {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterchain)
			throws java.io.IOException, javax.servlet.ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpSession session = httpRequest.getSession(true);
		System.out.println("Aaa");
		
		// 如果本次使用者訪問還沒有記錄，就記錄本次使用者資訊，並儲存到資料庫中
//			session.setAttribute("isRecorded", Boolean.TRUE);
		// 在session物件中儲存一個變數"isRecorded"，並賦值
		String ip = getIp(httpRequest);// 使用者ip
		
//		webDAO.insertIp(new Webflow(ip));


		
		filterchain.doFilter(request, response);
		// 呼叫下一個過濾器或者直接呼叫JSP
	}

	public void destroy() {
	}

	public String getIp(HttpServletRequest httpRequest) {
		String ipAddress = httpRequest.getHeader("x-forwarded-for");
		if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
			ipAddress = httpRequest.getHeader("Proxy-Client-IP");
		}
		if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
			ipAddress = httpRequest.getHeader("WL-Proxy-Client-IP");
		}
		if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
			ipAddress = httpRequest.getRemoteAddr();
			if (ipAddress.equals("127.0.0.1") || ipAddress.equals("0:0:0:0:0:0:0:1")) {
				// 根據網絡卡取本機配置的IP
				InetAddress inet = null;
				try {
					inet = InetAddress.getLocalHost();
				} catch (UnknownHostException e) {
					e.printStackTrace();
				}
				ipAddress = inet.getHostAddress();
			}
		}
		return ipAddress;
	
	}
}
