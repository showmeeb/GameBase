package com.gamebase.article.model;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name = "articleTitle")
public class ArticleTitle {

	private Integer titleId;
	private Integer forumId;
	private String titleName;
	private String firstFigure;
	private String createTime;
	private String lastReplyTime;
	private Boolean news;/* 新聞文章 */
	private String newsAddress;/* 新聞網址 */
	private Integer clickNum;
	private Integer likeNum;
	private Integer unlikeNum;

	public ArticleTitle() {
	}
	
	public ArticleTitle(Integer forumId) {
		this.forumId = forumId;
	}

	public ArticleTitle(Integer forumId, String titleName, String firstFigure) {
		this.forumId = forumId;
		this.titleName = titleName;
		this.firstFigure = firstFigure;
		this.createTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
		this.news = false;
		this.clickNum = 0;
		this.likeNum = 0;
		this.unlikeNum = 0;
	}

	public ArticleTitle(Integer forumId, String titleName, String firstFigure, String newsAddress) {
		this.forumId = forumId;
		this.titleName = titleName;
		this.firstFigure = firstFigure;
		this.createTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
		this.news = true;
		this.newsAddress = newsAddress;
		this.clickNum = 0;
		this.likeNum = 0;
		this.unlikeNum = 0;
	}

	@Id
	@Column(name = "TITLEID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getTitleId() {
		return titleId;
	}

	public void setTitleId(Integer titleId) {
		this.titleId = titleId;
	}

	@Column(name = "FORUMID")
	public Integer getForumId() {
		return forumId;
	}

	public void setForumId(Integer forumId) {
		this.forumId = forumId;
	}

	@Column(name = "TITLENAME")
	public String getTitleName() {
		return titleName;
	}

	public void setTitleName(String titleName) {
		this.titleName = titleName;
	}

	@Column(name = "FIRSTFIGURE")
	public String getFirstFigure() {
		return firstFigure;
	}

	public void setFirstFigure(String firstFigure) {
		this.firstFigure = firstFigure;
	}

	@Column(name = "CREATETIME")
	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	@Column(name = "LASTREPLYTIME")
	public String getLastReplyTime() {
		return lastReplyTime;
	}

	public void setLastReplyTime(String lastReplyTime) {
		this.lastReplyTime = lastReplyTime;
	}

	@Column(name = "NEWS")
	public Boolean isNews() {
		return news;
	}

	public void setNews(Boolean news) {
		this.news = news;
	}

	@Column(name = "NEWSADDRESS")
	public String getNewsAddress() {
		return newsAddress;
	}

	public void setNewsAddress(String newsAddress) {
		this.newsAddress = newsAddress;
	}

	@Column(name = "CLICKNUM")
	public Integer getClickNum() {
		return clickNum;
	}

	public void setClickNum(Integer clickNum) {
		this.clickNum = clickNum;
	}

	@Column(name = "LIKENUM")
	public Integer getLikeNum() {
		return likeNum;
	}

	public void setLikeNum(Integer likeNum) {
		this.likeNum = likeNum;
	}

	@Column(name = "UNLIKENUM")
	public Integer getUnlikeNum() {
		return unlikeNum;
	}

	public void setUnlikeNum(Integer unlikeNum) {
		this.unlikeNum = unlikeNum;
	}

}
