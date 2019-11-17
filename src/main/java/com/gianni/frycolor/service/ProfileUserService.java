package com.gianni.frycolor.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.gianni.frycolor.model.ResponseApi;
import com.gianni.frycolor.repository.ProfileUserDao;

public class ProfileUserService {
	
	@Autowired
	ProfileUserDao data;
	
	@Autowired
	ResponseApi response;
	
	public ResponseApi getUserInformation(int userId) {
		response.setCodeStatus(200);
		response.setMessage("User found");
		response.setData(data.findById(userId));
		return response;
	}

}
