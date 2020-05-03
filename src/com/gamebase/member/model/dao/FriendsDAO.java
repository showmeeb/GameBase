package com.gamebase.member.model.dao;

import javax.persistence.NoResultException;

import org.hibernate.SessionFactory;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.gamebase.member.model.Friends;

@Repository
public class FriendsDAO implements IFriends {

	@Autowired
	private SessionFactory sessionFactory;
	private final String SELECT_BY_BOTH_ID = "From com.gamebase.member.model.Friends f where f.userId = :userId and f.friendId = :friendId";

	@Override
	public Friends insert(Friends bean) {
		try {
			sessionFactory.getCurrentSession().save(bean);
			
		} catch (ConstraintViolationException e) {
			return null;
		}
		return bean;
	}

	@Override
	public Friends selectByEachId(Integer userId, Integer friendId) {
		Friends bean = null;
		try {
			bean = (Friends) sessionFactory.getCurrentSession().createQuery(SELECT_BY_BOTH_ID)
					.setParameter("userId", userId).setParameter("friendId", friendId).getSingleResult();
			
		} catch (NoResultException e) {
			return null;
		}
		return bean;
	}

}
