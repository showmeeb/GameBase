package com.gamebase.member.model.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gamebase.member.model.Friends;
import com.gamebase.member.model.Rank;
import com.gamebase.member.model.Role;
import com.gamebase.member.model.UserData;
import com.gamebase.member.model.UserProfile;
import com.gamebase.member.model.UsersInfo;
import com.gamebase.member.model.dao.EncryptDAO;
import com.gamebase.member.model.dao.MailSenderDAO;
import com.gamebase.member.model.dao.RankDAO;
import com.gamebase.member.model.dao.RoleDAO;
import com.gamebase.member.model.dao.UserDataDAO;
import com.gamebase.member.model.dao.UserProfileDAO;
import com.gamebase.member.model.dao.UsersInfoDAO;

@Service
@Transactional
public class UserDataService {

	@Autowired
	private UserDataDAO udDao;
	@Autowired
	private RankDAO rankDao;
	@Autowired
	private RoleDAO roleDao;
	@Autowired
	private UserProfileDAO upDao;
	@Autowired
	private MailSenderDAO mDao;
	@Autowired
	private EncryptDAO eDao;
	@Autowired
	private UsersInfoDAO uiDao;

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

	public UsersInfo showUserData(String account) {
		UsersInfo usersInfo = uiDao.selectById(account);

		if (usersInfo != null) {
			// get friend list
			List<UsersInfo> list = uiDao.getFriendList(usersInfo.getUserId());

			// set friend list
			usersInfo.setFriendsList(list);

			return usersInfo;
		} else {
			return null;
		}
	}

	public UsersInfo showUserData(Integer userNo) {
		UsersInfo usersInfo = uiDao.selectByNo(userNo);

		if (usersInfo != null) {
			// get friend list
			List<UsersInfo> list = uiDao.getFriendList(usersInfo.getUserId());

			// set friend list
			usersInfo.setFriendsList(list);

			return usersInfo;
		} else {
			return null;
		}
	}

	public void saveUserPrfile(UserProfile userProfile) {
		upDao.saveUserProfile(userProfile);
	}

	public UserProfile updateUserProfile(UserProfile userProfile) {
		return upDao.updateUserProfile(userProfile);
	}

	public Map<String, String> mailAction(String acc, String email) {
		return mDao.mailAction(acc, email);
	}

	public String getMD5Endocing(String message) {
		return eDao.getMD5Endocing(message);
	}

	public String getSHA1Endocing(String message) {
		return eDao.getSHA1Endocing(message);
	}

	public String encryptString(String message) {
		return eDao.encryptString(message);
	}

	public String decryptString(String stringToDecrypt) {
		return eDao.decryptString(stringToDecrypt);
	}
}
