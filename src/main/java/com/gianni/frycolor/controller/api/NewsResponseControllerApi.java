package com.gianni.frycolor.controller.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.gianni.frycolor.entities.NewsResponse;
import com.gianni.frycolor.model.RequestNewsResponse;
import com.gianni.frycolor.model.ResponseApi;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

public interface NewsResponseControllerApi {

	@PostMapping("/newsresponse")
	@ApiOperation(value = "It adds a new response, for now it adds to the post")
	@ApiResponses(value = {
	    @ApiResponse(code = 200, message = "Successfully to add a new response to the post (for now)"),
	    @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
	    @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
	    @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
	})
	ResponseEntity<ResponseApi> addResponse(
			@ApiParam(value = "Payload of the object RequestNewsResponse", required = true)
			@RequestBody RequestNewsResponse request);
	
	
	@PutMapping("/newsresponse/edit")
	@ApiOperation(value = "It edits an existing response")
	@ApiResponses(value = {
	    @ApiResponse(code = 200, message = "Successfully to edit an existing response to the post (for now)"),
	    @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
	    @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
	    @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
	})
	ResponseEntity<ResponseApi> editResponse(
			@ApiParam(value = "Response Id to identify the response which is going to being edited", required = true)
			@RequestParam("nwResId") int nwResId,
			@ApiParam(value = "UserId to identify the user of the response", required = true)
			@RequestParam("userId") int userId,
			@ApiParam(value = "Comment value to change it", required = true)
			@RequestParam("comment") String comment);
	
}
