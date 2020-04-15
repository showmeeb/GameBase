package com.gamebase.member.model.dao;

import java.util.List;

import javax.persistence.NoResultException;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.gamebase.member.model.UsersInfo;

@Repository
public class UsersInfoDAO implements IUsersInfoDAO {

	@Autowired
	private SessionFactory sessionFactory;

	private final String SELECT_BY_UID = "From com.gamebase.member.model.UsersInfo UsersInfo where UsersInfo.account = :uID";
	private final String SELECT_BY_UNO = "From com.gamebase.member.model.UsersInfo UsersInfo where UsersInfo.userId = :uNo";
	private final String SELECT_FRIENDS_BY_UNO = "select b.* From Friends a" + " inner join UsersInfoView b "
			+ " on a.friendId = b.userId" + " where a.userId = :userNo";

	@Override
	public UsersInfo selectById(String account) {
		UsersInfo result = null;
		try {
			result = (UsersInfo) sessionFactory.getCurrentSession().createQuery(SELECT_BY_UID)
					.setParameter("uID", account).getSingleResult();
		} catch (NoResultException e) {
			 e.printStackTrace();
			return null;
		}
		return result;
	}

	@Override
	public UsersInfo selectByNo(Integer userId) {
		UsersInfo result = null;
		try {
			result = (UsersInfo) sessionFactory.getCurrentSession().createQuery(SELECT_BY_UNO)
					.setParameter("uNo", userId).getSingleResult();
		} catch (NoResultException e) {
			 e.printStackTrace();
			return null;
		}
		return result;
	}

	@Override
	public List<UsersInfo> getFriendList(Integer userNo) {
		List<UsersInfo> list = null;

		try {
			list = sessionFactory.getCurrentSession().createSQLQuery(SELECT_FRIENDS_BY_UNO)
					.setParameter("userNo", userNo).addEntity(UsersInfo.class).getResultList();
		} catch (NoResultException e) {
			e.printStackTrace();
			return null;
		}

		return list;
	}

}
