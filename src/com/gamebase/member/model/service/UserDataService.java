package com.gamebase.member.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gamebase.member.model.UserData;
import com.gamebase.member.model.dao.UserDataDAO;

@Service
public class UserDataService implements IUserDataService{

	private UserDataDAO udDao;

	@Autowired
	public UserDataService(UserDataDAO udDao) {
		this.udDao=udDao;
	}

	@Override
	public UserData getByLogin(String account, String password) {
		return udDao.getByLogin(account, password);
	}

	@Override
	public List<UserData> getAllUserData() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean checkAccount(String account) {
		
		return udDao.checkAccount(account)
				;
	}

	@Override
	public UserData getByUserId(Integer userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteUserData(UserData userData) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void saveUserData(UserData userData) {
		// TODO Auto-generated method stub
		
	}
	
}
