package com.gianni.frycolor.information;

import com.gianni.frycolor.entities.User;
import com.gianni.frycolor.entities.UserInformation;

public class UserInfo {
	
	public User getUser() {
		User user = new User();
		user.setUsId(1);
		user.setUsEmail("test@hot.com");
		user.setUsUser("Test");
		user.setUsPassword("123456");
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
		userInfo.setUsInfCity("Farminton hills");
		userInfo.setUsInfState("Michigan");
		
		return userInfo;
	}

}
