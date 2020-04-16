package com.gamebase.article.model.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.gamebase.article.model.ArticleData;

@Repository
public class ArticleDataDAO implements IArticleDataDAO {

	private SessionFactory sessionFactory;

	@Autowired
	public ArticleDataDAO(@Qualifier("sessionFactory") SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public ArticleData insertData(ArticleData data) {
		List<ArticleData> query = sessionFactory.getCurrentSession()
				.createQuery("from ArticleData where articleDataId=?1", ArticleData.class)
				.setParameter(1, data.getArticleDataId())
				.list();
		if(query.size() > 0) {
			return null;
		}
		sessionFactory.getCurrentSession().save(data);
		return data;
	}

	@Override
	public ArticleData queryData(ArticleData data) {
		data = sessionFactory.getCurrentSession().get(ArticleData.class, data.getArticleDataId());
		return data;
	}

	@Override
	public List<ArticleData> queryByTitleId(ArticleData data) {
		Query<ArticleData> query = sessionFactory.getCurrentSession().createQuery("from ArticleData where titleId=?1", ArticleData.class)
				.setParameter(1, data.getTitleId());
		List<ArticleData> list = query.list();
		return list;
	}

	@Override
	public List<ArticleData> queryByforumId(ArticleData data) {
		Query<ArticleData> query = sessionFactory.getCurrentSession().createQuery("from ArticleData where forumId=?1", ArticleData.class)
				.setParameter(1, data.getForumId());
		List<ArticleData> list = query.list();
		return list;
	}

	@Override
	public ArticleData updateyByArticleDataId(ArticleData data) {
		ArticleData result = sessionFactory.getCurrentSession().get(ArticleData.class, data.getArticleDataId());
		result.setClickNum(data.getClickNum());
		result.setLikeNum(data.getLikeNum());
		result.setUnlikeNum(data.getUnlikeNum());
		result.setShareNum(data.getShareNum());
		return result;
	}

	@Override
	public boolean deleteOneData(ArticleData data) {
		ArticleData rs = sessionFactory.getCurrentSession().get(ArticleData.class, data.getArticleDataId());
		if(rs != null) {
			sessionFactory.getCurrentSession().delete(rs);
			return true;
		}
		return false;
	}

}
