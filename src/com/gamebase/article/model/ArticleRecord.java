package com.gamebase.article.model;

public class ArticleRecord {

	private Integer recordId;
	private Integer userId;
	private Integer titleId;
	private String record;

	public ArticleRecord() {
	}

	public ArticleRecord(Integer userId, Integer titleId, String record) {
		this.userId = userId;
		this.titleId = titleId;
		this.record = record;
	}

	public Integer getRecordId() {
		return recordId;
	}

	public void setRecordId(Integer recordId) {
		this.recordId = recordId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getTitleId() {
		return titleId;
	}

	public void setTitleId(Integer titleId) {
		this.titleId = titleId;
	}

	public String getRecord() {
		return record;
	}

	public void setRecord(String record) {
		this.record = record;
	}

}
