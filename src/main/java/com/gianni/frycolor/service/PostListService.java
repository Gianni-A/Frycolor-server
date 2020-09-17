package com.gianni.frycolor.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
			post.setImageProfile(p.getImageProfile());
			//Be careful on the name, if the user doesn't have a lastname, the query will get you the name null complete
			post.setNameUser(p.getNameUser());
			
			if(p.getComment() != null) post.setComment(p.getComment());
			
			if(p.getPathImage() != null) post.setImage(p.getPathImage());
			
			int contReactions = repositoryImpl.getTotalReactionsByNwId(p.getNwId());
			
			post.setContReactions(contReactions);
			post.setUserLike( p.getUserLike() != null ? true : false );
			
			List<ResponsePost> listResponses = repoResponseImpl.getAllResponseFromPost(p.getNwId());
			post.setListResponses(listResponses);
			
			listPost.add(post);
			
		});
		
		if(listPost.size() == 0) {
			throw new PostListException("There is no post published");
		}
		
		return listPost;
	}
	
	//It is the same as above method but adding a filter to get just post with images
	public List<Post> getNewsJustImages(int userId, int pagination) {
		List<Post> listPhotos = new ArrayList<>();
		List<PostModel> list = repositoryImpl.getAllListPost(userId);
		list.stream()
			.filter(p -> p.getPathImage() != null)
			.forEach(p -> {
				Post post = new Post();
				
				post.setNwId(p.getNwId());
				post.setImageProfile(p.getImageProfile());
				//Be careful on the name, if the user doesn't have a lastname, the query will get you the name null complete
				post.setNameUser(p.getNameUser());
				
				if(p.getComment() != null) post.setComment(p.getComment());
				
				if(p.getPathImage() != null) post.setImage(p.getPathImage());
				
				int contReactions = repositoryImpl.getTotalReactionsByNwId(p.getNwId());
				
				post.setContReactions(contReactions);
				post.setUserLike( p.getUserLike() != null ? true : false );
				
				List<ResponsePost> listResponses = repoResponseImpl.getAllResponseFromPost(p.getNwId());
				post.setListResponses(listResponses);
				
				listPhotos.add(post);
				
			});
		
		if(listPhotos.size() == 0) {
			throw new PostListException("There is no photos published");
		}
		
		return listPhotos;
	}

}
