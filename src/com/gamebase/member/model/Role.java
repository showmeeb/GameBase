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
@Table(name = "role")
@Component
public class Role {

	private Integer roleId;
	private UserData userData;
	private Rank rank;

	
	public Role() {
	}

	@Id
	@Column(name = "ROLEID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "USERID")
	public UserData getUserData() {
		return userData;
	}

	public void setUserData(UserData userData) {
		this.userData = userData;
	}

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "RANKID")
	public Rank getRank() {
		return rank;
	}

	public void setRank(Rank rank) {
		this.rank = rank;
	}

}
