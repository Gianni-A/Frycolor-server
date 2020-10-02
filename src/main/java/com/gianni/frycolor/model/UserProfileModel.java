package com.gianni.frycolor.model;

import com.gianni.frycolor.entities.UserInformation;

public class UserProfileModel {
	
	private UserInformation userInformation;
	private String statusFriend;
	private int friendRequestId;
	
	public UserInformation getUserInformation() {
		return userInformation;
	}
	public void setUserInformation(UserInformation userInformation) {
		this.userInformation = userInformation;
	}
	public String getStatusFriend() {
		return statusFriend;
	}
	public void setStatusFriend(String statusFriend) {
		this.statusFriend = statusFriend;
	}
	public int getFriendRequestId() {
		return friendRequestId;
	}
	public void setFriendRequestId(int friendRequestId) {
		this.friendRequestId = friendRequestId;
	}
}
