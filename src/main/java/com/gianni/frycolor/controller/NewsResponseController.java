package com.gianni.frycolor.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.gianni.frycolor.controller.api.NewsResponseControllerApi;
import com.gianni.frycolor.exception.NewsResponseException;
import com.gianni.frycolor.model.RequestNewsResponse;
import com.gianni.frycolor.service.NewsResponseService;

@RestController
public class NewsResponseController implements NewsResponseControllerApi {
	
	@Autowired
	private NewsResponseService service;
	
	@Autowired
	public void setNewsResponseService(NewsResponseService repository) {service = repository;}

	@Override
	public ResponseEntity<?> addResponse(RequestNewsResponse request) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(service.addResponse(request));
		} catch(NewsResponseException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}

	@Override
	public ResponseEntity<?> editResponse(int nwResId, String comment) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(service.editResponse(nwResId, comment));
		} catch (NewsResponseException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}

	@Override
	public ResponseEntity<?> deleteResponse(int nwResId) {
		service.deleteResponse(nwResId);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@Override
	public ResponseEntity<?> AddOrRemoveReaction(int nwResId, int userId) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(service.addOrRemoveReaction(nwResId, userId));
		} catch(NewsResponseException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}

}
