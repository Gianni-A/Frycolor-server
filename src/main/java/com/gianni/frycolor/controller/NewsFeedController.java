package com.gianni.frycolor.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.gianni.frycolor.controller.api.NewsFeedControllerApi;
import com.gianni.frycolor.entities.NewsReaction;
import com.gianni.frycolor.exception.NewsException;
import com.gianni.frycolor.service.NewsFeedService;

@RestController
public class NewsFeedController implements NewsFeedControllerApi {
	
	@Autowired
	private NewsFeedService service;

	@SuppressWarnings("rawtypes")
	@Override
	public ResponseEntity saveNews(MultipartFile file, String comment, int userId) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(service.saveNews(file, comment, userId));
		} catch (NewsException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
		
	}

	@SuppressWarnings("rawtypes")
	@Override
	public ResponseEntity editNews(int commentId, String inputComment) {
		return ResponseEntity.status(HttpStatus.OK).body(service.editNews(commentId, inputComment));
	}

	@SuppressWarnings("rawtypes")
	@Override
	public ResponseEntity deleteNews(int nwId) {
		return ResponseEntity.status(HttpStatus.OK).body(service.deleteNews(nwId));
	}

	@SuppressWarnings("rawtypes")
	@Override
	public ResponseEntity addOrRemoveReactionToPost(NewsReaction newsReaction) {
		return ResponseEntity.status(HttpStatus.OK).body(service.addOrRemoveReactionToPost(newsReaction));
	}
}
