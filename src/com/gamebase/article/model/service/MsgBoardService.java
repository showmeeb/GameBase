package com.gamebase.article.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gamebase.article.model.MsgBoard;
import com.gamebase.article.model.dao.MsgBoardDAO;

@Service
public class MsgBoardService implements IMsgBoardService {

	private MsgBoardDAO mbDao;
	
	@Autowired
	public MsgBoardService(MsgBoardDAO mbDao) {
		this.mbDao = mbDao;
	}
	
	@Override
	public MsgBoard insertMsg(MsgBoard mb) {
		MsgBoard mbBean = mbDao.insertMsg(mb);
		return mbBean;
	}

	@Override
	public MsgBoard queryOneMsg(MsgBoard mb) {
		return mbDao.queryOneMsg(mb);
	}

	@Override
	public List<MsgBoard> queryParentMsg(MsgBoard mb) {
		return mbDao.queryParentMsg(mb);
	}

	@Override
	public MsgBoard updateOneMsg(MsgBoard mb) {
		return mbDao.updateOneMsg(mb);
	}

	@Override
	public boolean deleteOneMsg(MsgBoard mb) {
		return mbDao.deleteOneMsg(mb);
	}

}
