package com.gamebase.member.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Entity
@Table(name = "rank")
@Component
public class Rank {

	private Integer rankId;
	private String rank;

	public Rank() {
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

}
