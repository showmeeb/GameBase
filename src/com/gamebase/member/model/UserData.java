package com.gamebase.member.model;





import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.Table;

import org.springframework.stereotype.Component;


@Entity
@Table(name = "userData")
@Component
public class UserData {

	private Integer userId;
	private String account;
	private String password;
	private String email;
	private Integer rankId;
	private String regiestdate;
	

	public UserData() {
	}

	public UserData(String account, String email) {
		this.account = account;
		this.email = email;
	}

	@Id
	@Column(name = "USERID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	@Column(name = "ACCOUNT")
	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	@Column(name = "PASSWORD")
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "EMAIL")
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "RANKID")
	public Integer getRankId() {
		return rankId;
	}

	public void setRankId(Integer rankId) {
		this.rankId = rankId;
	}


	@Column(name = "REGIESTDATE")
	public String getRegiestdate() {
		return regiestdate;
	}

	public void setRegiestdate(String regiestdate) {
		this.regiestdate = regiestdate;
	}

}
