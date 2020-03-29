package com.gianni.frycolor.controller.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gianni.frycolor.model.ResponseApi;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

public interface SessionControllerApi {
	
	@PostMapping("/session/password")
	@ApiOperation(value = "Request to change the password forgotten")
	@ApiResponses(value = {
	    @ApiResponse(code = 200, message = "Successfully user IDS information added"),
	    @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
	    @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
	    @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
	})
	ResponseEntity<ResponseApi> requestChangePasswordForgotten(
			@ApiParam(value = "Email ID to identify the user wanted to change the password", required = true)
			@RequestParam("emailID") String emailID);
	
	
	@PutMapping("/session/password")
	@ApiOperation(value = "Change the password forgotten")
	@ApiResponses(value = {
	    @ApiResponse(code = 200, message = "Successfully user IDS information added"),
	    @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
	    @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
	    @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
	})
	ResponseEntity<ResponseApi> changePasswordForgotten(
			@ApiParam(value = "UserId to identify which user needs to change his password", required = true)
			@RequestParam("userId") String userId,
			@ApiParam(value = "The password text is going to be change", required = true)
			@RequestParam("newPassword") String newPassword);

}
