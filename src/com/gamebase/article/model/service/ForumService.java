package com.gamebase.article.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gamebase.article.model.Forum;
import com.gamebase.article.model.dao.ArticleTitleDAO;
import com.gamebase.article.model.dao.ForumDAO;

@Service
public class ForumService implements IForumService {

	@Autowired
	private ForumDAO forumDao;
	@Autowired
	private ArticleTitleDAO titleDao;

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

	public List<Forum> queryForumAndTitle() {

		List<Forum> fList = forumDao.queryAllForum();

//		for (int i = 0; i <= fList.size(); i++) {
//			System.out.println("i = " + i);
//			int forumId = fList.get(i).getId();
//			System.out.println("forumid = " + forumId);
//			title.setForumId(forumId);
//			List<ArticleTitle> tList = titleDao.querySomeArticleTitleByForumId(title);
//			fList.get(i).setTitleList(tList);
//		}

//		Iterator it = fList.iterator();
//		while(it.hasNext()) {
//			ArticleTitle title = new ArticleTitle();
//			Forum forum = (Forum)it.next();
//			int forumId = forum.getId();
//			System.out.println("forumid = " + forumId);
//			title.setForumId(forumId);
//			List<ArticleTitle> tList = titleDao.querySomeArticleTitleByForumId(title);
//			forum.setTitleList(tList);
//		}

		return fList;
	}

}
