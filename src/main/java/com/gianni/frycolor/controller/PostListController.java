package com.gianni.frycolor.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.gianni.frycolor.controller.api.PostListControllerApi;
import com.gianni.frycolor.model.ResponseApi;
import com.gianni.frycolor.service.PostListService;

@RestController
public class PostListController implements PostListControllerApi {
	
	@Autowired
	private PostListService service;

	@Override
	public ResponseEntity<ResponseApi> getNewsPerUser(int userId, int pagination) {
		return ResponseEntity.status(HttpStatus.OK).body(service.getNews(userId, pagination));
	}

}
