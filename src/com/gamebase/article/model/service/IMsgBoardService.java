package com.gamebase.article.model.service;

import java.util.List;

import com.gamebase.article.model.MsgBoard;

public interface IMsgBoardService {
	public MsgBoard insertMsg(MsgBoard mb);

	public MsgBoard queryOneMsg(MsgBoard mb);

	public List<MsgBoard> querySomeMsg(MsgBoard mb);

	public MsgBoard updateOneMsg(MsgBoard mb);

	public boolean deleteOneMsg(MsgBoard mb);
}
