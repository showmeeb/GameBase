package com.gamebase.article.model.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.gamebase.article.model.ArticleListView;
import com.gamebase.article.model.ArticleTitle;

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
	
	public List<ArticleListView> queryContentListByUserId(Integer Id) {
		Query<ArticleListView> query = sessionFactory.getCurrentSession().createQuery("from ArticleListView where contentRN != 1 and UserId = :id", ArticleListView.class)
				.setParameter("id", Id);
		List<ArticleListView> list = query.list();
		return list;
		
	}
	
	//myArticles
	public List<ArticleListView> queryMemberArticleTitleByKeyInOneForum(Integer userId, Integer forumId, String title) {
		System.out.println("querySomeArticleTitleByKeyInOneForum : "+forumId);	
		 Query<ArticleListView> query = sessionFactory.getCurrentSession()
				.createQuery("from ArticleListView where contentRN = 1 and userId= :uid and forumId= :id and titleName like'%" + title +"%'", ArticleListView.class);
		query.setParameter("id", forumId);
		query.setParameter("uid", userId);
		List<ArticleListView> list=query.list();
		return list;
	}

	//myArticles
	public List<ArticleListView> queryMemberArticleTitleByKeyInallForum(Integer userId, String title) {
		Query<ArticleListView> query = sessionFactory.getCurrentSession()
				.createQuery("from ArticleListView where contentRN = 1 and userId= :id and titleName like'%" + title +"%'", ArticleListView.class);
		query.setParameter("id", userId);
		List<ArticleListView> list=query.list();
		return list;
	}
	
//	//每天文章數
//	public List queryEverydayArticle() {
//      Query query = sessionFactory.getCurrentSession()
//				.createQuery("select convert(varchar(10),createTime,111),count(*) from ArticleListView where contentRN=1 group by convert(varchar(10),createTime,111)");
//
//      List list = query.list();
//		return list;
//	}
	 
	//發文會員數
	public List<ArticleListView> getNumOfUserPosteed() {
		Query<ArticleListView> query = sessionFactory.getCurrentSession()
				.createQuery("select distinct userId from ArticleListView where userId!=0");
		List<ArticleListView> list=query.list();
		return list;
	}				 

}
