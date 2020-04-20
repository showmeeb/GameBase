package com.gamebase.article.model.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.gamebase.article.model.Forum;

@Repository
public class ForumDAO implements IForumDAO {

	private SessionFactory sessionFactory;

	@Autowired
	public ForumDAO(@Qualifier("sessionFactory") SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public Forum insertForum(Forum forum) {
		List<Forum> query = sessionFactory.getCurrentSession()
				.createQuery("from Forum where forumName=?1 and forumFigure=?2", Forum.class)
				.setParameter(1, forum.getForumName()).setParameter(2, forum.getForumFigure()).list();

		if (query.size() > 0) {
			return null;
		}
		sessionFactory.getCurrentSession().save(forum);
		return forum;
	}

	@Override
	public Forum queryOneForum(Forum forum) {
		forum = sessionFactory.getCurrentSession().get(Forum.class, forum.getForumId());
		return forum;
	}

	@Override
	public List<Forum> queryAllForum() {
		Query<Forum> query = sessionFactory.getCurrentSession().createQuery("from Forum", Forum.class);
		List<Forum> list = query.list();
		return list;
	}

	@Override
	public Forum updateOneForum(Forum forum) {
		Forum result = sessionFactory.getCurrentSession().get(Forum.class, forum.getForumId());
		result.setForumName(forum.getForumName());
		result.setForumFigure(forum.getForumFigure());
		sessionFactory.getCurrentSession().update(result);
		return result;
	}

	@Override
	public boolean deleteOneForum(Forum forum) {
		Forum rs = sessionFactory.getCurrentSession().get(Forum.class, forum.getForumId());
		if (rs != null) {
			sessionFactory.getCurrentSession().delete(rs);
			return true;
		}
		return false;
	}

}
