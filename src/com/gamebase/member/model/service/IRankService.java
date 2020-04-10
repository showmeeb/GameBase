package com.gamebase.member.model.service;

import java.util.List;

import com.gamebase.member.model.Rank;

public interface IRankService {
	public Rank getByRankId(Integer rankId);
	public List<Rank> getAllRank();
}
