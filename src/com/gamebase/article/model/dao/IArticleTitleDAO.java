package com.gamebase.article.model.dao;

import java.util.List;

import com.gamebase.article.model.ArticleTitle;

public interface IArticleTitleDAO {

	public ArticleTitle insertArticleTitle(ArticleTitle title);

	public ArticleTitle queryOneArticleTitle(Integer titleId);

	public List<ArticleTitle> querySomeArticleTitleByForumId(Integer forumId);

	public List<ArticleTitle> queryAllArticleTitle();

	public ArticleTitle updateOneArticleTitle(ArticleTitle title);

	public boolean deleteOneArticleTitle(ArticleTitle title);
	
	public List<ArticleTitle> querySomeArticleTitleByKeyInOneForum(Integer forumId,String title);
	
	public List<ArticleTitle> querySomeArticleTitleByKeyInallForum(String title);
}
