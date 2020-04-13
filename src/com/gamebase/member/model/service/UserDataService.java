package com.gamebase.member.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gamebase.member.model.Rank;
import com.gamebase.member.model.Role;
import com.gamebase.member.model.UserData;
import com.gamebase.member.model.dao.RankDAO;
import com.gamebase.member.model.dao.RoleDAO;
import com.gamebase.member.model.dao.UserDataDAO;

@Service
@Transactional
public class UserDataService {

	@Autowired
	private UserDataDAO udDao;
	@Autowired
	private RankDAO rankDao;
	@Autowired
	private RoleDAO roleDao;

	public UserData getByLogin(String account, String password) {
		return udDao.getByLogin(account, password);
	}

	public List<UserData> getAllUserData() {
		return udDao.getAllUserData();
	}

	public boolean checkAccount(String account) {
		return udDao.checkAccount(account);
	}

	public UserData getByUserId(Integer userId) {
		return udDao.getByUserId(userId);
	}

	public void deleteUserData(UserData userData) {
		udDao.deleteUserData(userData);
	}

	public void saveUserData(UserData userData) {
		udDao.saveUserData(userData);
	}

	public Role getRoleByUserId(Integer usreId) {
		return roleDao.getRoleByUserId(usreId);
	}

	public void changeRole(Role role) {
		roleDao.changeRole(role);
	}

	public Rank getByRankId(Integer rankId) {
		return rankDao.getByRankId(rankId);
	}

	public List<Rank> getAllRank() {
		return rankDao.getAllRank();
	}

	public UserData getByAccount(String account) {
		return udDao.getByAccount(account);
	}
}
