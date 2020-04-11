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

	private int id;
	private String forumName;
	private String forumFigure;
	
	public Forum() {		
	}
	
	public Forum(String forumName) {
		this.forumName = forumName;
	}
	
	public Forum(String forumName, String forumFigure) {
		this.forumName = forumName;
		this.forumFigure = forumFigure;
	}

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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
