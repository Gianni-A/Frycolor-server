package com.gianni.frycolor.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.gianni.frycolor.controller.api.ProfileUserControllerApi;
import com.gianni.frycolor.model.ResponseApi;
import com.gianni.frycolor.service.ProfileUserService;

public class ProfileUserController implements ProfileUserControllerApi {
	
	@Autowired
	ProfileUserService service;

	@Override
	public ResponseEntity<ResponseApi> getUserInformation(int userInfId) {
		return ResponseEntity.status(HttpStatus.OK).body(service.getUserInformation(userInfId));
	}

}
