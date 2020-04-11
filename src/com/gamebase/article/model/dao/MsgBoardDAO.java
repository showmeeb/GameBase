package com.gamebase.article.model.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.gamebase.article.model.MsgBoard;

@Repository
public class MsgBoardDAO implements IMsgBoardDAO {
	
	private SessionFactory sessionFactory;

	@Autowired
	public MsgBoardDAO(@Qualifier("sessionFactory")SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public MsgBoard insertMsg(MsgBoard mb) {
		List<MsgBoard> query = sessionFactory.getCurrentSession().createQuery("from MsgBoard where "
				+ "parentId=?1 and userId=?2 and boardLocation=?3 and boardTitle=?4 and content=?5 "
				+ "and recordTime=?6 and createTime=?7", MsgBoard.class)
				.setParameter(1, mb.getParentId())
				.setParameter(2, mb.getUserId())
				.setParameter(3, mb.getBoardLocation())
				.setParameter(4, mb.getBoardTitle())
				.setParameter(5, mb.getContent())
				.setParameter(6, mb.getRecordTime())
				.setParameter(7, mb.getCreateTime()).list();
		if (query.size() > 0) {
			return null;
		}
		sessionFactory.getCurrentSession().save(mb);
		return mb;
	}

	@Override
	public MsgBoard queryOneMsg(MsgBoard mb) {
		mb = sessionFactory.getCurrentSession().get(MsgBoard.class, mb.getId());
		return mb;
	}

	@Override
	public List<MsgBoard> queryParentMsg(MsgBoard mb) {
		Query<MsgBoard> query = sessionFactory.getCurrentSession().createQuery("from MsgBoard where boardLocation=:boardLocation and parentId=:parentId", MsgBoard.class)
				.setParameter("boardLocation", mb.getBoardLocation())
				.setParameter("parentId", 0);
		List<MsgBoard> list = query.list();
		return list;
	}

	@Override
	public MsgBoard updateOneMsg(MsgBoard mb) {
		MsgBoard result = sessionFactory.getCurrentSession().get(MsgBoard.class, mb.getId());
		result.setBoardTitle(mb.getBoardTitle());
		result.setContent(mb.getContent());
		sessionFactory.getCurrentSession().update(result);
		return result;
	}

	@Override
	public boolean deleteOneMsg(MsgBoard mb) {
		MsgBoard rs = sessionFactory.getCurrentSession().get(MsgBoard.class, mb.getId());
		if(rs!=null) {
			sessionFactory.getCurrentSession().delete(rs);
			return true;
		}
		return false;
	}

}
