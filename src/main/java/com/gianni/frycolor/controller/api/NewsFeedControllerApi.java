package com.gianni.frycolor.controller.api;

import java.io.IOException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.gianni.frycolor.entities.UserFriends;
import com.gianni.frycolor.model.ResponseApi;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

public interface NewsFeedControllerApi {
	
	@GetMapping("/newsfeed/{userId}/all")
	@ApiOperation(value = "Enlist all newsfeed for each user, including his friends")
	@ApiResponses(value = {
	    @ApiResponse(code = 200, message = "Successfully get all news from the user"),
	    @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
	    @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
	    @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
	})
	ResponseEntity<ResponseApi> getAllNews(
			@ApiParam(value = "userId is required to find all his news and his friends", required = true)
			@PathVariable("userId") int userId);
	
	
	@GetMapping("/newsfeed/{userId}")
	@ApiOperation(value = "Enlist all newsfeed for the user is logged")
	@ApiResponses(value = {
	    @ApiResponse(code = 200, message = "Successfully get all news from the user"),
	    @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
	    @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
	    @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
	})
	ResponseEntity<ResponseApi> getNewsPerUser(
			@ApiParam(value = "userId is required to find all his news", required = true)
			@PathVariable("userId") int userId);
	
	
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

}
