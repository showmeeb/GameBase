package com.gamebase.member.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gamebase.member.model.Rank;
import com.gamebase.member.model.Role;
import com.gamebase.member.model.UserData;
import com.gamebase.member.model.dao.RankDAO;
import com.gamebase.member.model.dao.RoleDAO;
import com.gamebase.member.model.dao.UserDataDAO;

@Service
public class UserDataService implements IUserDataService, IRankService, IRoleService {

	@Autowired
	private UserDataDAO udDao;
	@Autowired
	private RankDAO rankDao;
	@Autowired
	private RoleDAO roleDao;

	@Override
	public UserData getByLogin(String account, String password) {
		return udDao.getByLogin(account, password);
	}

	@Override
	public List<UserData> getAllUserData() {

		return udDao.getAllUserData();
	}

	@Override
	public boolean checkAccount(String account) {

		return udDao.checkAccount(account);

	}

	@Override
	public UserData getByUserId(Integer userId) {

		return udDao.getByUserId(userId);
	}

	@Override
	public void deleteUserData(UserData userData) {

		udDao.deleteUserData(userData);

	}

	@Override
	public void saveUserData(UserData userData) {

		udDao.saveUserData(userData);

	}

	@Override
	public Role getRoleByUserId(Integer usreId) {
		return roleDao.getRoleByUserId(usreId);
	}

	@Override
	public void changeRole(Role role) {
		roleDao.changeRole(role);
	}

	@Override
	public Rank getByRankId(Integer rankId) {
		return rankDao.getByRankId(rankId);
	}

	@Override
	public List<Rank> getAllRank() {
		return rankDao.getAllRank();
	}

}
