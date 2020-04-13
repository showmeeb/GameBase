package com.gamebase.article.model.dao;

import java.util.List;

import com.gamebase.article.model.MsgBoard;

public interface IMsgBoardDAO {
	public MsgBoard insertMsg(MsgBoard mb);

	public MsgBoard queryOneMsg(MsgBoard mb);

	public List<MsgBoard> queryParentMsg(MsgBoard mb);
	
	public List<MsgBoard> queryArticlesByParentId(int parentId);

	public MsgBoard updateOneMsg(MsgBoard mb);

	public boolean deleteOneMsg(MsgBoard mb);
}
