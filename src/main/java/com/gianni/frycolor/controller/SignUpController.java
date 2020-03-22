package com.gianni.frycolor.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.gianni.frycolor.controller.api.SignUpControllerApi;
import com.gianni.frycolor.entities.User;
import com.gianni.frycolor.model.ResponseApi;
import com.gianni.frycolor.service.SignUpService;

@RestController
public class SignUpController implements SignUpControllerApi {
	
	@Autowired
	SignUpService signUpService;
	
	
	@Override
	public ResponseEntity<ResponseApi> signUpUser(User user) {
		return ResponseEntity.status(HttpStatus.OK).body(signUpService.signUpUser(user));
	}

	@Override
	public ResponseEntity<ResponseApi> updateUserRegister(int userId) {
		return ResponseEntity.status(HttpStatus.OK).body(signUpService.setStatusRegisterUser(userId));
	}

}
