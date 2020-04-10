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
	@Autowired
	private SessionFactory sessionFactory;

	/**
	 * @param account , password
	 * @return UserData , null
	 */
	@Override
	public UserData getByLogin(String account, String password) {
		Query<UserData> query = sessionFactory.getCurrentSession()
				.createQuery("From UserData where account=:acc and password=:pwd", UserData.class);
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
		Query<UserData> allQuery = sessionFactory.getCurrentSession().createQuery("From UserData", UserData.class);
		List<UserData> list = allQuery.list();
		return list;
	}

	@Override
	public boolean checkAccount(String account) {
		Query<UserData> query = sessionFactory.getCurrentSession().createQuery("From UserData where account=:acc",
				UserData.class);
		query.setParameter("acc", account);
		UserData myAccount = query.uniqueResult();
		if (myAccount != null) {
			return true;
		}
		return false;
	}

	@Override
	public UserData getByUserId(Integer userId) {
		Query<UserData> query = sessionFactory.getCurrentSession().createQuery("From UserData where userId=:uid",
				UserData.class);
		query.setParameter("uid", userId);

		return null;
	}

	@Override
	public void deleteUserData(UserData userData) {

		sessionFactory.getCurrentSession().delete(userData);

	}

	@Override
	public void saveUserData(UserData userData) {

		sessionFactory.getCurrentSession().save(userData);

	}

}
