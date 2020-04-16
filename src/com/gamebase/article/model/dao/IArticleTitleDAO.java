package com.gamebase.article.model.dao;

import java.util.List;

import com.gamebase.article.model.ArticleTitle;

public interface IArticleTitleDAO {

	public ArticleTitle insertArticleTitle(ArticleTitle title);

	public ArticleTitle queryOneArticleTitle(ArticleTitle title);

	public List<ArticleTitle> querySomeArticleTitleByForumId(ArticleTitle title);

	public List<ArticleTitle> queryAllArticleTitle();

	public ArticleTitle updateOneArticleTitle(ArticleTitle title);

	public boolean deleteOneArticleTitle(ArticleTitle title);
}