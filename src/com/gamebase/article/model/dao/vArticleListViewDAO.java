package com.gamebase.article.model.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.gamebase.article.model.ArticleListView;

@Repository
public class vArticleListViewDAO {

	private SessionFactory sessionFactory;

	@Autowired
	public vArticleListViewDAO(@Qualifier("sessionFactory") SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public List<ArticleListView> queryArticleListByContentRN(Integer contentRN, Integer forumId) {
		Query<ArticleListView> query = sessionFactory.getCurrentSession().createQuery("from ArticleListView where contentRN <= :contentRN and forumId = :forumId order by titleId DESC", ArticleListView.class)
				.setParameter("contentRN", contentRN)
				.setParameter("forumId", forumId);
		List<ArticleListView> list = query.list();
		return list;
		
	}
	
	public List<ArticleListView> queryArticleListByUserId(Integer Id) {
		Query<ArticleListView> query = sessionFactory.getCurrentSession().createQuery("from ArticleListView where contentRN = 1 and UserId = :id", ArticleListView.class)
				.setParameter("id", Id);
		List<ArticleListView> list = query.list();
		return list;
		
	}

}
