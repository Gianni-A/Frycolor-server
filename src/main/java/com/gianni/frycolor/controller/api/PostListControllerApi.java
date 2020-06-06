package com.gianni.frycolor.controller.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

public interface PostListControllerApi {
	
	@SuppressWarnings("rawtypes")
	@GetMapping("/postlist/{userId}/{pagination}")
	@ApiOperation(value = "Enlist newsfeed's user")
	@ApiResponses(value = {
	    @ApiResponse(code = 200, message = "Successfully get news from the user"),
	    @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
	    @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
	    @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
	})
	ResponseEntity getNewsPerUser(
			@ApiParam(value = "userId is required to find all his news and his friends", required = true)
			@PathVariable("userId") int userId,
			@ApiParam(value = "Pagination, the number of posts to show", required = true)
			@PathVariable("pagination") int pagination);

	
	
	@SuppressWarnings("rawtypes")
	@GetMapping("/postlist/friends/{userId}/{pagination}")
	@ApiOperation(value = "Enlist newsfeed's user, including his friends")
	@ApiResponses(value = {
	    @ApiResponse(code = 200, message = "Successfully get news from the user"),
	    @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
	    @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
	    @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
	})
	ResponseEntity getNewsWithFriends(
			@ApiParam(value = "userId is required to find all his news and his friends", required = true)
			@PathVariable("userId") int userId,
			@ApiParam(value = "Pagination, the number of posts to show", required = true)
			@PathVariable("pagination") int pagination);
}
