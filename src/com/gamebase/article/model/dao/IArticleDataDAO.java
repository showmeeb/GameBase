package com.gamebase.article.model.dao;

import java.util.List;

import com.gamebase.article.model.ArticleData;

public interface IArticleDataDAO {

	public ArticleData insertData(ArticleData data);

	public ArticleData queryData(ArticleData data);

	public List<ArticleData> queryByTitleId(ArticleData data);

	public List<ArticleData> queryByforumId(ArticleData data);

	public ArticleData updateyByArticleDataId(ArticleData data);

	public boolean deleteOneData(ArticleData data);
}
