package com.gianni.frycolor.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.gianni.frycolor.entities.NewsFeed;
import com.gianni.frycolor.model.ResponseApi;
import com.gianni.frycolor.repository.FriendsDao;
import com.gianni.frycolor.repository.NewsFeedDao;

@Service
public class NewsFeedService {
	
	@Autowired
	private NewsFeedDao newsRepository;
	
	@Autowired
	private FriendsDao friendsRepository;
	
	@Autowired
	private ResponseApi response;
	
	
	public ResponseApi getAllNews(int userId) {
		List<Integer> getIdsFriends = friendsRepository.getIdListFriends(userId);
		List<NewsFeed> listNews = new ArrayList<>();
		NewsFeed news = new NewsFeed();
		
		news = newsRepository.getNewsUser(userId);
		listNews.add(news);
		
		for(int id : getIdsFriends) {
			news = newsRepository.getNewsUser(id);
			if(news != null)
				listNews.add(news);
				
		}
		
		response.setCodeStatus(200);
		response.setMessage("Successfully get news");
		response.setData(listNews);
		return response;
	}
	
	
	public ResponseApi getNewsPerUser(int userId) {
		NewsFeed news = newsRepository.getNewsUser(userId);
		
		response.setCodeStatus(200);
		response.setMessage("Successfully get news");
		response.setData(news);
		return response;
	}
	
	public ResponseApi saveNews(MultipartFile file, String comment, int userId) {
		
		return response;
	}

}
