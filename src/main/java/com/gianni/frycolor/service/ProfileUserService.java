package com.gianni.frycolor.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gianni.frycolor.entities.UserInformation;
import com.gianni.frycolor.model.ResponseApi;
import com.gianni.frycolor.repository.ProfileUserDao;

@Service
public class ProfileUserService {
	
	@Autowired
	ProfileUserDao data;
	
	@Autowired
	ResponseApi response;
	
	@Autowired
	UserInformation uInf;
	
	public ResponseApi getUserInformation(int userId) {
		uInf = data.getUserInfoById(userId);
		if(uInf != null) {
			response.setCodeStatus(200);
			response.setMessage("User found");
			response.setData(uInf);
			return response;
		}
		
		response.setCodeStatus(500);
		response.setMessage("User NOT found");
		response.setData(null);
		return response;
		
	}
	
	public ResponseApi updateUserInformation(UserInformation userInformation) {
		response.setCodeStatus(200);
		response.setMessage("User updated");
		response.setData(data.save(userInformation));
		return response;
	}

}
