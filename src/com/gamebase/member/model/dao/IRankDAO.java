package com.gamebase.member.model.dao;

import java.util.List;

import com.gamebase.member.model.Rank;

public interface IRankDAO {
	public Rank getByRankId(Integer rankId);
	public List<Rank> getAllRank();
}
