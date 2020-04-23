package com.gamebase.article.model.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.gamebase.article.model.ArticleRecord;

@Repository
public class ArticleRecordDAO implements IArticleRecordDAO {
	private SessionFactory sessionFactory;

	@Autowired
	public ArticleRecordDAO(@Qualifier("sessionFactory") SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public ArticleRecord insertRecord(ArticleRecord record) {
		sessionFactory.getCurrentSession().save(record);
		return record;
	}

	@Override
	public ArticleRecord queryRecord(ArticleRecord record) {
		record = sessionFactory.getCurrentSession().get(ArticleRecord.class, record.getRecordId());
		if(record == null) {
			return null;
		}
		return record;
	}

	@Override
	public ArticleRecord queryByUserIdAndTitleId(ArticleRecord record) {
		Query<ArticleRecord> query = sessionFactory.getCurrentSession().createQuery("from ArticleRecord where userId=?1 and titleId=?2", ArticleRecord.class)
				.setParameter(1, record.getUserId())
				.setParameter(2, record.getTitleId());
		ArticleRecord result = query.uniqueResult();
		
		return result;
	}

	public List<ArticleRecord> queryByTitleId(Integer titleId) {
		Query<ArticleRecord> query = sessionFactory.getCurrentSession().createQuery("from ArticleRecord where titleId=?1", ArticleRecord.class)
				.setParameter(1, titleId);
		List<ArticleRecord> result = query.list();	
		return result;
	}
	
	@Override
	public ArticleRecord updateByUserIdAndTitleId(ArticleRecord record) {
		System.out.println("update record , record: "+record.getRecord());
		
		ArticleRecord result = sessionFactory.getCurrentSession().get(ArticleRecord.class, record.getRecordId());
		result.setRecord(record.getRecord());
		return result;
	}

	@Override
	public boolean deleteRecord(ArticleRecord record) {
		ArticleRecord rs = sessionFactory.getCurrentSession().get(ArticleRecord.class, record.getRecordId());
		if(rs != null) {
			sessionFactory.getCurrentSession().delete(rs);
			return true;
		}
		return false;
	}

}
