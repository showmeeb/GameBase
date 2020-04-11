package com.gamebase.article.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gamebase.article.model.Forum;
import com.gamebase.article.model.dao.ForumDAO;

@Service
public class ForumService implements IForumService {

	private ForumDAO forumDao;

	@Autowired
	public ForumService(ForumDAO forumDao) {
		this.forumDao = forumDao;
	}

	@Override
	public Forum insertForum(Forum forum) {
		Forum forumBean = forumDao.insertForum(forum);
		return forumBean;
	}

	@Override
	public Forum queryOneForum(Forum forum) {
		return forumDao.queryOneForum(forum);
	}

	@Override
	public List<Forum> queryAllForum() {
		return forumDao.queryAllForum();
	}

	@Override
	public Forum updateOneForum(Forum forum) {
		return forumDao.updateOneForum(forum);
	}

	@Override
	public boolean deleteOneForum(Forum forum) {
		return forumDao.deleteOneForum(forum);
	}

}
