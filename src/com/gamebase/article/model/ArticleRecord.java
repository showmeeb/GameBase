package com.gamebase.article.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name = "articleRecord")
public class ArticleRecord {

	private Integer recordId;
	private Integer userId;
	private Integer titleId;
	private String record;

	public ArticleRecord() {
	}

	public ArticleRecord(Integer userId, Integer titleId) {
		this.userId = userId;
		this.titleId = titleId;
	}

	public ArticleRecord(Integer userId, Integer titleId, String record) {
		this.userId = userId;
		this.titleId = titleId;
		this.record = record;
	}

	@Id
	@Column(name = "RECORDID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getRecordId() {
		return recordId;
	}

	public void setRecordId(Integer recordId) {
		this.recordId = recordId;
	}

	@Column(name = "USERID")
	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	@Column(name = "TITLEID")
	public Integer getTitleId() {
		return titleId;
	}

	public void setTitleId(Integer titleId) {
		this.titleId = titleId;
	}

	@Column(name = "RECORD")
	public String getRecord() {
		return record;
	}

	public void setRecord(String record) {
		this.record = record;
	}

}
