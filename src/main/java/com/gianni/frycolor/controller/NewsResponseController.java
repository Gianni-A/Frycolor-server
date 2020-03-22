package com.gianni.frycolor.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.gianni.frycolor.controller.api.NewsResponseControllerApi;
import com.gianni.frycolor.entities.NewsResponse;
import com.gianni.frycolor.model.RequestNewsResponse;
import com.gianni.frycolor.model.ResponseApi;
import com.gianni.frycolor.service.NewsResponseService;

@RestController
public class NewsResponseController implements NewsResponseControllerApi {
	
	@Autowired
	private NewsResponseService service;

	@Override
	public ResponseEntity<ResponseApi> addResponse(RequestNewsResponse request) {
		return ResponseEntity.status(HttpStatus.OK).body(service.addResponse(request));
	}

}
