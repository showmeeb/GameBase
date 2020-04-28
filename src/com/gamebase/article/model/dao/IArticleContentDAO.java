package com.gamebase.article.model.dao;

import java.util.List;

import com.gamebase.article.model.ArticleContent;

public interface IArticleContentDAO {

	public ArticleContent insertContent(ArticleContent content);

	public ArticleContent queryOneContent(ArticleContent content);

	public List<ArticleContent> querySomeContentByTitleId(ArticleContent content);

	public List<ArticleContent> querySomeContentByUserId(ArticleContent content);

	public ArticleContent updateByContentId(ArticleContent content);

	/*manager delete*/
	public boolean deleteByContentId(ArticleContent content);
	//後台
	public List<ArticleContent> queryMemberContentByUserId(int id);
}
