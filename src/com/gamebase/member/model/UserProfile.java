package com.gamebase.member.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.springframework.stereotype.Component;

@Entity
@Table(name = "userProfile")
@Component
public class UserProfile implements Serializable{

	
	private static final long serialVersionUID = -5382379947035200817L;
	
	
	private Integer userId;
	private Integer profileId;
	private String name;
	private String gender;
	private String nickName;
	private String phone;
	private Integer age;
	private String address;
	private String img;
	

	public UserProfile() {

	}

	@GenericGenerator(name="userId", strategy="foreign", parameters=@Parameter(name="property",value="userData"))
	@Column(name="userId")
	@GeneratedValue(generator = "userId")
	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	@Id
	@Column(name = "PROFILEID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getProfileId() {
		return profileId;
	}

	public void setProfileId(Integer profileId) {
		this.profileId = profileId;
	}

	@Column(name = "NAME")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "GENDER")
	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	@Column(name = "NICKNAME")
	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	@Column(name = "PHONE")
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Column(name = "AGE")
	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	@Column(name = "ADDRESS")
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Column(name = "IMG")
	public String getImage() {
		return img;
	}

	public void setImage(String img) {
		this.img = img;
	}

//	@OneToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name = "USERID")
//	public UserData getUserData() {
//		return userData;
//	}
//
//	public void setUserData(UserData userData) {
//		this.userData = userData;
//	}

}

