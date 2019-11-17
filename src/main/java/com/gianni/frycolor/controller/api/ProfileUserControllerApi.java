package com.gianni.frycolor.controller.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gianni.frycolor.model.ResponseApi;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@CrossOrigin("*")
@RequestMapping("/")
@Api(value = "Profile Management System")
public interface ProfileUserControllerApi {
	
	@GetMapping("/profile/{userId}")
	@ApiOperation(value = "Gives information of the user")
	@ApiResponses(value = {
		    @ApiResponse(code = 200, message = "Successfully found"),
		    @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
		    @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
		    @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
		})
	ResponseEntity<ResponseApi> getUserInformation(
			@ApiParam(value = "UserID value to find their information", required = true) 
			@PathVariable("userId") 
			int userId);

}
