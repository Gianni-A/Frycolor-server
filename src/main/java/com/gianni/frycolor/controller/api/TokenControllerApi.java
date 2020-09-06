package com.gianni.frycolor.controller.api;

import static com.gianni.frycolor.util.Constantes.SERVER_URL;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gianni.frycolor.model.AuthenticationRequest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@CrossOrigin(SERVER_URL)
@RequestMapping("/")
@Api(value = "Authentication token JWT")
public interface TokenControllerApi {
	
	@SuppressWarnings("rawtypes")
	@PostMapping("/authenticate")
	@ApiOperation(value = "Authenticate and get a token to use endpoints")
	@ApiResponses(value = {
	    @ApiResponse(code = 200, message = "Successfully get token"),
	    @ApiResponse(code = 500, message = "The username or password is wrong")
	})
	ResponseEntity createAuthenticationToken(
			@ApiParam(value = "Payload that contains user and password to authenticate", required = true)
			@RequestBody AuthenticationRequest authenticationRequest);

}
