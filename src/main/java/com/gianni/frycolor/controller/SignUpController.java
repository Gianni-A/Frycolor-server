package com.gianni.frycolor.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.gianni.frycolor.controller.api.SignUpControllerApi;
import com.gianni.frycolor.entities.User;
import com.gianni.frycolor.exception.UserExistException;
import com.gianni.frycolor.exception.UserValidationsException;
import com.gianni.frycolor.service.SignUpService;

import static com.gianni.frycolor.util.Constantes.HUBO_ERROR;

@RestController
public class SignUpController implements SignUpControllerApi {
	
	@Autowired
	private SignUpService signUpService;
	
	@Autowired
	public void setSignUpService(SignUpService repository) {signUpService = repository;}

	@Override
	public ResponseEntity<?> signUpUser(User user) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(signUpService.signUpUser(user));	
		}
		catch(UserValidationsException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
		catch(UserExistException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(HUBO_ERROR + e.getMessage());
		}
	}

	@Override
	public ResponseEntity<?> updateUserRegister(int userId) {
		try {
			signUpService.setStatusRegisterUser(userId);
		} catch(UserValidationsException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
		
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

}
