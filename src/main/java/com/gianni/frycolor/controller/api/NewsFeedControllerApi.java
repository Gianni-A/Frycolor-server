package com.gianni.frycolor.controller.api;

import java.io.IOException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.gianni.frycolor.entities.NewsReaction;
import com.gianni.frycolor.model.ResponseApi;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

public interface NewsFeedControllerApi {
		
	//Add a post
	@PostMapping("/newsfeed/newpost")
	@ApiOperation(value = "Enlist all newsfeed for the user is logged")
	@ApiResponses(value = {
	    @ApiResponse(code = 200, message = "Successfully get all news from the user"),
	    @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
	    @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
	    @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
	})
	ResponseEntity<ResponseApi> saveNews(
			@ApiParam(value = "File (Image) of the user profile", required = false)
			@RequestParam("file") MultipartFile file,
			@ApiParam(value = "comment field is not require, the user can only upload an image or a comment", required = false)
			@RequestParam("comment") String comment,
			@ApiParam(value = "userId is required to insert a new post", required = true)
			@RequestParam("userId") int userId) throws IOException;
	
	
	//Edit a post
	@PatchMapping("/newsfeed/{commentId}/edit")
	@ApiOperation(value = "User can edit a post, in this case they can edit just for comments")
	@ApiResponses(value = {
	    @ApiResponse(code = 200, message = "Successfully update a post (Comment)"),
	    @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
	    @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
	    @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
	})
	ResponseEntity<ResponseApi> editNews(
			@ApiParam(value = "Comment Id, which is the comment where is the post that the user selected to edit", required = true)
			@PathVariable("commentId") int commentId,
			@ApiParam(value = "The comment value where the user want to edit the post", required = true)
			@RequestParam("inputComment") String inputComment);
	
	
	//Delete a post
	@PatchMapping("/newsfeed/{nwId}/delete")
	@ApiOperation(value = "A service where is going to delete a post of a user")
	@ApiResponses(value = {
	    @ApiResponse(code = 200, message = "Successfully deleted a post"),
	    @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
	    @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
	    @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
	})
	ResponseEntity<ResponseApi> deleteNews(
			@ApiParam(value = "Post Id, where is used to change the status of the post", required = true)
			@PathVariable("nwId") int nwId);
	
	
	//Add new reaction to a post
	@PostMapping("/newsfeed/reaction")
	@ApiOperation(value = "A service to add reaction to a post")
	@ApiResponses(value = {
	    @ApiResponse(code = 200, message = "Successfully added a reaction to a post"),
	    @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
	    @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
	    @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
	})
	ResponseEntity<ResponseApi> addOrRemoveReactionToPost(
			@ApiParam(value = "Payload of the newsId and the userId", required = true)
			@RequestBody NewsReaction newsReaction);

}
