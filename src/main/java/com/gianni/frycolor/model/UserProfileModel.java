package com.gianni.frycolor.model;

import com.gianni.frycolor.entities.UserInformation;

public class UserProfileModel {
	
	private UserInformation userInformation;
	private boolean isFriend = false;
	
	public UserInformation getUserInformation() {
		return userInformation;
	}
	public void setUserInformation(UserInformation userInformation) {
		this.userInformation = userInformation;
	}
	public boolean isFriend() {
		return isFriend;
	}
	public void setFriend(boolean isFriend) {
		this.isFriend = isFriend;
	}
}
