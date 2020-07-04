package com.gianni.frycolor.information;

import java.util.ArrayList;
import java.util.List;

import com.gianni.frycolor.entities.UserFriends;
import com.gianni.frycolor.entities.UserInformation;

public class FriendsInfo {
	
	public UserFriends getUserFriends() {
		UserFriends userFriends = new UserFriends();
		userFriends.setFrdId(1);
		userFriends.setFrdUsId(10);
		userFriends.setFrdUsIdUf(20);
		return userFriends;
	}
	
	public List<UserInformation> getListFriendsInfo() {
		UserInfo userInfo = new UserInfo();
		
		List<UserInformation> list = new ArrayList<UserInformation>();
		list.add(userInfo.getUserInformation());
		
		return list;
	}
	
	public List<Integer> getListIdsFriends() {
		List<Integer> list = new ArrayList<Integer>();
		list.add(1);
		list.add(2);
		list.add(3);
		
		return list;
	}

}
