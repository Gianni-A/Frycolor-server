package com.gianni.frycolor.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.gianni.frycolor.controller.api.PostListControllerApi;
import com.gianni.frycolor.service.PostListService;

@RestController
public class PostListController implements PostListControllerApi {
	
	@Autowired
	private PostListService service;

	@SuppressWarnings("rawtypes")
	@Override
	public ResponseEntity getNewsPerUser(int userId, int pagination) {
		service.getNews(userId, pagination);
		return new ResponseEntity(HttpStatus.OK);
	}

}
