package com.gianni.frycolor.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.gianni.frycolor.controller.api.PostListControllerApi;
import com.gianni.frycolor.exception.PostListException;
import com.gianni.frycolor.service.PostListService;

@RestController
public class PostListController implements PostListControllerApi {
	
	@Autowired
	private PostListService service;
	
	@Autowired
	public void setPostListService(PostListService repository) {service = repository;}

	@Override
	public ResponseEntity<?> getNewsWithFriends(int userId, int pagination) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(service.getNewsWithFriends(userId, pagination));
		}
		catch(PostListException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}

	@Override
	public ResponseEntity<?> getNewsJustImages(int userId, int pagination) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(service.getNewsJustImages(userId, pagination));
		}
		catch(PostListException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}
}
