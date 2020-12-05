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
import com.gianni.frycolor.model.UserProfileModel;
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
	
	final private String PATH_MEDIA_IMAGE_PROFILE = "media\\profiles\\";
	final private String REQUEST_FRIEND_ACTION = "APPROVE";
	
	final private String STATUS_FRIEND_ACTIVE = "ACTIVE";
	final private String STATUS_FRIEND_PEND = "PEND";
	final private String STATUS_FRIEND_UNKNOWN = "UNKNOWN";
	final private String STATUS_FRIEND_RESPONSE = "RESPONSE";
	
	public UserProfileModel getUserInformation(int userId, int userIdLogged) {
		UserProfileModel userModel = new UserProfileModel();
		
		uInf = repository.getUserInfoById(userId);
		if(uInf == null) {
			throw new UserExistException("User not found");
		}
		
		uInf.setUsInfPath_image(uInf.getUsInfPath_image());
		userModel.setUserInformation(uInf);
		
		//Validate if the user that is being getting information is a friends of the user that is logged
		if(userId != userIdLogged) {
			User user = repSession.getOne(userId);
			User uLogged = repSession.getOne(userIdLogged);
			
			UserFriends friends = repFriend.isFriendWithUserLogged(user, uLogged);
			
			if(friends != null) {
				if(friends.getFrdUsId().getUsId() == userId && friends.getFrdStatus() == 0) {
					userModel.setStatusFriend(STATUS_FRIEND_RESPONSE);
				}
				else {
					userModel.setStatusFriend(friends.getFrdStatus() != 0 ? STATUS_FRIEND_ACTIVE : STATUS_FRIEND_PEND);					
				}
				
				//Set UserFriend table ID
				userModel.setFriendRequestId(friends.getFrdId());
			}
			else {
				userModel.setStatusFriend(STATUS_FRIEND_UNKNOWN);
			}
		}
		
		return userModel;
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
		User user = repSession.getOne(userId);
		
		List<UserFriends> listFriends = repFriend.getIdListFriends(user);
		List<UserInformation> infoFriends = new ArrayList<UserInformation>();
		try {
			//See who is logged and decide which column to take to list their friends
			listFriends.stream().forEach(friend -> {
				if(friend.getFrdUsId().getUsId() == userId) {
					infoFriends.add(friend.getFrdUsIdUf().getUsInfId());
				}
				else {
					infoFriends.add(friend.getFrdUsId().getUsInfId());
				}
			});
			
		} catch(Exception e) {}
		
		return infoFriends;
	}
	
	public List<UserFriends> getListFriendRequest(int userIdLogged) {
		User userLogged = repSession.getOne(userIdLogged);
		List<UserFriends> listRequest = repFriend.getListFriendRequest(userLogged);
		
		if(listRequest.size() == 0) {
			throw new FriendsException("There is no requests of friends");
		}
		
		return listRequest;
	}
	
	public UserFriends friendRequest(int userId, int friendId) {
		try {
			String dateTime = Utilities.getTimestamp();
			UserFriends userFriend = new UserFriends();
			userFriend.setFrdTsCreated(dateTime);
			userFriend.setFrdTsUpdated(dateTime);
			
			User user = repSession.getOne(userId);
			User friend = repSession.getOne(friendId);
			
			userFriend.setFrdUsId(user);
			userFriend.setFrdUsIdUf(friend);
			userFriend.setFrdStatus(0);
			
			return repFriend.save(userFriend);
		} catch(Exception e) {
			throw new FriendsException("Error to add a friend");
		}
	}
	
	public ResponseSuccessMsg approveRejectFriend(int userFriendId, String action) {
		String dateTime = Utilities.getTimestamp();
		UserFriends userFriend = repFriend.getOne(userFriendId);
		final String json = "{\"frdId\":"+userFriend.getFrdId()+"}";
		String msg = "";
		
		if(action.equals(REQUEST_FRIEND_ACTION)) {
			userFriend.setFrdStatus(1);
			userFriend.setFrdTsUpdated(dateTime);	
			
			repFriend.save(userFriend);
			msg = "Approved";
		}
		else {
			repFriend.delete(userFriend);
			msg = "Rejected";
		}
		
		ResponseSuccessMsg message = new ResponseSuccessMsg(msg, json);
		return message;
	}
	
	public ResponseSuccessMsg deleteFriend(int userId, int friendId) {
		try {
			User user = repSession.getOne(userId);
			User friend = repSession.getOne(friendId);
			
			repFriend.deleteFriend(user, friend);
			
			ResponseSuccessMsg message = new ResponseSuccessMsg("User deleted successfully");
			return message;
			
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
