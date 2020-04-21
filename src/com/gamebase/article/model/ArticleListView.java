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
@Table(name = "articleListView")
public class ArticleListView {

	private Integer forumId;
	private Integer titleId;
	private String titleName;
	private String firstFigure;
	private String content;
	private Integer contentId;
	private String createTime;
	private String lastReplyTime;
	private Integer clickNum;
	private Integer likeNum;
	private Integer unlikeNum;
	private Integer contentRN;

	public ArticleListView() {
	}

	public ArticleListView(Integer contentId) {
		this.contentId = contentId;
	}

	@Column(name = "FORUMID")
	public Integer getForumId() {
		return forumId;
	}

	public void setForumId(Integer forumId) {
		this.forumId = forumId;
	}

	@Column(name = "TITLEID")
	public Integer getTitleId() {
		return titleId;
	}

	public void setTitleId(Integer titleId) {
		this.titleId = titleId;
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

	@Column(name = "CONTENT")
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
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

	@Column(name = "CONTENTRN")
	public Integer getContentRN() {
		return contentRN;
	}

	public void setContentRN(Integer contentRN) {
		this.contentRN = contentRN;
	}

}
