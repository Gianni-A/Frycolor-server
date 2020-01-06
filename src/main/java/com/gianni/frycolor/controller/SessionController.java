package com.gianni.frycolor.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.gianni.frycolor.controller.api.SessionControllerApi;
import com.gianni.frycolor.model.ResponseApi;
import com.gianni.frycolor.service.SessionService;

@RestController
public class SessionController implements SessionControllerApi {
	
	@Autowired
	SessionService service;

	@Override
	public ResponseEntity<ResponseApi> requestChangePasswordForgotten(String emailID) {
		return ResponseEntity.status(HttpStatus.OK).body(service.sendChangesPassword(emailID));
	}

	@Override
	public ResponseEntity<ResponseApi> changePasswordForgotten(String userId, String newPassword) {
		return ResponseEntity.status(HttpStatus.OK).body(service.changePasswordForgotten(userId, newPassword));
	}

}
