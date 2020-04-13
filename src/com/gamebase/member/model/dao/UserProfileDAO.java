package com.gamebase.member.model.dao;

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
	public UserProfileDAO(@Qualifier(value="sessionFactory") SessionFactory sessionFactory) {
		this.sessionFactory=sessionFactory;
	}
	
	public UserProfile getByUserId(Integer userId) {
		
		Query<UserProfile> query = sessionFactory.getCurrentSession().createQuery("From UserProfile where userId=:userId",UserProfile.class);
		query.setParameter("userId", userId);
		query.uniqueResult();
		return null;
	}
	
	public void saveUserProfile(UserProfile userProfile) {
		
		sessionFactory.getCurrentSession().save(userProfile);
	}
	
	public UserProfile updateUserProfile(UserProfile userProfile) {
		
		Query<UserProfile> query = sessionFactory.getCurrentSession().createQuery("From UserProfile",UserProfile.class);
		
		UserProfile myUserProfile = query.uniqueResult();
		if(myUserProfile!=null) {
			return myUserProfile;
		}
		return null;
	}
}

