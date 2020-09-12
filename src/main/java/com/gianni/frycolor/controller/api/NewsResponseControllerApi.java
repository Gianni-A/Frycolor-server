package com.gianni.frycolor.controller.api;

import static com.gianni.frycolor.util.Constantes.SERVER_URL;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.gianni.frycolor.entities.ResponseReaction;
import com.gianni.frycolor.model.RequestNewsResponse;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@CrossOrigin(SERVER_URL)
public interface NewsResponseControllerApi {

	@SuppressWarnings("rawtypes")
	@PostMapping("/newsresponse")
	@ApiOperation(value = "It adds a new response, for now it adds to the post")
	@ApiResponses(value = {
	    @ApiResponse(code = 200, message = "Successfully to add a new response to the post (for now)"),
	    @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
	    @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
	    @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
	})
	ResponseEntity addResponse(
			@ApiParam(value = "Payload of the object RequestNewsResponse", required = true)
			@RequestBody RequestNewsResponse request);
	
	
	@SuppressWarnings("rawtypes")
	@PatchMapping("/newsresponse/edit")
	@ApiOperation(value = "It edits an existing response")
	@ApiResponses(value = {
	    @ApiResponse(code = 200, message = "Successfully to edit an existing response to the post (for now)"),
	    @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
	    @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
	    @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
	})
	ResponseEntity editResponse(
			@ApiParam(value = "Response Id to identify the response which is going to being edited", required = true)
			@RequestParam("nwResId") int nwResId,
			@ApiParam(value = "Comment value to change it", required = true)
			@RequestParam("comment") String comment);
	
	
	@SuppressWarnings("rawtypes")
	@DeleteMapping("/newsresponse/{nwResId}/delete")
	@ApiOperation(value = "It deletes an existing response")
	@ApiResponses(value = {
	    @ApiResponse(code = 200, message = "Successfully to delete an existing response to the post (for now)"),
	    @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
	    @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
	    @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
	})
	ResponseEntity deleteResponse(
			@ApiParam(value = "Response Id to identify the response which is going to being deleted", required = true)
			@PathVariable("nwResId") int nwResId);
	
	
	@SuppressWarnings("rawtypes")
	@PostMapping("/newsresponse/reaction")
	@ApiOperation(value = "It adds or removes a reaction of a response")
	@ApiResponses(value = {
	    @ApiResponse(code = 200, message = "Successfully to delete an existing response to the post (for now)"),
	    @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
	    @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
	    @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
	})
	ResponseEntity AddOrRemoveReaction(
			@ApiParam(value = "Id of the Response from a post", required = true)
			@RequestParam("nwResId") int nwResId,
			@ApiParam(value = "userId related to the response of a post", required = true)
			@RequestParam("userId") int userId);
	
}
