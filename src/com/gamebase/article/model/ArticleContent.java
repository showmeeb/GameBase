package com.gamebase.article.model;

import java.sql.Timestamp;
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
@Table(name = "articleContent")
public class ArticleContent {

	private Integer contentId;
	private Integer titleId;
	private Integer userId;
	private String content;
	private Timestamp createTime;
	private Timestamp updateTime;

	public ArticleContent() {
	}

	public ArticleContent(Integer titleId) {
		this.titleId = titleId;
		this.updateTime = new Timestamp(System.currentTimeMillis());
	}
//	Timestamp ts = new Timestamp(System.currentTimeMillis());

	/* be used when insert new content */
	public ArticleContent(Integer titleId, Integer userId, String content) {
		this.titleId = titleId;
		this.userId = userId;
		this.content = content;
		this.createTime = new Timestamp(System.currentTimeMillis());
		this.updateTime = new Timestamp(System.currentTimeMillis());
	}

	@Id
	@Column(name = "CONTENTID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getContentId() {
		return contentId;
	}

	public void setContentId(Integer contentId) {
		this.contentId = contentId;
	}

	@Column(name = "TITLEID")
	public Integer getTitleId() {
		return titleId;
	}

	public void setTitleId(Integer titleId) {
		this.titleId = titleId;
	}

	@Column(name = "USERID")
	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	@Column(name = "CONTENT")
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Column(name = "CREATETIME")
	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	@Column(name = "UPDATETIME")
	public Timestamp getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Timestamp times) {
		this.updateTime = times;
	}

}
