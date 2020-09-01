package com.gianni.frycolor.information;

import java.util.ArrayList;
import java.util.List;

import com.gianni.frycolor.entities.UserFriends;
import com.gianni.frycolor.entities.UserInformation;

public class FriendsInfo {
	
	private UserInfo userInfo;
	
	public UserFriends getUserFriends() {
		UserFriends userFriends = new UserFriends();
		userInfo = new UserInfo();
		userFriends.setFrdId(1);
		userFriends.setFrdUsId(userInfo.getUser());
		userFriends.setFrdUsIdUf(userInfo.getUserFriend());
		return userFriends;
	}
	
	public List<UserInformation> getListFriendsInfo() {
		UserInfo userInfo = new UserInfo();
		
		List<UserInformation> list = new ArrayList<UserInformation>();
		list.add(userInfo.getUserInformation());
		
		return list;
	}
	
	public List<UserFriends> getListIdsFriends() {
		List<UserFriends> list = new ArrayList<UserFriends>();
		list.add(getFriend1());
		
		return list;
	}
	
	private UserFriends getFriend1() {
		UserFriends user1 = new UserFriends();
		userInfo = new UserInfo();
		user1.setFrdId(1);
		user1.setFrdUsId(userInfo.getUser());
		user1.setFrdUsIdUf(userInfo.getUserFriend());
		return user1;
	}

}
