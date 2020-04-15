package com.gamebase.member.model.service;

import com.gamebase.member.model.UserProfile;

public interface IUserProfileService {
	public UserProfile getByUserId(Integer userId);

	public void saveUserPrfile(UserProfile userProfile);

	public UserProfile updateUserProfile(UserProfile userProfile);

}
