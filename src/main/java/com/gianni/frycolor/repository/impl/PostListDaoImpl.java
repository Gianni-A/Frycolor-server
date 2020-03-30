package com.gianni.frycolor.repository.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gianni.frycolor.entities.NewsFeed;
import com.gianni.frycolor.entities.UserComments;
import com.gianni.frycolor.repository.NewsFeedDao;
import com.gianni.frycolor.repository.NewsReactionDao;
import com.gianni.frycolor.repository.UserCommentsDao;
import com.gianni.frycolor.repository.UserMediaDao;

@Component
public class PostListDaoImpl {
	
	@Autowired
	private NewsFeedDao repoNewsFeed;
	
	@Autowired
	private UserCommentsDao repoComments;
	
	@Autowired
	private UserMediaDao repoMedia;
	
	@Autowired
	private NewsReactionDao repoReactionsPost;
	
	public List<NewsFeed> getNewsFeed(int userId) {
		return repoNewsFeed.getNewsFeedList(userId);
	}
	
	public UserComments getComment(int commentId) {
		return repoComments.getOne(commentId);
	}
	
	public String getPathImage(int mediaId) {
		return repoMedia.getPathImage(mediaId);
	}
}
