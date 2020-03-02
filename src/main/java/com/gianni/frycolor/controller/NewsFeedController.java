package com.gianni.frycolor.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.gianni.frycolor.controller.api.NewsFeedControllerApi;
import com.gianni.frycolor.model.ResponseApi;
import com.gianni.frycolor.service.NewsFeedService;

@RestController
public class NewsFeedController implements NewsFeedControllerApi {
	
	@Autowired
	private NewsFeedService service;

	@Override
	public ResponseEntity<ResponseApi> getAllNews(int userId) {
		return ResponseEntity.status(HttpStatus.OK).body(service.getAllNews(userId));
	}

	@Override
	public ResponseEntity<ResponseApi> getNewsPerUser(int userId) {
		return ResponseEntity.status(HttpStatus.OK).body(service.getNewsPerUser(userId));
	}

	@Override
	public ResponseEntity<ResponseApi> getAddNews(MultipartFile file, String comment, int userId) {
		return ResponseEntity.status(HttpStatus.OK).body(service.saveNews(file, comment, userId));
	}

	
}
