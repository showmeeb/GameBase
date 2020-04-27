package com.gamebase.general.model.service;

import java.util.Date;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component("test")
public class Test {

	@Scheduled(cron = "*/5 * * * * ?")
	public void testMethod() {
		System.out.println("每5秒顯示一次," + new Date());

	}
}