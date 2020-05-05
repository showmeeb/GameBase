package com.gamebase.member.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name = "UsersInfoView")
public class UsersInfo {

	private Integer userId;
	private String account;
	private List<UsersInfo> friendsList;
	private String img;
	private Integer rankId;
	private Integer total;

	@Id
	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	@Transient
	public List<UsersInfo> getFriendsList() {
		return friendsList;
	}

	public void setFriendsList(List<UsersInfo> friendsList) {
		this.friendsList = friendsList;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public Integer getRankId() {
		return rankId;
	}

	public void setRankId(Integer rankId) {
		this.rankId = rankId;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

}
