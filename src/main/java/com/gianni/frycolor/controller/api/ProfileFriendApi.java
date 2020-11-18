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
	
	@GetMapping("/request/friend/{userIdLogged}")
	@ApiOperation(value = "Friend Request list all requests")
	@ApiResponses(value = {
	    @ApiResponse(code = 200, message = "Successfully user IDS information added"),
	    @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
	    @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
	    @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
	})
	ResponseEntity<?> listFriendRequest(
			@ApiParam(value = "The userId who is logged to the system", required = true)
			@PathVariable("userIdLogged") int userIdLogged);
	

	@PostMapping("/profile/friends/request")
	@ApiOperation(value = "Friend Request required")
	@ApiResponses(value = {
	    @ApiResponse(code = 200, message = "Successfully user IDS information added"),
	    @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
	    @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
	    @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
	})
	ResponseEntity<?> friendRequest(
			@ApiParam(value = "ID of the user who is adding a friend", required = true)
			@RequestParam("userId") int userId,
			@ApiParam(value = "ID of the friend whom is adding by the user", required = true)
			@RequestParam("friendId") int friendId);
	
	
	@PostMapping("/profile/friends")
	@ApiOperation(value = "Add a friend of the user giving their userID")
	@ApiResponses(value = {
	    @ApiResponse(code = 200, message = "Successfully user IDS information added"),
	    @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
	    @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
	    @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
	})
	ResponseEntity<?> approveRejectFriend(
			@ApiParam(value = "The Id friend from the table user_friends", required = true)
			@RequestParam("userFriendId") int userFriendId,
			@ApiParam(value = "Action: Approve or Reject", required = true)
			@RequestParam("action") String action);
	
	
	@DeleteMapping("/profile/friends")
	@ApiOperation(value = "Delete a friend of the user giving their userID and the friendId which needs to delete")
	@ApiResponses(value = {
	    @ApiResponse(code = 200, message = "Successfully user IDS information added"),
	    @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
	    @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
	    @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
	})
	ResponseEntity<?> deleteFriend(
			@ApiParam(value = "ID of the user who is deleting a friend", required = true)
			@RequestParam("userId") int userId,
			@ApiParam(value = "ID of the friend whom is deleting by the user", required = true)
			@RequestParam("friendId") int friendId);


	@GetMapping("/profile/{userId}/friends")
	@ApiOperation(value = "Get friend list by userID")
	@ApiResponses(value = {
	    @ApiResponse(code = 200, message = "Successfully found"),
	    @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
	    @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
	    @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
	})
	ResponseEntity<?> getListFriends(
			@ApiParam(value = "UserID value to find their friends", required = true)
			@PathVariable("userId") int userId);

}
