package com.gianni.frycolor.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import com.gianni.frycolor.controller.api.SignUpControllerApi;
import com.gianni.frycolor.entities.User;
import com.gianni.frycolor.model.ResponseApi;
import com.gianni.frycolor.service.SignUpService;

//http://localhost:8090/swagger-ui.html
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
