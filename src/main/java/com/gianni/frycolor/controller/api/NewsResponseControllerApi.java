package com.gianni.frycolor.controller.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

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
	
}