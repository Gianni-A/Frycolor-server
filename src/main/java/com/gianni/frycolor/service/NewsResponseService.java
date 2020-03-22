package com.gianni.frycolor.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gianni.frycolor.entities.NewsResponse;
import com.gianni.frycolor.entities.UserComments;
import com.gianni.frycolor.model.RequestNewsResponse;
import com.gianni.frycolor.model.ResponseApi;
import com.gianni.frycolor.repository.NewsResponseDao;
import com.gianni.frycolor.repository.UserCommentsDao;
import com.gianni.frycolor.util.Utilities;

@Service
public class NewsResponseService {
	
	@Autowired
	private ResponseApi response;
	
	@Autowired
	private NewsResponseDao newsResponseRepository;
	
	@Autowired
	private UserCommentsDao userCommentsRepository;
	
	@Autowired
	private NewsResponse newsResponse;
	
	@Autowired
	private UserComments userComments;
	
	private String dateTime;
	
	public ResponseApi addResponse(RequestNewsResponse request) {
		dateTime = Utilities.getTimestamp();
		//First add the comment
		if(request.getComment().isEmpty()) return null;
		
		//Adding a comment into user_comments
		userComments.setUsId(request.getUsId());
		userComments.setUsComComment(request.getComment());
		userComments.setUsComTsCreated(dateTime);
		userComments.setUsComTsUpdated(dateTime);
		userComments = userCommentsRepository.save(userComments);
		
		//Adding the response
		newsResponse.setUsId(request.getUsId());
		newsResponse.setUsComId(userComments.getUsComId());
		newsResponse.setNwComOriginId(request.getNwComOriginId());
		newsResponse.setNwResTsCreated(dateTime);
		newsResponse.setNwResTsUpdated(dateTime);
		
		response.setCodeStatus(200);
		response.setMessage("Response added");
		response.setData(newsResponseRepository.save(newsResponse));
		
		return response;
	}

}
