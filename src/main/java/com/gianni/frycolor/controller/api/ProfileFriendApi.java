package com.gianni.frycolor.controller.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

public interface ProfileFriendApi {
	
	@SuppressWarnings("rawtypes")
	@PostMapping("/profile/friends")
	@ApiOperation(value = "Add a friend of the user giving their userID")
	@ApiResponses(value = {
	    @ApiResponse(code = 200, message = "Successfully user IDS information added"),
	    @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
	    @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
	    @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
	})
	ResponseEntity addFriend(
			@ApiParam(value = "ID of the user who is adding a friend", required = true)
			@RequestParam("userId") int userId,
			@ApiParam(value = "ID of the friend whom is adding by the user", required = true)
			@RequestParam("friendId") int friendId);
	
	
	@SuppressWarnings("rawtypes")
	@DeleteMapping("/profile/friends")
	@ApiOperation(value = "Delete a friend of the user giving their userID and the friendId which needs to delete")
	@ApiResponses(value = {
	    @ApiResponse(code = 200, message = "Successfully user IDS information added"),
	    @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
	    @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
	    @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
	})
	ResponseEntity deleteFriend(
			@ApiParam(value = "ID of the user who is deleting a friend", required = true)
			@RequestParam("userId") int userId,
			@ApiParam(value = "ID of the friend whom is deleting by the user", required = true)
			@RequestParam("friendId") int friendId);


	@SuppressWarnings("rawtypes")
	@GetMapping("/profile/{userId}/friends")
	@ApiOperation(value = "Get friend list by userID")
	@ApiResponses(value = {
	    @ApiResponse(code = 200, message = "Successfully found"),
	    @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
	    @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
	    @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
	})
	ResponseEntity getListFriends(
			@ApiParam(value = "UserID value to find their friends", required = true)
			@PathVariable("userId") int userId);

}
