package com.gianni.frycolor.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.gianni.frycolor.controller.api.SessionControllerApi;
import com.gianni.frycolor.exception.EmailException;
import com.gianni.frycolor.exception.UserExistException;
import com.gianni.frycolor.service.SessionService;

@RestController
public class SessionController implements SessionControllerApi {
	
	@Autowired
	SessionService service;
	
	@SuppressWarnings("rawtypes")
	@Override
	public ResponseEntity logIn(String username, String password) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(service.logIn(username, password));
		}
		catch(UserExistException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}

	@SuppressWarnings("rawtypes")
	@Override
	public ResponseEntity requestChangePasswordForgotten(String emailID) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(service.sendChangesPassword(emailID));
		} 
		catch(UserExistException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
		catch(EmailException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}

	@SuppressWarnings("rawtypes")
	@Override
	public ResponseEntity changePasswordForgotten(String userId, String newPassword) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(service.changePasswordForgotten(userId, newPassword));
		}
		catch(UserExistException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}

}
