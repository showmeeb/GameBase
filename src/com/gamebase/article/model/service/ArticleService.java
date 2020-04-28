package com.gamebase.article.model.service;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gamebase.article.model.ArticleContent;
import com.gamebase.article.model.ArticleListView;
import com.gamebase.article.model.ArticleRecord;
import com.gamebase.article.model.ArticleTitle;
import com.gamebase.article.model.ContentListView;
import com.gamebase.article.model.dao.ArticleContentDAO;
import com.gamebase.article.model.dao.ArticleRecordDAO;
import com.gamebase.article.model.dao.ArticleTitleDAO;
import com.gamebase.article.model.dao.vArticleListViewDAO;
import com.gamebase.article.model.dao.vContentListViewDAO;
import com.gamebase.member.model.Friends;

@Service
@Transactional
public class ArticleService {

	@Autowired
	private ArticleTitleDAO titleDao;
	@Autowired
	private ArticleContentDAO contentDao;
	@Autowired
	private ArticleRecordDAO recordDao;
	@Autowired
	private vArticleListViewDAO alvDao;
	@Autowired
	private vContentListViewDAO clvDao;

	public List<ArticleTitle> queryTitleByForumId(Integer forumId) {
		return titleDao.querySomeArticleTitleByForumId(forumId);
	}

	public ArticleTitle queryTitleByTitleId(Integer titleId) {
		return titleDao.queryOneArticleTitle(titleId);
	}

	public List<ArticleContent> queryContentByTitleId(ArticleContent content) {
		return contentDao.querySomeContentByTitleId(content);
	}

	public ArticleContent querytOneContentByContentId(ArticleContent content) {
		return contentDao.queryOneContent(content);
	}

	public ArticleRecord queryRecordByUserIdAndTitleId(ArticleRecord record) {
		return recordDao.queryByUserIdAndTitleId(record);
	}
	
	//後臺全部文章列表
	public List<ArticleTitle> queryAllArticleTitle(){
		return titleDao.queryAllArticleTitle();		
	}
	//後臺個人文章列表
	public List<ArticleListView> queryMyArticle(Integer id){
		return alvDao.queryArticleListByUserId(id);		
	}
	
	public List<ArticleRecord> queryRecordsByTitleId(Integer titleId) {
		return recordDao.queryByTitleId(titleId);
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

	public List<ArticleListView> queryArticleListByContentRN(Integer contentRN, Integer forumId) {
		return alvDao.queryArticleListByContentRN(contentRN, forumId);
	}

	public List<ContentListView> queryContentListByTitleId(Integer titleId) {
		return clvDao.queryContentListByContentRN(titleId);
	}

	public ArticleTitle updateTitleData(ArticleTitle title, String original, String btn) {
		if (original.equals(btn)) {
			if (btn.equals("like")) {
				title.setLikeNum(title.getLikeNum() - 1);
			} else {
				title.setUnlikeNum(title.getUnlikeNum() - 1);
			}
		} else {
			if (btn.equals("like")) {
				title.setLikeNum(title.getLikeNum() + 1);
			} else {
				title.setUnlikeNum(title.getUnlikeNum() + 1);
			}
		}
		return titleDao.updateOneArticleTitle(title);
	}

	public Boolean deleteArticleAndReply(Integer titleId) {
		Iterator<?> it;
		try {
			/*drop record*/		
			List<ArticleRecord> recordList = queryRecordsByTitleId(titleId);
			it = recordList.iterator();
			while(it.hasNext()) {
				ArticleRecord record = (ArticleRecord)it.next();
				Integer recordId = record.getRecordId();	
				deleteRcord(recordId);			
			}
			/*drop content and reply*/
			List<ContentListView> contentList = queryContentListByTitleId(titleId);
			it = contentList.iterator();		
			while(it.hasNext()) {
				ContentListView contentView = (ContentListView)it.next();
				Integer contentId = contentView.getContentId();			
				deleteReply(contentId);			
			}
			/*drop title*/
			deletetitle(titleId);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}		
	}

	public Boolean deleteReply(Integer contentId) {
		ArticleContent content = new ArticleContent();
		content.setContentId(contentId);
		Boolean cStatus = contentDao.deleteByContentId(content);
		return cStatus;
	}

	public Boolean deleteRcord(Integer recordId) {
		ArticleRecord record = new ArticleRecord();
		record.setRecordId(recordId);
		Boolean rStatus = recordDao.deleteRecord(record);
		return rStatus;
	}

	public Boolean deletetitle(Integer titleId) {
		ArticleTitle title = new ArticleTitle();
		title.setTitleId(titleId);
		Boolean tStatus = titleDao.deleteOneArticleTitle(title);
		return tStatus;
	}
	
	public List<Friends> queryFriendsByUserId(Integer userId) {
		return clvDao.queryFriendsByUserId(userId);
	}
	
	public Friends queryFriendsByUserIdAndAuthorId(Integer userId, Integer authorId) {
		return clvDao.queryFriendByUserIdAndAuthorId(userId, authorId);
	}
	
	public Friends updateFriendsByUserIdAndAuthorId(Integer userId, Integer authorId) {
		return clvDao.updateFriendByUserIdAndAuthorId(userId, authorId);
	}
	
	//後台
	public List<ArticleContent> queryMemberContentByUserId(int id) {
		return contentDao.queryMemberContentByUserId(id);
	}
	//後台
	public List<ArticleTitle> querySomeArticleTitleByKeyInOneForum(Integer forumId, String title) {
		return titleDao.querySomeArticleTitleByKeyInOneForum(forumId,title);
	}
	//後台
	public List<ArticleTitle> querySomeArticleTitleByKeyInallForum(String title) {
		return titleDao.querySomeArticleTitleByKeyInallForum(title);
	}
}
