package com.gianni.frycolor.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gianni.frycolor.entities.NewsFeed;
import com.gianni.frycolor.entities.UserComments;
import com.gianni.frycolor.repository.impl.PostListDaoImpl;

@Service
public class PostListService {
	
	@Autowired
	private PostListDaoImpl repositoryImpl;
	
	public void getNews(int userId, int pagination) {
		List<NewsFeed> listNewsFeed = repositoryImpl.getNewsFeed(userId);
		UserComments comments = new UserComments();
		String pathImage = "";
		int contReactions = 0;
		
		for(NewsFeed post : listNewsFeed) {
			comments = repositoryImpl.getComment(post.getUsCommentId());
			pathImage = repositoryImpl.getPathImage(post.getUsMdId());
			//Needs to get the count of reactions.
			
		}
		
	}

}
