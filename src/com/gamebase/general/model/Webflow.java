package com.gamebase.general.model;

import java.util.Date;
import java.text.SimpleDateFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;


@Component
@Entity
@Table(name = "webflow")
public class Webflow {
	
	private Integer id;
	private String ip;
	private String logdate;
	private Integer logtime;

	public Webflow() {
		
	}
	
	public Webflow(String ip) {
		this.ip = ip;
		this.logdate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		this.logtime = 1;
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name="IP")
	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	@Column(name="LOGDATE")
	public String getLogdate() {
		return logdate;
	}

	public void setLogdate(String logdate) {
		this.logdate = logdate;
	}

	@Column(name="LOGTIME")
	public Integer getLogtime() {
		return logtime;
	}

	public void setLogtime(Integer logtime) {
		this.logtime = logtime;
	}
	
	

}
