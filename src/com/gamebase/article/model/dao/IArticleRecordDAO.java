package com.gamebase.article.model.dao;

import java.util.List;

import com.gamebase.article.model.ArticleRecord;

public interface IArticleRecordDAO {
	
	public ArticleRecord insertRecord(ArticleRecord record);
	
	public ArticleRecord queryRecord(ArticleRecord record);/*暫時不用*/
	
	public ArticleRecord queryByUserIdAndTitleId(ArticleRecord record);
	
	public ArticleRecord updateByUserIdAndTitleId(ArticleRecord record);
	
	public boolean deleteRecord(ArticleRecord record);

}
