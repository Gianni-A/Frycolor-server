package com.gianni.frycolor.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gianni.frycolor.entities.NewsFeed;
import com.gianni.frycolor.entities.NewsResponse;
import com.gianni.frycolor.entities.UserComments;
import com.gianni.frycolor.exception.PostListException;
import com.gianni.frycolor.model.Post;
import com.gianni.frycolor.model.ResponsePost;
import com.gianni.frycolor.repository.impl.PostListDaoImpl;

@Service
public class PostListService {
	
	@Autowired
	private PostListDaoImpl repositoryImpl;
	
	public List<Post> getNewsPerUser(int userId, int pagination) {
		List<NewsFeed> listNewsFeed = repositoryImpl.getNewsFeed(userId);
		
		if(listNewsFeed.size() <= 0) {
			throw new PostListException("No records in the DB");
		}
		
		Post post;
		ResponsePost res;
		List<Post> postList = new ArrayList<>();
		List<ResponsePost> responseList;
		Optional<UserComments> comments;
		String pathImage = "";
		int contReactions = 0;
		int contResponseReactions = 0;
		String nameUser = "";
		
		for(NewsFeed news : listNewsFeed) {
			post = new Post();
			comments = repositoryImpl.getComment(news.getUsCommentId());
			pathImage = repositoryImpl.getPathImage(news.getUsMdId());
			contReactions = repositoryImpl.getTotalReactionsByNwId(news.getNwId());
			nameUser = repositoryImpl.getCompleteName(news.getUsId());
			
			//Object for the News
			post.setNwId(news.getNwId());
			post.setComment(comments);
			post.setImage(pathImage);
			post.setContReactions(contReactions);
			post.setNameUser(nameUser);
			
			//Objects for the responses
			List<NewsResponse> listResponse = repositoryImpl.getResponsesByNwId(news.getNwId());
			responseList = new ArrayList<>();
			for(NewsResponse response : listResponse) {
				res = new ResponsePost();
				comments = repositoryImpl.getComment(response.getUsComId());
				nameUser = repositoryImpl.getCompleteName(response.getUsId());
				contResponseReactions = repositoryImpl.getTotalResponseReactions(response.getNwResId());
				
				res.setNwResId(response.getNwResId());
				res.setComment(comments);
				res.setNameUser(nameUser);
				res.setContReactions(contResponseReactions);
				responseList.add(res);
			}
			
			post.setListResponses(responseList);
			
			postList.add(post);
		}
		
		return postList;
	}
	
	public void getNewsWithFriends(int userId, int pagination) {
		
	}
	
	//Needs to test later or when it is the moment
	public List<Post> getNewsJustImages(int userId, int pagination) {
		List<NewsFeed> listNewsFeed = repositoryImpl.getNewsFeed(userId);
		
		if(listNewsFeed.size() <= 0) {
			throw new PostListException("No records in the DB");
		}
		
		Post post;
		ResponsePost res;
		List<Post> postList = new ArrayList<>();
		List<ResponsePost> responseList;
		Optional<UserComments> comments;
		String pathImage = "";
		int contReactions = 0;
		int contResponseReactions = 0;
		String nameUser = "";
		
		for(NewsFeed news : listNewsFeed) {
			pathImage = repositoryImpl.getPathImage(news.getUsMdId());
			
			if(pathImage != "") {
				post = new Post();
				comments = repositoryImpl.getComment(news.getUsCommentId());
				contReactions = repositoryImpl.getTotalReactionsByNwId(news.getNwId());
				nameUser = repositoryImpl.getCompleteName(news.getUsId());
				
				//Object for the News
				post.setNwId(news.getNwId());
				post.setComment(comments);
				post.setImage(pathImage);
				post.setContReactions(contReactions);
				post.setNameUser(nameUser);
				
				//Objects for the responses
				List<NewsResponse> listResponse = repositoryImpl.getResponsesByNwId(news.getNwId());
				responseList = new ArrayList<>();
				for(NewsResponse response : listResponse) {
					res = new ResponsePost();
					comments = repositoryImpl.getComment(response.getUsComId());
					nameUser = repositoryImpl.getCompleteName(response.getUsId());
					contResponseReactions = repositoryImpl.getTotalResponseReactions(response.getNwResId());
					
					res.setNwResId(response.getNwResId());
					res.setComment(comments);
					res.setNameUser(nameUser);
					res.setContReactions(contResponseReactions);
					responseList.add(res);
				}
				
				post.setListResponses(responseList);
				
				postList.add(post);
			}
			
		}
		
		return postList;
	}

}
