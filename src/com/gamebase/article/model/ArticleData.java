package com.gamebase.article.model;

public class ArticleData {

	private Integer articleDataId;
	private Integer titleId;
	private Integer clickNum;
	private Integer likeNum;
	private Integer unlikeNum;
	private Integer shareNum;

	public ArticleData() {
	}

	public ArticleData(Integer titleId) {
		this.titleId = titleId;
		this.clickNum = 0;
		this.likeNum = 0;
		this.unlikeNum = 0;
		this.shareNum = 0;
	}
	
	public ArticleData(Integer titleId, Integer clickNum, Integer likeNum, Integer unlikeNum, Integer shareNum) {
		this.titleId = titleId;
		this.clickNum = clickNum;
		this.likeNum = likeNum;
		this.unlikeNum = unlikeNum;
		this.shareNum = shareNum;
	}

	public Integer getArticleDataId() {
		return articleDataId;
	}

	public void setArticleDataId(Integer articleDataId) {
		this.articleDataId = articleDataId;
	}

	public Integer getTitleId() {
		return titleId;
	}

	public void setTitleId(Integer titleId) {
		this.titleId = titleId;
	}

	public Integer getClickNum() {
		return clickNum;
	}

	public void setClickNum(Integer clickNum) {
		this.clickNum = clickNum;
	}

	public Integer getLikeNum() {
		return likeNum;
	}

	public void setLikeNum(Integer likeNum) {
		this.likeNum = likeNum;
	}

	public Integer getUnlikeNum() {
		return unlikeNum;
	}

	public void setUnlikeNum(Integer unlikeNum) {
		this.unlikeNum = unlikeNum;
	}

	public Integer getShareNum() {
		return shareNum;
	}

	public void setShareNum(Integer shareNum) {
		this.shareNum = shareNum;
	}

}
