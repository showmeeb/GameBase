package com.gamebase.article.model.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.gamebase.article.model.ArticleTitle;

@Repository
public class ArticleTitleDAO implements IArticleTitleDAO {

	private SessionFactory sessionFactory;

	@Autowired
	public ArticleTitleDAO(@Qualifier("sessionFactory") SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public ArticleTitle insertArticleTitle(ArticleTitle title) {
		List<ArticleTitle> query = sessionFactory.getCurrentSession()
				.createQuery("from ArticleTitle where forumId=?1 and titleName=?2", ArticleTitle.class)
				.setParameter(1, title.getForumId()).setParameter(2, title.getTitleName()).list();
		if (query.size() > 0) {
			return null;
		}
		sessionFactory.getCurrentSession().save(title);
		return title;
	}

	@Override
	public ArticleTitle queryOneArticleTitle(Integer titleId) {
		ArticleTitle title = sessionFactory.getCurrentSession().get(ArticleTitle.class, titleId);
		return title;
	}

	@Override
	public List<ArticleTitle> querySomeArticleTitleByForumId(Integer forumId) {
		Query<ArticleTitle> query = sessionFactory.getCurrentSession()
				.createQuery("from ArticleTitle where forumId=:forumId", ArticleTitle.class)
				.setParameter("forumId", forumId);
		List<ArticleTitle> list = query.list();
		return list;
	}

	@Override
	public List<ArticleTitle> queryAllArticleTitle() {
		Query<ArticleTitle> query = sessionFactory.getCurrentSession().createQuery("from ArticleTitle",
				ArticleTitle.class);
		List<ArticleTitle> list = query.list();
		return list;
	}

	@Override
	public ArticleTitle updateOneArticleTitle(ArticleTitle title) {
		ArticleTitle result = sessionFactory.getCurrentSession().get(ArticleTitle.class, title.getTitleId());
		result.setTitleName(title.getTitleName());
		result.setFirstFigure(title.getFirstFigure());
		result.setLastReplyTime(title.getLastReplyTime());
		result.setClickNum(title.getClickNum());
		result.setLikeNum(title.getLikeNum());
		result.setUnlikeNum(title.getUnlikeNum());
//		result.setShareNum(title.getShareNum());
		return result;
	}

	@Override
	public boolean deleteOneArticleTitle(ArticleTitle title) {
		ArticleTitle rs = sessionFactory.getCurrentSession().get(ArticleTitle.class, title.getTitleId());
		if (rs != null) {
			sessionFactory.getCurrentSession().delete(rs);
			return true;
		}
		return false;
	}
	
	
	//allArticles
	@Override
	public List<ArticleTitle> querySomeArticleTitleByKeyInOneForum(Integer forumId, String title) {
		System.out.println("querySomeArticleTitleByKeyInOneForum : "+forumId);	
		Query<ArticleTitle> query = sessionFactory.getCurrentSession()
				.createQuery("from ArticleTitle where forumId= :id and titleName like'%" + title +"%'", ArticleTitle.class);
		query.setParameter("id", forumId);
		List<ArticleTitle> list=query.list();
		return list;
	}
	
	//allArticles
	@Override
	public List<ArticleTitle> querySomeArticleTitleByKeyInallForum(String title) {

		Query<ArticleTitle> query = sessionFactory.getCurrentSession()
				.createQuery("from ArticleTitle where titleName like'%" + title +"%'", ArticleTitle.class);
		List<ArticleTitle> list=query.list();
		return list;
	}
	


}
