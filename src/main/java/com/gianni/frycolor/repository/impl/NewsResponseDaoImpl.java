package com.gianni.frycolor.repository.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gianni.frycolor.entities.NewsResponse;
import com.gianni.frycolor.entities.UserComments;
import com.gianni.frycolor.model.RequestNewsResponse;
import com.gianni.frycolor.repository.NewsFeedDao;
import com.gianni.frycolor.repository.NewsResponseDao;
import com.gianni.frycolor.repository.UserCommentsDao;
import com.gianni.frycolor.util.Utilities;
import com.gianni.frycolor.util.ValidationsDao;

@Component
public class NewsResponseDaoImpl {
	
	@Autowired
	private NewsResponseDao repository;
	
	@Autowired
	private UserCommentsDao userCommentsRepository;
	
	@Autowired
	private NewsFeedDao newsFeedRepository;
	
	@Autowired
	private NewsResponse newsResponse;
	
	@Autowired
	private UserComments userComments;
	
	@Autowired
	private ValidationsDao validations;
	
	private String dateTime;
	
	public NewsResponse addResponse(RequestNewsResponse request) {
		dateTime = Utilities.getTimestamp();
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
		
		return repository.save(newsResponse);
	}
	
	public boolean userStatusActive(int userId) {
		return validations.userActiveOrExist(userId);
	}
	
	public boolean postExistsOrActive(int newsId) {
		if(newsFeedRepository.existsById(newsId)) {
			if(newsFeedRepository.isPostActive(newsId) <= 0) {
				return false;
			} else {
				return true;
			}
		} else {
			return false;
		}
	}
	
}
