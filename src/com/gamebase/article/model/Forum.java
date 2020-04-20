package com.gamebase.article.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.springframework.stereotype.Component;

@Entity
@Table(name = "forum")
@Component
public class Forum {

	private Integer forumId;
	private String forumName;
	private String forumFigure;

	public Forum() {
	}

	public Forum(Integer forumId) {
		this.forumId = forumId;
	}

	public Forum(String forumName, String forumFigure) {
		this.forumName = forumName;
		this.forumFigure = forumFigure;
	}

	@Column(name = "FORUMID")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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
}
