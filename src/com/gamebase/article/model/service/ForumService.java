package com.gamebase.article.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gamebase.article.model.Forum;
import com.gamebase.article.model.ForumListView;
import com.gamebase.article.model.dao.ArticleTitleDAO;
import com.gamebase.article.model.dao.ForumDAO;
import com.gamebase.article.model.dao.vForumListViewDAO;

@Service
public class ForumService implements IForumService {

	@Autowired
	private ForumDAO forumDao;
	@Autowired
	private ArticleTitleDAO titleDao;
	@Autowired
	private vForumListViewDAO flvDao;

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

	public List<ForumListView> queryForumListByClickNum(Integer clickRN){
		return flvDao.queryForumListByClickNum(clickRN);
	}
	
	public List<ForumListView> queryForumListByLikeNum(Integer likeRN){
		return flvDao.queryForumListByLikeNum(likeRN);
	}

}
