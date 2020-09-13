package com.gianni.frycolor.controller.api;

import static com.gianni.frycolor.util.Constantes.SERVER_URL;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@CrossOrigin(SERVER_URL)
@RequestMapping("/")
@Api(value = "Session Management System")
public interface SessionControllerApi {
	
	@SuppressWarnings("rawtypes")
	@PostMapping("/session/login")
	@ApiOperation(value = "Request to change the password forgotten")
	@ApiResponses(value = {
	    @ApiResponse(code = 200, message = "Successfully user IDS information added"),
	    @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
	    @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
	    @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
	})
	ResponseEntity logIn(
			@ApiParam(value = "The username, credentials to identify if the user is already on the system", required = true)
			@RequestParam("username") String username,
			@ApiParam(value = "The password, credentials to identify if the user is already on the system", required = true)
			@RequestParam("password") String password);
	
	
	@SuppressWarnings("rawtypes")
	@PostMapping("/session/password")
	@ApiOperation(value = "Request to change the password forgotten")
	@ApiResponses(value = {
	    @ApiResponse(code = 200, message = "Successfully user IDS information added"),
	    @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
	    @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
	    @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
	})
	ResponseEntity requestChangePasswordForgotten(
			@ApiParam(value = "Email ID to identify the user wanted to change the password", required = true)
			@RequestParam("emailID") String emailID);
	
	
	@SuppressWarnings("rawtypes")
	@PutMapping("/session/password")
	@ApiOperation(value = "Change the password forgotten")
	@ApiResponses(value = {
	    @ApiResponse(code = 200, message = "Successfully user IDS information added"),
	    @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
	    @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
	    @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
	})
	ResponseEntity changePasswordForgotten(
			@ApiParam(value = "UserId to identify which user needs to change his password", required = true)
			@RequestParam("userId") String userId,
			@ApiParam(value = "The password text is going to be change", required = true)
			@RequestParam("newPassword") String newPassword);

}
