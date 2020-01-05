package com.gianni.frycolor.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.gianni.frycolor.controller.api.ProfileFriendApi;
import com.gianni.frycolor.controller.api.ProfileMediaApi;
import com.gianni.frycolor.controller.api.ProfileUserControllerApi;
import com.gianni.frycolor.entities.UserFriends;
import com.gianni.frycolor.entities.UserInformation;
import com.gianni.frycolor.model.ResponseApi;
import com.gianni.frycolor.service.ProfileUserService;

@RestController
public class ProfileUserController implements ProfileUserControllerApi, ProfileFriendApi, ProfileMediaApi {
	
	@Autowired
	ProfileUserService service;

	@Override
	public ResponseEntity<ResponseApi> getUserInformation(int userInfId) {
		return ResponseEntity.status(HttpStatus.OK).body(service.getUserInformation(userInfId));
	}

	@Override
	public ResponseEntity<ResponseApi> updateUserInformation(UserInformation userInformation) {
		return ResponseEntity.status(HttpStatus.OK).body(service.updateUserInformation(userInformation));
	}

	@Override
	public ResponseEntity<ResponseApi> getListFriends(int userId) {
		return ResponseEntity.status(HttpStatus.OK).body(service.getListFriends(userId));
	}

	@Override
	public ResponseEntity<ResponseApi> addFriend(UserFriends userFriends) {
		return ResponseEntity.status(HttpStatus.OK).body(service.addFriend(userFriends));
	}

	@Override
	public ResponseEntity<ResponseApi> deleteFriend(UserFriends userFriend) {
		return ResponseEntity.status(HttpStatus.OK).body(service.deleteFriend(userFriend));
	}

	@Override
	public ResponseEntity<ResponseApi> addOrUpdateMediaProfile(MultipartFile file, int userId) throws IOException {
		return ResponseEntity.status(HttpStatus.OK).body(service.addOrUpdateMediaProfile(file, userId));
	}

}
