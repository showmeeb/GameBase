package com.gamebase.article.model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ArticleTitle {

	private Integer titleId;
	private Integer forumId;
	private String titleName;
	private String firstFigure;
	private String createTime;
	private String lastReplyTime;	
	private boolean news;/*新聞文章*/
	private String newsAddress;/*新聞網址*/

	public ArticleTitle() {
	}

	public ArticleTitle(Integer forumId, String titleName, String firstFigure) {
		this.forumId = forumId;
		this.titleName = titleName;
		this.firstFigure = firstFigure;
		this.createTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
		this.news = false;
	}
	
	public ArticleTitle(Integer forumId, String titleName, String firstFigure, String newsAddress) {
		this.forumId = forumId;
		this.titleName = titleName;
		this.firstFigure = firstFigure;
		this.createTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
		this.news = true;
		this.newsAddress = newsAddress;
	}

	public Integer getTitleId() {
		return titleId;
	}

	public void setTitleId(Integer titleId) {
		this.titleId = titleId;
	}

	public Integer getForumId() {
		return forumId;
	}

	public void setForumId(Integer forumId) {
		this.forumId = forumId;
	}

	public String getTitleName() {
		return titleName;
	}

	public void setTitleName(String titleName) {
		this.titleName = titleName;
	}

	public String getFirstFigure() {
		return firstFigure;
	}

	public void setFirstFigure(String firstFigure) {
		this.firstFigure = firstFigure;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getLastReplyTime() {
		return lastReplyTime;
	}

	public void setLastReplyTime(String lastReplyTime) {
		this.lastReplyTime = lastReplyTime;
	}

	public boolean isNews() {
		return news;
	}

	public void setNews(boolean news) {
		this.news = news;
	}

	public String getNewsAddress() {
		return newsAddress;
	}

	public void setNewsAddress(String newsAddress) {
		this.newsAddress = newsAddress;
	}

}
