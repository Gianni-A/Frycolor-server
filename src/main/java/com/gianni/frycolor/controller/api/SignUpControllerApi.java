package com.gianni.frycolor.controller.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gianni.frycolor.entities.User;
import com.gianni.frycolor.model.ResponseApi;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@CrossOrigin("*")
@RequestMapping("/")
@Api(value = "User Management System")
public interface SignUpControllerApi {
	
	@PostMapping("/users")
	@ApiOperation(value = "Create an user")
	@ApiResponses(value = {
		    @ApiResponse(code = 200, message = "Successfully created"),
		    @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
		    @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
		    @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
		})
	ResponseEntity<ResponseApi> signUpUser(
			@ApiParam(value = "User object containing the information of a user", required = true) @RequestBody User user);
	
	
	
	@PatchMapping("/users/{userId}")
	@ApiOperation(value = "Update register status of a user")
	ResponseEntity<ResponseApi> updateUserRegister(@PathVariable("userId") int userId);

}
