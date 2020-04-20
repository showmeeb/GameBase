package com.gamebase.article.model.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.gamebase.article.model.ArticleContent;

@Repository
public class ArticleContentDAO implements IArticleContentDAO {

	private SessionFactory sessionFactory;

	@Autowired
	public ArticleContentDAO(@Qualifier("sessionFactory") SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public ArticleContent insertContent(ArticleContent content) {
		sessionFactory.getCurrentSession().save(content);
		return content;
	}

	@Override
	public ArticleContent queryOneContent(ArticleContent content) {
		content = sessionFactory.getCurrentSession().get(ArticleContent.class, content.getContentId());
		return content;
	}

	@Override
	public List<ArticleContent> querySomeContentByTitleId(ArticleContent content) {
		Query<ArticleContent> query = sessionFactory.getCurrentSession()
				.createQuery("from ArticleContent where titleId=?1", ArticleContent.class)
				.setParameter(1, content.getTitleId());
		List<ArticleContent> list = query.list();
		return list;
	}

	@Override
	public List<ArticleContent> querySomeContentByUserId(ArticleContent content) {
		Query<ArticleContent> query = sessionFactory.getCurrentSession()
				.createQuery("from ArticleContent where userId=?1", ArticleContent.class)
				.setParameter(1, content.getUserId());
		List<ArticleContent> list = query.list();
		return list;
	}

	@Override
	public ArticleContent updateByContentId(ArticleContent content) {
		ArticleContent result = sessionFactory.getCurrentSession().get(ArticleContent.class, content.getContentId());
		result.setContent(content.getContent());
		result.setUpdateTime(content.getContent());
		return result;
	}

	@Override
	public boolean deleteByContentId(ArticleContent content) {
		ArticleContent rs = sessionFactory.getCurrentSession().get(ArticleContent.class, content.getContentId());
		if (rs != null) {
			sessionFactory.getCurrentSession().delete(rs);
			return true;
		}
		return false;
	}

}
