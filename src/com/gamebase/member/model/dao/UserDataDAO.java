package com.gamebase.member.model.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.gamebase.member.model.UserData;

@Repository
public class UserDataDAO implements IUserData {

	private SessionFactory sessionFactory;

	@Autowired
	public UserDataDAO(@Qualifier(value = "sessionFactory") SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	private Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	/**
	 * @param account , password
	 * @return UserData , null
	 */
	@Override
	public UserData getByLogin(String account, String password) {
		Query<UserData> query = getSession().createQuery("From UserData where account=:acc and password=:pwd",
				UserData.class);
		query.setParameter("acc", account);
		query.setParameter("pwd", password);
		UserData myUserData = query.uniqueResult();
		if (myUserData != null) {
			return myUserData;
		}
		return null;
	}

	@Override
	public List<UserData> getAllUserData() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean checkAccount(String account) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public UserData getByUserId(Integer userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteUserData(UserData userData) {
		// TODO Auto-generated method stub

	}

	@Override
	public void saveUserData(UserData userData) {
		// TODO Auto-generated method stub

	}

}
