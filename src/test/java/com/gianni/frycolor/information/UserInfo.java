package com.gianni.frycolor.information;

import com.gianni.frycolor.entities.User;
import com.gianni.frycolor.entities.UserInformation;

public class UserInfo {
	
	public User getUserInfo() {
		User userInfo = new User();
		userInfo.setUsId(1);
		userInfo.setUsEmail("test@hot.com");
		userInfo.setUsUser("Test");
		userInfo.setUsPassword("123456");
		userInfo.setUsInfId(new UserInformation());
		userInfo.setUsStatus(1);
		
		return userInfo;
	}

}
