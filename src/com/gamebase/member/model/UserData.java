package com.gamebase.member.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.springframework.stereotype.Component;

//import java.util.HashSet;
//import java.util.Set;
@Entity
@Table(name = "userData")
@Component
public class UserData {

	private Integer userId;
	private String account;
	private String password;
	private String email;
	private Integer rankId;
	private Role role;
	private Rank rank;
//	private UserProfile userProfile;
//	private Set<Order> orders = new HashSet<Order>();
//	private Set<Forum> forums = new HashSet<Forum>();
//	private List<Friends> friendsList;

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

	@Transient
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "userData")
	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
	@Transient
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "userData")
	public Rank getRank() {
		return rank;
	}

	public void setRank(Rank rank) {
		this.rank = rank;
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
//	@Transient
//	public List<Friends> getFriendsList() {
//		return friendsList;
//	}
//
//	public void setFriendsList(List<Friends> friendsList) {
//		this.friendsList = friendsList;
//	}
}
