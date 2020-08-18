package com.gianni.frycolor.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.gianni.frycolor.entities.User;
import com.gianni.frycolor.entities.UserFriends;
import com.gianni.frycolor.entities.UserInformation;
import com.gianni.frycolor.exception.FriendsException;
import com.gianni.frycolor.exception.MediaException;
import com.gianni.frycolor.exception.UserExistException;
import com.gianni.frycolor.exception.UserValidationsException;
import com.gianni.frycolor.model.RequestChangePassword;
import com.gianni.frycolor.model.ResponseSuccessMsg;
import com.gianni.frycolor.repository.FriendsDao;
import com.gianni.frycolor.repository.ProfileUserDao;
import com.gianni.frycolor.repository.SessionDao;
import com.gianni.frycolor.util.Utilities;
import com.gianni.frycolor.util.ValidationsDao;

import static com.gianni.frycolor.util.Constantes.*;

@Service
public class ProfileUserService {
	
	@Autowired
	ProfileUserDao repository;
	
	@Autowired
	FriendsDao repFriend;
	
	@Autowired
	User user;
	
	@Autowired
	UserInformation uInf;
	
	@Autowired
	ValidationsDao utilValidations;
	
	@Autowired
	SessionDao repSession;
	
	@Autowired
	ProfileUserDao repoProfile;
	
	final public String PATH_MEDIA_IMAGE_PROFILE = "media\\profile_images\\";
	
	public UserInformation getUserInformation(int userId) {	
		uInf = repository.getUserInfoById(userId);
		if(uInf == null) {
			throw new UserExistException("User not found");
		}
		
		//Formating PathImage of the user profile
		String pathImageProfile = Utilities.getPath(PATH_MEDIA_IMAGE_PROFILE);
		uInf.setUsInfPath_image(pathImageProfile + uInf.getUsInfPath_image());
		
		return uInf;
		
	}
	
	public UserInformation updateUserInformation(UserInformation userInformation) {
		//Validate if the user exist in order to update their information
		if(!utilValidations.userActiveOrExist(userInformation.getUsInfId())) {
			throw new UserExistException("User not found");
		}
		
		userInformation.setUsInfTsUpdated(Utilities.getTimestamp());
		return repository.save(userInformation);
	}
	
	public List<UserInformation> getListFriends(int userId) {
		List<Integer> idsUser = repFriend.getIdListFriends(userId);
		List<UserInformation> infoFriends = new ArrayList<UserInformation>();
		for (int userFrdId : idsUser) {
			infoFriends.add(repFriend.getInfFriend(userFrdId));
		}
		
		if(infoFriends.size() <= 0) {
			throw new FriendsException("There is no friend listed");
		}
		
		return infoFriends;
	}
	
	public void addFriend(UserFriends userFriend) {
		String dateTime = Utilities.getTimestamp();
		userFriend.setFrdTsCreated(dateTime);
		userFriend.setFrdTsUpdated(dateTime);
		
		int friendResponse = repFriend.addNewFriend(userFriend.getFrdUsId(), 
				                                    userFriend.getFrdUsIdUf(),
				                                    userFriend.getFrdTsCreated(),
				                                    userFriend.getFrdTsUpdated());
		if(friendResponse == 0) {
			throw new FriendsException("Error to add a friend");
		}
	}
	
	public void deleteFriend(UserFriends userFriend) {
		try {
			repFriend.deleteFriend(userFriend.getFrdUsId(), userFriend.getFrdUsIdUf());
		} catch(Exception e) {
			throw new FriendsException(HUBO_ERROR+e.getMessage());
		}
	}
	
	public UserInformation addOrUpdateMediaProfile(MultipartFile pathImage, int userInfId) {
		try {
			String mediaDirectory = Utilities.getPath(PATH_MEDIA_IMAGE_PROFILE);
			File convertFile = new File(mediaDirectory + pathImage.getOriginalFilename());
			convertFile.createNewFile();
			FileOutputStream fout = new FileOutputStream(convertFile);
			fout.write(pathImage.getBytes());
			fout.close();
			
		} catch (IOException e) {
			throw new MediaException(HUBO_ERROR + e.getMessage());
		}
		
		uInf = repository.getUserInfoById(userInfId);
		uInf.setUsInfPath_image(pathImage.getOriginalFilename());
		uInf.setUsInfTsUpdated(Utilities.getTimestamp());
		
		return repository.save(uInf);
	}
	
	public User changePassword(RequestChangePassword changePasswordInfo) {

		user = repSession.getOne(changePasswordInfo.getUserId());

		//Validate password in order to able to change
		if(!user.getUsPassword().equals(changePasswordInfo.getActualPassword())) {
			throw new UserValidationsException("The password does't match with the actual one");
		}
		
		user.setUsPassword(changePasswordInfo.getNewPassword());
		user.setUsTsUpdated(Utilities.getTimestamp());
		
		return repSession.save(user);
	}

}
