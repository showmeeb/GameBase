package com.gamebase.member.model.dao;

import java.util.Map;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.gamebase.member.model.UserProfile;

@Repository
public class UserProfileDAO {

	private SessionFactory sessionFactory;

	@Autowired
	public UserProfileDAO(@Qualifier(value = "sessionFactory") SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	

	public void saveUserProfile(UserProfile userProfile) {

		sessionFactory.getCurrentSession().save(userProfile);
		System.out.println("1");
	}

	public UserProfile updateUserProfile(Map<String, String[]> upMap) {
		
		Query<UserProfile> up = sessionFactory.getCurrentSession().createQuery("From UserProfile where userId=:userId",
				UserProfile.class);

		up.setParameter("userId", Integer.valueOf(upMap.get("userId")[0]));
		UserProfile userProfile = up.uniqueResult();

		userProfile.setUserId(Integer.valueOf(upMap.get("userId")[0]));
		userProfile.setName(upMap.get("name")[0]);
		userProfile.setAge(Integer.valueOf(upMap.get("age")[0]));
		userProfile.setAddress(upMap.get("address")[0]);
		userProfile.setGender(upMap.get("gender")[0]);
		userProfile.setNickName(upMap.get("nickName")[0]);
		userProfile.setPhone(upMap.get("phone")[0]);

		return userProfile;
	}

	public UserProfile getProfileByUserId(Integer userId) {
		Query<UserProfile> query = sessionFactory.getCurrentSession()
				.createQuery("From UserProfile where userId=:userId", UserProfile.class);
		query.setParameter("userId", userId);
		UserProfile myBean = query.uniqueResult();
		if (myBean != null) {
			return myBean;
		}
			return null;
	}
}

