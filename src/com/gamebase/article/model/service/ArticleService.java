package com.gamebase.article.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gamebase.article.model.ArticleContent;
import com.gamebase.article.model.ArticleRecord;
import com.gamebase.article.model.ArticleTitle;
import com.gamebase.article.model.dao.ArticleContentDAO;
import com.gamebase.article.model.dao.ArticleRecordDAO;
import com.gamebase.article.model.dao.ArticleTitleDAO;
import com.gamebase.member.model.UserProfile;

@Service
@Transactional
public class ArticleService {

	@Autowired
	private ArticleTitleDAO titleDao;
	@Autowired
	private ArticleContentDAO contentDao;
	@Autowired
	private ArticleRecordDAO recordDao;

	public List<ArticleTitle> queryTitleByForumId(Integer forumId) {
		return titleDao.querySomeArticleTitleByForumId(forumId);
	}
	
	public ArticleTitle queryTitleByTitleId(Integer titleId) {
		return titleDao.queryOneArticleTitle(titleId);
	}

	public List<ArticleContent> queryContentByTitleId(ArticleContent content) {
		return contentDao.querySomeContentByTitleId(content);
	}

	public ArticleRecord queryRecordByUserIdAndTitleId(ArticleRecord record) {
		return recordDao.queryByUserIdAndTitleId(record);
	}

	public ArticleTitle inertTitle(ArticleTitle title) {
		return titleDao.insertArticleTitle(title);
	}

	public ArticleContent insertContent(ArticleContent content) {
		return contentDao.insertContent(content);
	}

	public ArticleRecord insertRecord(ArticleRecord record) {
		return recordDao.insertRecord(record);
	}

	public ArticleTitle updateTitle(ArticleTitle title) {
		return titleDao.updateOneArticleTitle(title);
	}

	public ArticleContent updateContent(ArticleContent content) {
		return contentDao.updateByContentId(content);
	}

	public ArticleRecord updateRecord(ArticleRecord record) {
		return recordDao.updateByUserIdAndTitleId(record);
	}
	
//	public List<UserProfile> queryUserProfile() {
//		return contentDao.queryUserImgByUserId();
//	}

}
