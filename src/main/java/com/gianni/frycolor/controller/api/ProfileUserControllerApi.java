package com.gianni.frycolor.controller.api;

import static com.gianni.frycolor.util.Constantes.SERVER_URL;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gianni.frycolor.entities.UserInformation;
import com.gianni.frycolor.model.RequestChangePassword;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Validated
@CrossOrigin(SERVER_URL)
@RequestMapping("/")
@Api(value = "Profile Management System")
public interface ProfileUserControllerApi {
	
	@SuppressWarnings("rawtypes")
	@GetMapping("/profile/{userId}")
	@ApiOperation(value = "Gives information of the user")
	@ApiResponses(value = {
		    @ApiResponse(code = 200, message = "Successfully found"),
		    @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
		    @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
		    @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
		})
	ResponseEntity getUserInformation(
			@ApiParam(value = "UserID value to find their information", required = true) 
			@PathVariable("userId") int userId);
	
	
	@SuppressWarnings("rawtypes")
	@PutMapping("/profile")
	@ApiOperation(value = "Update user information giving the userID")
	@ApiResponses(value = {
		    @ApiResponse(code = 200, message = "Successfully user information updated"),
		    @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
		    @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
		    @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
		})
	ResponseEntity updateUserInformation(
			@ApiParam(value = "Payload information of the user to be update", required = true)
			@Valid @RequestBody UserInformation userInformation);
	
	
	@SuppressWarnings("rawtypes")
	@PutMapping("/profile/password")
	@ApiOperation(value = "Change the password from the profile")
	@ApiResponses(value = {
	    @ApiResponse(code = 200, message = "Successfully password changed"),
	    @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
	    @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
	    @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
	})
	ResponseEntity changePassword(
			@ApiParam(value = "Payload information that contains: userId, actualPassword, newPassword", required = true)
			@RequestBody RequestChangePassword changePasswordInfo);

}
