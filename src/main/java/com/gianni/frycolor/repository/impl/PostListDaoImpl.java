package com.gianni.frycolor.repository.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gianni.frycolor.entities.NewsFeed;
import com.gianni.frycolor.entities.NewsResponse;
import com.gianni.frycolor.entities.UserComments;
import com.gianni.frycolor.entities.UserInformation;
import com.gianni.frycolor.repository.NewsFeedDao;
import com.gianni.frycolor.repository.NewsReactionDao;
import com.gianni.frycolor.repository.NewsResponseDao;
import com.gianni.frycolor.repository.ProfileUserDao;
import com.gianni.frycolor.repository.ResponseReactionDao;
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
	
	@Autowired
	private NewsResponseDao repoResponse;
	
	@Autowired
	private ProfileUserDao repoProfile;
	
	@Autowired
	private ResponseReactionDao repoResponseReact;
	
	public List<NewsFeed> getNewsFeed(int userId) {
		return repoNewsFeed.getNewsFeedList(userId);
	}
	
	public UserComments getComment(int commentId) {
		return repoComments.getOne(commentId);
	}
	
	public String getPathImage(int mediaId) {
		return repoMedia.getPathImage(mediaId);
	}
	
	public int getTotalReactionsByNwId(int nwId) {
		return repoReactionsPost.getCountReactionNews(nwId);
	}
	
	public List<NewsResponse> getResponsesByNwId(int nwId) {
		return repoResponse.getAllResponsesByNwId(nwId);
	}
	
	public String getCompleteName(int usId) {
		return repoProfile.getCompleteName(usId);
	}
	
	public int getTotalResponseReactions(int nwResId) {
		return repoResponseReact.getCountResReactionNews(nwResId);
	}
	
}
