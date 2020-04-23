package com.gamebase.article.model.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.gamebase.article.model.ForumListView;

@Repository
public class vForumListViewDAO {

	private SessionFactory sessionFactory;

	@Autowired
	public vForumListViewDAO(@Qualifier("sessionFactory") SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	public List<ForumListView> queryForumListByClickNum(Integer clickRN) {
		Query<ForumListView> query = sessionFactory.getCurrentSession().createQuery("from ForumListView where clickRN <= :clickRN order by forumId", ForumListView.class)
				.setParameter("clickRN", clickRN);
		List<ForumListView> list = query.list();
		return list;
	}

	public List<ForumListView> queryForumListByLikeNum(Integer likeRN) {
		Query<ForumListView> query = sessionFactory.getCurrentSession().createQuery("from ForumListView where likeRN <= :likeRN order by forumId", ForumListView.class)
				.setParameter("likeRN", likeRN);
		List<ForumListView> list = query.list();
		return list;
	}
	
	public ForumListView queryForumListByForumId(Integer forumId) {
		Query<ForumListView> query = sessionFactory.getCurrentSession().createQuery("from ForumListView where forumId = :forumId order by forumId", ForumListView.class)
				.setParameter("forumId", forumId);
		ForumListView rs = query.uniqueResult();
		return rs;
	}
}
