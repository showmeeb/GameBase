package com.gamebase.member.model;

//import java.util.HashSet;
//import java.util.Set;

public class UserData {

	private Integer userId;
	private String account;
	private String password;
	private String email;
	private Role role;
//	private UserProfile userProfile;
//	private Set<Order> orders = new HashSet<Order>();
//	private Set<Forum> forums = new HashSet<Forum>();

	public UserData() {
	}

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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

//	public UserProfile getUserProfile() {
//		return userProfile;
//	}
//
//	public void setUserProfile(UserProfile userProfile) {
//		this.userProfile = userProfile;
//	}
//
//	public Set<Order> getOrders() {
//		return orders;
//	}
//
//	public void setOrders(Set<Order> orders) {
//		this.orders = orders;
//	}
//
//	public Set<Forum> getForums() {
//		return forums;
//	}
//
//	public void setForums(Set<Forum> forums) {
//		this.forums = forums;
//	}
}
