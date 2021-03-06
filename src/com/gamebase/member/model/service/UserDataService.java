package com.gamebase.member.model.service;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.security.GeneralSecurityException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gamebase.general.model.Webflow;
import com.gamebase.general.model.dao.WebflowDAO;
import com.gamebase.member.model.Friends;
import com.gamebase.member.model.Rank;
//import com.gamebase.member.model.Role;
import com.gamebase.member.model.UserData;
import com.gamebase.member.model.UserProfile;
import com.gamebase.member.model.UsersInfo;
import com.gamebase.member.model.dao.EncryptDAO;
import com.gamebase.member.model.dao.MailSenderDAO;
import com.gamebase.member.model.dao.RankDAO;
//import com.gamebase.member.model.dao.RoleDAO;
import com.gamebase.member.model.dao.UserDataDAO;
import com.gamebase.member.model.dao.UserProfileDAO;
import com.gamebase.member.model.dao.UsersInfoDAO;
import com.google.api.client.auth.openidconnect.IdToken.Payload;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;

@Service
@Transactional
public class UserDataService {

	@Autowired
	private UserDataDAO udDao;
	@Autowired
	private RankDAO rankDao;
	@Autowired
	private UserProfileDAO upDao;
	@Autowired
	private MailSenderDAO mDao;
	@Autowired
	private EncryptDAO eDao;
	@Autowired
	private UsersInfoDAO uiDao;
	@Autowired
	private WebflowDAO webDAO;
	
	private final String GOOGLE_CLIENT_ID = "982957556355-9h99fuvvivi52g599iucre1v04ktheh0.apps.googleusercontent.com";
	
	public void sendPwdEmail(UserData userdata) {
		mDao.password(userdata);
	}
	
	public Map<String,String> randomPwd() {
		int i = (int) (Math.random() * (99999999 - 1000 + 1) + 150);
		String pwd = i+"";
		String encryptpwd=eDao.encryptString(pwd);
		Map<String,String> map = new HashMap<String,String>();
//		System.out.println("pwd:"+pwd+" , encryptpwd: " + encryptpwd);
		map.put("pwd", pwd);
		map.put("encryptpwd",encryptpwd);
		return map;
	}
	
	public UserData checkAccWithEmail(UserData userdata) {
		UserData findUserData = udDao.getByAccount(userdata.getAccount());
		if(findUserData!=null) {
			if(findUserData.getEmail().equals(userdata.getEmail())) {
				return findUserData;
			}
		}		
		return null;
	}
	
	public UsersInfo googleLogin(String idTokenStr) {
		// get verifier
		GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier
				.Builder(new NetHttpTransport(),JacksonFactory.getDefaultInstance())
				.setAudience(Collections.singletonList(GOOGLE_CLIENT_ID))
				.build();
		
		// set id token field
		GoogleIdToken idToken = null;
		
		try {
			idToken = verifier.verify(idTokenStr);
		} catch (GeneralSecurityException e) {
			System.out.println("驗證時出現GeneralSecurityException異常");
			return null;
		} catch (IOException e) {
			System.out.println("驗證時出現IOException異常");
			return null;
		}
		
		if (idToken != null) {
//			System.out.println("驗證成功.");
			
			//取得idToken內資料
			Payload payload = idToken.getPayload();
			String userId = payload.getSubject();
			String email=(String) payload.get("email");
			//String name = (String) payload.get("name");
			String pictureUrl = (String) payload.get("picture");
			
			
			// 預設密碼google
			String pwd = "google";
			// 密碼轉型
			pwd=eDao.encryptString(pwd);
			
			// examine whether the ID already registed
			UserData bean = udDao.getByLogin(userId, pwd);
			
			if(bean != null) {
				// if already registed , send data back
				UsersInfo usersLogin = showUserData(bean.getAccount());
				
				return usersLogin;
				
			}else {
				
				// if haven't exist , regist a new account
				bean = new UserData();
				bean.setAccount(userId);
				bean.setEmail(email);
				bean.setPassword(pwd);
				bean.setRankId(2);
				Date regTime = new Date();
				bean.setRegiestdate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(regTime.getTime()));
				udDao.saveUserData(bean);
				UserProfile up=new UserProfile();
				up.setUserId(bean.getUserId());
				up.setImg(pictureUrl);
				upDao.saveUserProfile(up);
				return  showUserData(bean.getAccount());
			}
			
		} else {
			System.out.println("Invalid ID token.");
			return null;
		}
	}

	public UserData getByLogin(String account, String password) {
		return udDao.getByLogin(account, password);
	}

	public Map<String, Object> getLogin(String account, String password) {
		Map<String, Object> map = new HashMap<String, Object>();
		UserData userData = udDao.getByLogin(account, password);
		if (userData != null) {
			UsersInfo userInfo = showUserData(userData.getAccount());
			if(userInfo.getTotal()!=null) {
				if(userInfo.getTotal()>=10000 && userInfo.getRankId()==2) {
					userData.setRankId(3);
					saveUserData(userData);
					UsersInfo newuserInfo = showUserData(userData.getAccount());
					System.out.println("rank:"+newuserInfo.getRankId());
					map.put("upgrade", true);
					map.put("loginUser",newuserInfo);
					map.put("status",true);
					return map;
				}else {
					map.put("loginUser", userInfo);
					map.put("status", true);
					return map;
				}
			}else {
				map.put("loginUser", userInfo);
//				map.put("UserData", userData);
				map.put("status", true);
				return map;
			}			
		}
		map.put("status", false);
		return map;
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

//	public Role getRoleByUserId(Integer usreId) {
//		return roleDao.getRoleByUserId(usreId);
//	}
//
//	public void changeRole(Role role) {
//		roleDao.changeRole(role);
//	}

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

	public Map<String, String> mailAction(String acc, String email) {
		return mDao.mailAction(acc, email);
	}

	public Map<String, String> authAction(String acc, String email) {
		return mDao.authAction(acc, email);
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

	public void logout(HttpServletRequest request) {
		udDao.logout(request);
	}

	public Integer getProfileIdByUserId(Integer userId) {
		Integer myId = upDao.getProfileIdByUserId(userId);
		System.out.println("SPId=" + myId);
		if (myId != null) {
			return myId;
		}
		return null;
	}

	public UserProfile getProfileByUserId(Integer userId) {
		UserProfile myBean = upDao.getProfileByUserId(userId);
		if (myBean != null) {
			return myBean;
		}
		return myBean;
	}

	public void setCookie(String account, String password, boolean save, HttpServletRequest request, HttpServletResponse response) {
		udDao.setCookie(account, password, save, request, response);
	}

	public void GetCookie(String account, String password, HttpServletRequest request) {
		udDao.GetCookie(account, password, request);
	}
	
	public List<UserData> getUserByAcInAllRank(String ac) {
		List<UserData> list = udDao.getuserbyacinallrank(ac);
		return list;
	}
	
	public List<UserData> getUserByAcInOneRank(String ac ,Integer rank) {
		List<UserData> list = udDao.getuserbyacinonerank(rank, ac);
		return list;
	}
	
	public String getIp(HttpServletRequest httpRequest) {
		String ipAddress = httpRequest.getHeader("x-forwarded-for");
		if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
			ipAddress = httpRequest.getHeader("Proxy-Client-IP");
		}
		if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
			ipAddress = httpRequest.getHeader("WL-Proxy-Client-IP");
		}
		if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
			ipAddress = httpRequest.getRemoteAddr();
			if (ipAddress.equals("127.0.0.1") || ipAddress.equals("0:0:0:0:0:0:0:1")) {
				// ?寞?蝬脩窗?∪??祆??蔭?P
				InetAddress inet = null;
				try {
					inet = InetAddress.getLocalHost();
				} catch (UnknownHostException e) {
					e.printStackTrace();
				}
				ipAddress = inet.getHostAddress();
			}
		}
		return ipAddress;	
	}

	public Webflow insertIp(Webflow Webflow) {
		return webDAO.insertIp(Webflow);		
	}
	
	public Webflow checkIpByIpNDate(String ip, String date) {
		return webDAO.queryByIpNDate(ip,date);		
	}
	
	public Webflow updateTimes(Webflow webflow) {
		return webDAO.updateTimes(webflow); 
	}
	public List<Webflow> IpnoRepeatDay(String date){
		return webDAO.IpnoRepeatDay(date);
	}
	public List<Webflow> IpnoRepeatWeek(String date){
		return webDAO.IpnoRepeatWeek(date);
	}
	public List<Webflow> IpRepeatDay(String date){
		return webDAO.IpRepeatDay(date);
	}
	public List<Webflow> IpRepeatWeek(String date){
		return webDAO.IpRepeatWeek(date);
	}
	
	public String processRankOrder(Integer userid) {
		return rankDao.processRankOrder(userid);
	}
	
	public void rankOrderStatus(int rtnCode,Integer userid) {
		rankDao.rankOrderStatus(rtnCode,userid);
	}
	public List<UserData> getUserWithoutAdmin(){
		return udDao.getUserWithoutAdmin();
	}

}
