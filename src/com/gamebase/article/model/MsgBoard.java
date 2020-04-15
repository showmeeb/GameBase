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

@Entity
@Table(name = "msgBoard")
@Component
public class MsgBoard {

	private int id;
	private int parentId;
	private int userId;
	private String boardLocation;
	private String boardTitle;
	private String content;
	private String recordTime;
	private String createTime;

	public MsgBoard() {
	}

	public MsgBoard(String boardLocation) {
		this.boardLocation = boardLocation;
	}

	public MsgBoard(Integer parentId, Integer userId, String boardLocation, String boardTitle, String content) {
		this.parentId = parentId;
		this.userId = userId;
		this.boardLocation = boardLocation;
		this.boardTitle = boardTitle;
		this.content = content;
		this.recordTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
		this.createTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
	}
	
	public MsgBoard(String content, String recordTime) {
		this.content = content;
		this.recordTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
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

	@Column(name = "PARENTID")
	public int getParentId() {
		return parentId;
	}

	public void setParentId(int parentId) {
		this.parentId = parentId;
	}
	@Column(name = "USERID")
	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}
	@Column(name = "BOARDLOCATION")
	public String getBoardLocation() {
		return boardLocation;
	}

	public void setBoardLocation(String boardLocation) {
		this.boardLocation = boardLocation;
	}
	@Column(name = "BOARDTITLE")
	public String getBoardTitle() {
		return boardTitle;
	}

	public void setBoardTitle(String boardTitle) {
		this.boardTitle = boardTitle;
	}
	@Column(name = "CONTENT")
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	@Column(name = "RECORDTIME")
	public String getRecordTime() {
		return recordTime;
	}

	public void setRecordTime(String recordTime) {
		this.recordTime = recordTime;
	}
	@Column(name = "CREATETIME")
	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

}
