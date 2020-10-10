package com.gianni.frycolor.information;

import java.util.ArrayList;
import java.util.List;

import com.gianni.frycolor.entities.User;
import com.gianni.frycolor.entities.UserInformation;
import com.gianni.frycolor.model.RequestChangePassword;
import com.gianni.frycolor.model.UserProfileModel;

public class UserInfo {
	
	public User getUser() {
		User user = new User();
		user.setUsId(1);
		user.setUsEmail("test@hot.com");
		user.setUsUser("Test");
		user.setUsPassword("123456");
		user.setUsInfId(getUserInformation());
		user.setUsStatus(1);
		
		return user;
	}
	
	public User getUserFriend() {
		User user = new User();
		user.setUsId(2);
		user.setUsEmail("test2@hot.com");
		user.setUsUser("Test 2");
		user.setUsPassword("987654");
		user.setUsInfId(new UserInformation());
		user.setUsStatus(1);
		
		return user;
	}
	
	public User getUserLogged() {
		User user = new User();
		user.setUsId(3);
		user.setUsEmail("test3@hot.com");
		user.setUsUser("Test 3");
		user.setUsPassword("854967");
		user.setUsInfId(new UserInformation());
		user.setUsStatus(1);
		
		return user;
	}
	
	public UserInformation getUserInformation() {
		UserInformation userInfo = new UserInformation();
		userInfo.setUsInfId(1);
		userInfo.setUsInfName("testName");
		userInfo.setUsInfLastname("testLastName");
		userInfo.setUsInfBirthday("1995-05-24");
		userInfo.setUsInfCity("Farmington Hills");
		userInfo.setUsInfState("Michigan");
		
		return userInfo;
	}
	
	public List<UserInformation> getListUserInformation() {
		List<UserInformation> listUsers = new ArrayList<>();
		listUsers.add(getUserInformation());
		return listUsers;
	}
	
	public RequestChangePassword getReqChangePasswordInfo() {
		RequestChangePassword request = new RequestChangePassword();
		request.setUserId(1);
		request.setActualPassword("123456");
		request.setNewPassword("new-password");
		
		return request;
	}
	
	public UserProfileModel getUserProfileModel() {
		UserProfileModel user = new UserProfileModel();
		user.setFriendRequestId(15);
		user.setStatusFriend("ACTIVE");
		user.setUserInformation(getUserInformation());
		
		return user;
	}

}
