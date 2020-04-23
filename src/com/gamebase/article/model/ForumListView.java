package com.gamebase.article.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.springframework.stereotype.Component;

@Entity
@Table(name = "forumListView")
@Component
public class ForumListView {

	private Integer forumListId;
	private Integer forumRank;
	private Integer forumId;
	private String forumName;
	private String forumFigure;
	private Integer titleId;
	private String titleName;
	private String firstFigure;
	private Integer clickNum;
	private Integer likeNum;
	private Integer clickRN;
	private Integer likeRN;

	public ForumListView() {
	}

	public ForumListView(Integer forumId) {
		this.forumId = forumId;
	}

	public ForumListView(Integer forumId, Integer clickRN) {
		this.forumId = forumId;
		this.clickRN = clickRN;
	}

	@Id
	@Column(name = "FORUMLISTID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getForumListId() {
		return forumListId;
	}

	public void setForumListId(Integer forumListId) {
		this.forumListId = forumListId;
	}

	@Column(name = "FORUMRANK")
	public Integer getForumRank() {
		return forumRank;
	}

	public void setForumRank(Integer forumRank) {
		this.forumRank = forumRank;
	}

	@Column(name = "FORUMID")
	public Integer getForumId() {
		return forumId;
	}

	public void setForumId(Integer forumId) {
		this.forumId = forumId;
	}

	@Column(name = "FORUMNAME")
	public String getForumName() {
		return forumName;
	}

	public void setForumName(String forumName) {
		this.forumName = forumName;
	}

	@Column(name = "FORUMFIGURE")
	public String getForumFigure() {
		return forumFigure;
	}

	public void setForumFigure(String forumFigure) {
		this.forumFigure = forumFigure;
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

	@Column(name = "CLICKRN")
	public Integer getClickRN() {
		return clickRN;
	}

	public void setClickRN(Integer clickRN) {
		this.clickRN = clickRN;
	}

	@Column(name = "LIKERN")
	public Integer getLikeRN() {
		return likeRN;
	}

	public void setLikeRN(Integer likeRN) {
		this.likeRN = likeRN;
	}
	
}
