package com.gamebase.member.model.dao;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.gamebase.member.model.Role;

@Repository
public class RoleDAO implements IRoleDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public Role getRoleByUserId(Integer usreId) {
		Query<Role> query = sessionFactory.getCurrentSession().createQuery("From Role where userId=:uid", Role.class);
		query.setParameter("uid", usreId);
		Role role = query.uniqueResult();
		return role;
	}

	@Override
	public void changeRole(Role role) {
		sessionFactory.getCurrentSession().save(role);
	}

}
