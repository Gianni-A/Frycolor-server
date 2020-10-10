package com.gianni.frycolor.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.gianni.frycolor.controller.api.NewsFeedControllerApi;
import com.gianni.frycolor.exception.NewsException;
import com.gianni.frycolor.service.NewsFeedService;

@RestController
public class NewsFeedController implements NewsFeedControllerApi {
	
	@Autowired
	private NewsFeedService service;
	
	@Autowired
	public void setNewsFeedService(NewsFeedService repository) {service = repository;}

	@Override
	public ResponseEntity<?> saveNews(MultipartFile file, String comment, int userId) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(service.saveNews(file, comment, userId));
		} catch (NewsException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
		
	}

	@Override
	public ResponseEntity<?> editNews(int nwId, String inputComment) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(service.editNews(nwId, inputComment));	
		} catch(NewsException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}

	@Override
	public ResponseEntity<?> deleteNews(int nwId) {
		return ResponseEntity.status(HttpStatus.OK).body(service.deleteNews(nwId));
	}

	@Override
	public ResponseEntity<?> addOrRemoveReactionToPost(int userId, int nwId) {
		return ResponseEntity.status(HttpStatus.OK).body(service.addOrRemoveReactionToPost(userId, nwId));
	}
}
