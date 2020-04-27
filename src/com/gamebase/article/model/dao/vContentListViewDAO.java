package com.gamebase.article.model.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.gamebase.article.model.ContentListView;
import com.gamebase.member.model.Friends;

@Repository
public class vContentListViewDAO {

	private SessionFactory sessionFactory;

	@Autowired
	public vContentListViewDAO(@Qualifier("sessionFactory") SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public List<ContentListView> queryContentListByContentRN(Integer titleId) {
		Query<ContentListView> query = sessionFactory.getCurrentSession().createQuery("from ContentListView where titleId = :titleId order by titleId ", ContentListView.class)
				.setParameter("titleId", titleId);
		List<ContentListView> list = query.list();
		return list;
		
	}
	
	public List<Friends> queryFriendsByUserId(Integer userId){
		Query<Friends> query = sessionFactory.getCurrentSession().createQuery("from Friends where userId = :userId", Friends.class)
				.setParameter("userId", userId);
		List<Friends> list = query.list();
		return list;
	}

}
