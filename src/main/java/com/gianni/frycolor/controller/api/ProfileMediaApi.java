package com.gianni.frycolor.controller.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

public interface ProfileMediaApi {
	
	@SuppressWarnings("rawtypes")
	@PostMapping(value = "/profile/media")
	@ApiOperation(value = "Add an image of the user profile")
	@ApiResponses(value = {
	    @ApiResponse(code = 200, message = "Successfully user IDS information added"),
	    @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
	    @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
	    @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
	})
	ResponseEntity addOrUpdateMediaProfile(
			@ApiParam(value = "File (Image) of the user profile", required = true)
			@RequestParam("file") MultipartFile file,
			@ApiParam(value = "UserId to identify what user should change his image profile", required = true)
			@RequestParam("userInfId") int userInfId);

}
