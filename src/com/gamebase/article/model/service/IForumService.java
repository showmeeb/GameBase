package com.gamebase.article.model.service;

import java.util.List;

import com.gamebase.article.model.Forum;

public interface IForumService {
	public Forum insertForum(Forum forum);

	public Forum queryOneForum(Forum forum);

	public List<Forum> queryAllForum();

	public Forum updateOneForum(Forum forum);

	public boolean deleteOneForum(Forum forum);
}
