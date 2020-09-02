package com.gianni.frycolor.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gianni.frycolor.entities.NewsFeed;
import com.gianni.frycolor.entities.NewsResponse;
import com.gianni.frycolor.entities.UserComments;
import com.gianni.frycolor.exception.PostListException;
import com.gianni.frycolor.model.Post;
import com.gianni.frycolor.model.PostModel;
import com.gianni.frycolor.model.ResponsePost;
import com.gianni.frycolor.repository.impl.NewsResponseDaoImpl;
import com.gianni.frycolor.repository.impl.PostListDaoImpl;

@Service
public class PostListService {
	
	@Autowired
	private PostListDaoImpl repositoryImpl;
	
	@Autowired
	private NewsResponseDaoImpl repoResponseImpl;
	
	public List<Post> getNewsWithFriends(int userId, int pagination) {
		List<Post> listPost = new ArrayList<>();
		List<PostModel> list = repositoryImpl.getAllListPost(userId);
		list.stream().forEach(p -> {
			Post post = new Post();
			
			post.setNwId(p.getNwId());
			//Be careful on the name, if the user doesn't have a lastname, the query will get you the name null complete
			post.setNameUser(p.getNameUser());
			
			if(p.getComment() != null) post.setComment(p.getComment());
			
			if(p.getPathImage() != null) post.setImage(p.getPathImage());
			
			
			int contReactions = repositoryImpl.getTotalReactionsByNwId(p.getNwId());
			
			post.setContReactions(contReactions);
			
			List<ResponsePost> listResponses = repoResponseImpl.getAllResponseFromPost(p.getNwId());
			post.setListResponses(listResponses);
			
			listPost.add(post);
			
		});
		
		return listPost;
	}
	
	public List<Post> getNewsPerUser(int userId, int pagination) {
		List<NewsFeed> listNewsFeed = repositoryImpl.getNewsFeed(userId);
		
		if(listNewsFeed.size() <= 0) {
			throw new PostListException("No records in the DB");
		}
		
		Post post;
		ResponsePost res;
		List<Post> postList = new ArrayList<>();
		List<ResponsePost> responseList;
		UserComments comments;
		String pathImage = "";
		int contReactions = 0;
		int contResponseReactions = 0;
		String nameUser = "";
		
		for(NewsFeed news : listNewsFeed) {
			post = new Post();
			comments = repositoryImpl.getComment(news.getUsCommentId().getUsComId());
			pathImage = repositoryImpl.getPathImage(news.getUsMdId().getUsMdId());
			contReactions = repositoryImpl.getTotalReactionsByNwId(news.getNwId());
			nameUser = repositoryImpl.getCompleteName(news.getUsId().getUsId());
			
			//Object for the News
			post.setNwId(news.getNwId());
			//post.setComment(comments);
			post.setImage(pathImage);
			post.setContReactions(contReactions);
			post.setNameUser(nameUser);
			
			//Objects for the responses
			List<NewsResponse> listResponse = repositoryImpl.getResponsesByNwId(news.getNwId());
			responseList = new ArrayList<>();
			for(NewsResponse response : listResponse) {
				res = new ResponsePost();
				//comments = repositoryImpl.getComment(response.getUsComId());
				//nameUser = repositoryImpl.getCompleteName(response.getUsId());
				contResponseReactions = repositoryImpl.getTotalResponseReactions(response.getNwResId());
				
				res.setNwResId(response.getNwResId());
				//res.setComment(comments);
				res.setNameUser(nameUser);
				res.setContReactions(contResponseReactions);
				responseList.add(res);
			}
			
			post.setListResponses(responseList);
			
			postList.add(post);
		}
		
		return postList;
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
		UserComments comments;
		String pathImage = "";
		int contReactions = 0;
		int contResponseReactions = 0;
		String nameUser = "";
		
		for(NewsFeed news : listNewsFeed) {
			pathImage = repositoryImpl.getPathImage(news.getUsMdId().getUsMdId());
			
			if(pathImage != "") {
				post = new Post();
				comments = repositoryImpl.getComment(news.getUsCommentId().getUsComId());
				contReactions = repositoryImpl.getTotalReactionsByNwId(news.getNwId());
				nameUser = repositoryImpl.getCompleteName(news.getUsId().getUsId());
				
				//Object for the News
				post.setNwId(news.getNwId());
				//post.setComment(comments);
				post.setImage(pathImage);
				post.setContReactions(contReactions);
				post.setNameUser(nameUser);
				
				//Objects for the responses
				List<NewsResponse> listResponse = repositoryImpl.getResponsesByNwId(news.getNwId());
				responseList = new ArrayList<>();
				for(NewsResponse response : listResponse) {
					res = new ResponsePost();
					//comments = repositoryImpl.getComment(response.getUsComId());
					//nameUser = repositoryImpl.getCompleteName(response.getUsId());
					contResponseReactions = repositoryImpl.getTotalResponseReactions(response.getNwResId());
					
					res.setNwResId(response.getNwResId());
					//res.setComment(comments);
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
