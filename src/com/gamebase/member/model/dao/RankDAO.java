package com.gamebase.member.model.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.gamebase.member.model.Rank;

@Repository
public class RankDAO implements IRankDAO {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public Rank getByRankId(Integer rankId) {
		Rank rank = sessionFactory.getCurrentSession().get(Rank.class, rankId);
		System.out.println("Rank: " + rank);
		return rank;
	}

	@Override
	public List<Rank> getAllRank() {
		List<Rank> ranks = sessionFactory.getCurrentSession().createQuery("From Rank", Rank.class).list();
		return ranks;
	}

}
