package com.gamebase.member.model;

public class Role {

	private Integer roleId;
	private UserData userData;
//	private Rank rank;

	public Role() {
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public UserData getUserData() {
		return userData;
	}

	public void setUserData(UserData userData) {
		this.userData = userData;
	}

//	public Rank getRank() {
//		return rank;
//	}
//
//	public void setRank(Rank rank) {
//		this.rank = rank;
//	}

}
