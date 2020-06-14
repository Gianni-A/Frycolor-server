package com.gianni.frycolor.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.gianni.frycolor.controller.api.TokenControllerApi;
import com.gianni.frycolor.exception.TokenException;
import com.gianni.frycolor.model.AuthenticationRequest;
import com.gianni.frycolor.model.AuthenticationResponse;
import com.gianni.frycolor.service.TokenService;

@RestController
public class TokenController implements TokenControllerApi {
	
	@Autowired
	private TokenService service;

	@Override
	public ResponseEntity<?> createAuthenticationToken(AuthenticationRequest authenticationRequest) {
		
		try {
			return ResponseEntity.status(HttpStatus.OK).body(new AuthenticationResponse(service.createToken(authenticationRequest)));
		} catch (TokenException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
		
	}

}
