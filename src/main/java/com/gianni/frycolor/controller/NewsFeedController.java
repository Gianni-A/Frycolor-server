package com.gianni.frycolor.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.gianni.frycolor.controller.api.NewsFeedControllerApi;
import com.gianni.frycolor.entities.NewsReaction;
import com.gianni.frycolor.model.ResponseApi;
import com.gianni.frycolor.service.NewsFeedService;

@RestController
public class NewsFeedController implements NewsFeedControllerApi {
	
	@Autowired
	private NewsFeedService service;

	@Override
	public ResponseEntity<ResponseApi> saveNews(MultipartFile file, String comment, int userId) throws IOException {
		return ResponseEntity.status(HttpStatus.OK).body(service.saveNews(file, comment, userId));
	}

	@Override
	public ResponseEntity<ResponseApi> editNews(int commentId, String inputComment) {
		return ResponseEntity.status(HttpStatus.OK).body(service.editNews(commentId, inputComment));
	}

	@Override
	public ResponseEntity<ResponseApi> deleteNews(int nwId) {
		return ResponseEntity.status(HttpStatus.OK).body(service.deleteNews(nwId));
	}

	@Override
	public ResponseEntity<ResponseApi> addOrRemoveReactionToPost(NewsReaction newsReaction) {
		return ResponseEntity.status(HttpStatus.OK).body(service.addOrRemoveReactionToPost(newsReaction));
	}

	
}
