package com.gianni.frycolor.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.gianni.frycolor.entities.UserFriends;
import com.gianni.frycolor.entities.UserInformation;
import com.gianni.frycolor.model.ResponseApi;
import com.gianni.frycolor.repository.FriendsDao;
import com.gianni.frycolor.repository.ProfileUserDao;
import com.gianni.frycolor.util.Utilities;

@Service
public class ProfileUserService {
	
	@Autowired
	ProfileUserDao repository;
	
	@Autowired
	FriendsDao repFriend;
	
	@Autowired
	ResponseApi response;
	
	@Autowired
	UserInformation uInf;
	
	@Autowired
	ServletContext context;
	
	final public String PATH_MEDIA_IMAGE_PROFILE = "media\\profile_images\\";
	
	public ResponseApi getUserInformation(int userId) {	
		uInf = repository.getUserInfoById(userId);
		if(uInf != null) {
			//Formating PathImage of the user profile
			String pathImageProfile = Utilities.getPath(PATH_MEDIA_IMAGE_PROFILE);
			uInf.setUsInfPath_image(pathImageProfile + uInf.getUsInfPath_image());
			
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
			userInformation.setUsInfTsUpdated(Utilities.getTimestamp());
			
			response.setCodeStatus(200);
			response.setMessage("User updated");
			response.setData(repository.save(userInformation));	
		}
		else {
			response.setCodeStatus(404);
			response.setMessage("User not found");
			response.setData(null);
		}
		
		return response;
	}
	
	public ResponseApi getListFriends(int userId) {
		List<Integer> idsUser = repFriend.getIdListFriends(userId);
		List<UserInformation> infoFriends = new ArrayList<UserInformation>();
		for (int userFrdId : idsUser) {
			infoFriends.add(repFriend.getInfFriend(userFrdId));
		}
		
		if(infoFriends.size() > 0) {
			response.setCodeStatus(200);
			response.setMessage("List of friends");
			response.setData(infoFriends);
		}
		else {
			response.setCodeStatus(500);
			response.setMessage("There is no friends listed");
			response.setData(null);
		}
		
		return response;
	}
	
	public ResponseApi addFriend(UserFriends userFriend) {
		String dateTime = Utilities.getTimestamp();
		userFriend.setFrdTsCreated(dateTime);
		userFriend.setFrdTsUpdated(dateTime);
		
		int friendResponse = repFriend.addNewFriend(userFriend.getFrdUsId(), 
				                                    userFriend.getFrdUsIdUf(),
				                                    userFriend.getFrdTsCreated(),
				                                    userFriend.getFrdTsUpdated());
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
	
	public ResponseApi deleteFriend(UserFriends userFriend) {
		repFriend.deleteFriend(userFriend.getFrdUsId(), userFriend.getFrdUsIdUf());
		response.setCodeStatus(200);
		response.setMessage("Friend deleted");
		response.setData(null);
		
		return response;
	}
	
	public ResponseApi addOrUpdateMediaProfile(MultipartFile pathImage, int userInfId) throws IOException {
		String mediaDirectory = Utilities.getPath(PATH_MEDIA_IMAGE_PROFILE);
		File convertFile = new File(mediaDirectory + pathImage.getOriginalFilename());
		convertFile.createNewFile();
		FileOutputStream fout = new FileOutputStream(convertFile);
		fout.write(pathImage.getBytes());
		fout.close();
		
		uInf = repository.getUserInfoById(userInfId);
		uInf.setUsInfPath_image(pathImage.getOriginalFilename());
		uInf.setUsInfTsUpdated(Utilities.getTimestamp());
		
		response.setCodeStatus(200);
		response.setMessage("Image Profile updated");
		response.setData(repository.save(uInf));
		
		return response;
	}
	
	//Check if the user is still active or exists, returns true if exist
	private boolean userActiveOrExist(int userID) {
		if(repository.existUser(userID) <= 0) {
			return false;
		} else {
			return true;
		}
	}

}
