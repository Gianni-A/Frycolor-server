package com.gianni.frycolor.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gianni.frycolor.repository.ProfileUserDao;

@Component
public class ValidationsDao {
	
	@Autowired
	ProfileUserDao repository;
	
	//Check if the user is still active or exists, returns true if exist
	public boolean userActiveOrExist(int userID) {
		if(repository.existUser(userID) <= 0) {
			return false;
		} else {
			return true;
		}
	}

}
