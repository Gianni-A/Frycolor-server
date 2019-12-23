package com.gianni.frycolor.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gianni.frycolor.entities.UserFriends;
import com.gianni.frycolor.entities.UserInformation;
import com.gianni.frycolor.model.ResponseApi;
import com.gianni.frycolor.repository.FriendsDao;
import com.gianni.frycolor.repository.ProfileUserDao;

@Service
public class ProfileUserService {
	
	@Autowired
	ProfileUserDao data;
	
	@Autowired
	FriendsDao dataFriend;
	
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
		
		//We set an error if it could't find the user
		response.setCodeStatus(500);
		response.setMessage("User NOT found");
		response.setData(null);
		return response;
		
	}
	
	public ResponseApi updateUserInformation(UserInformation userInformation) {
		//Validate if the user exist in order to update their information
		if(userActiveOrExist(userInformation.getUsInfId())) {
			response.setCodeStatus(200);
			response.setMessage("User updated");
			response.setData(data.save(userInformation));	
		}
		else {
			response.setCodeStatus(404);
			response.setMessage("User not found");
			response.setData(null);
		}
		
		return response;
	}
	
	public ResponseApi getListFriends(int userId) {
		return null;
	}
	
	public ResponseApi addFriend(UserFriends userFriend) {
		int friendResponse = dataFriend.addNewFriend(userFriend.getFrdUsId(), userFriend.getFrdUsIdUf());
		if(friendResponse != 0) {
			response.setCodeStatus(200);
			response.setMessage("Friend added");
			response.setData(friendResponse);
		}
		else {
			response.setCodeStatus(500);
			response.setMessage("Error to add a friend");
			response.setData(null);	
		}
		
		return response;
	}
	
	//Check if the user is still active or exists, returns true if exist
	private boolean userActiveOrExist(int userID) {
		if(data.existUser(userID) <= 0) {
			return false;
		} else {
			return true;
		}
	}

}
