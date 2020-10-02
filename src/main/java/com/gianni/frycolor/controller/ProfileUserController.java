package com.gianni.frycolor.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.gianni.frycolor.controller.api.ProfileFriendApi;
import com.gianni.frycolor.controller.api.ProfileMediaApi;
import com.gianni.frycolor.controller.api.ProfileUserControllerApi;
import com.gianni.frycolor.entities.UserInformation;
import com.gianni.frycolor.exception.FriendsException;
import com.gianni.frycolor.exception.MediaException;
import com.gianni.frycolor.exception.UserExistException;
import com.gianni.frycolor.exception.UserValidationsException;
import com.gianni.frycolor.model.RequestChangePassword;
import com.gianni.frycolor.service.ProfileUserService;

@RestController
public class ProfileUserController implements ProfileUserControllerApi, ProfileFriendApi, ProfileMediaApi {
	
	@Autowired
	ProfileUserService service;

	@SuppressWarnings("rawtypes")
	@Override
	public ResponseEntity getUserInformation(int userInfId, int userIdLogged) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(service.getUserInformation(userInfId, userIdLogged));
		}
		catch (UserExistException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}

	@SuppressWarnings("rawtypes")
	@Override
	public ResponseEntity updateUserInformation(UserInformation userInformation) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(service.updateUserInformation(userInformation));
		}
		catch (UserExistException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}

	@SuppressWarnings("rawtypes")
	@Override
	public ResponseEntity getListFriends(int userId) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(service.getListFriends(userId));
		} catch(FriendsException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}
	
	@SuppressWarnings("rawtypes")
	@Override
	public ResponseEntity listFriendRequest(int userIdLogged) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(service.getListFriendRequest(userIdLogged));
		} catch(FriendsException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}

	@SuppressWarnings("rawtypes")
	@Override
	public ResponseEntity friendRequest(int userId, int friendId) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(service.friendRequest(userId, friendId));
		} catch (FriendsException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}
	
	@SuppressWarnings("rawtypes")
	@Override
	public ResponseEntity approveRejectFriend(int userFriendId, String action) {
		return ResponseEntity.status(HttpStatus.OK).body(service.approveRejectFriend(userFriendId, action));
	}

	@SuppressWarnings("rawtypes")
	@Override
	public ResponseEntity deleteFriend(int userId, int friendId) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(service.deleteFriend(userId, friendId));
		} catch(FriendsException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}

	@SuppressWarnings("rawtypes")
	@Override
	public ResponseEntity addOrUpdateMediaProfile(MultipartFile file, int userId) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(service.addOrUpdateMediaProfile(file, userId));
		} catch (MediaException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
		
	}

	@SuppressWarnings("rawtypes")
	@Override
	public ResponseEntity changePassword(RequestChangePassword changePasswordInfo) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(service.changePassword(changePasswordInfo));
		} catch(UserValidationsException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}

}
