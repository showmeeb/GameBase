package com.gamebase.member.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Entity
@Table(name = "rank")
@Component
public class Rank {

	private Integer rankId;
	private String rank;
	private UserData userData;

	public Rank() {
	}

	public Rank(UserData userData, Integer rankId) {
		this.userData = userData;
		this.rankId = rankId;
	}

	@Id
	@Column(name = "RANKID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getRankId() {
		return rankId;
	}

	public void setRankId(Integer rankId) {
		this.rankId = rankId;
	}

	@Column(name = "RANK")
	public String getRank() {
		return rank;
	}

	public void setRank(String rank) {
		this.rank = rank;
	}

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "RANKID")
	public UserData getUserData() {
		return userData;
	}

	public void setUserData(UserData userData) {
		this.userData = userData;
	}

}
