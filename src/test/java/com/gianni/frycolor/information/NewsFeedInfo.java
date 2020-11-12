package com.gianni.frycolor.information;

import java.util.ArrayList;
import java.util.List;

import com.gianni.frycolor.entities.NewsFeed;
import com.gianni.frycolor.entities.NewsResponse;
import com.gianni.frycolor.model.Post;
import com.gianni.frycolor.model.PostModel;
import com.gianni.frycolor.model.RequestNewsResponse;
import com.gianni.frycolor.model.ResponseModelResponses;
import com.gianni.frycolor.model.ResponsePost;

public class NewsFeedInfo {
	
	private CommentsInfo commentsInfo = new CommentsInfo();
	private UserInfo userInfo = new UserInfo();
	private MediaInfo mediaInfo;
	
	public NewsFeed getNewsFeed() {
		NewsFeed news = new NewsFeed();
		mediaInfo = new MediaInfo();
		news.setNwId(1);
		news.setNwStatus(1);
		news.setUsCommentId(commentsInfo.getUserComments());
		news.setUsId(userInfo.getUser());
		news.setUsMdId(mediaInfo.getUserMedia());
		
		return news;
	}
	
	public NewsResponse getNewsResponse() {
		NewsResponse newsResponse = new NewsResponse();
		newsResponse.setNwResId(1);
		newsResponse.setUsId(userInfo.getUser());
		newsResponse.setUsComId(commentsInfo.getUserComments());
		newsResponse.setNwComOriginId(2);
		newsResponse.setNwResStatus(1);
		
		return newsResponse;
	}
	
	public RequestNewsResponse getRequestResponse() {
		RequestNewsResponse responses = new RequestNewsResponse();
		responses.setUsId(3);
		responses.setNwComOriginId(2);
		responses.setComment("Comment Test");
		
		return responses;
	}
	
	//Getting information of RequestNewsResponse without a comment
	public RequestNewsResponse getRequestResponseWithoutComment() {
		RequestNewsResponse responses = new RequestNewsResponse();
		responses.setUsId(1);
		responses.setNwComOriginId(2);
		responses.setComment("");
		
		return responses;
	}
	
	public List<PostModel> getListPostModel() {
		List<PostModel> listPost = new ArrayList<>();
		PostModel post = new PostModel(1, "CommentTest", "PathImage", 5, "ProfileImage", 3, "2020-11-11", "TestUser");
		listPost.add(post);
		
		return listPost;
	}
	
	public List<ResponsePost> getListResponsePost() {
		List<ResponsePost> listResponses = new ArrayList<>();
		ResponsePost response = new ResponsePost();
		response.setNwResId(1);
		response.setComment("Testing comment");
		response.setNameUser("Username Test");
		response.setContReactions(15);
		response.setUserLike(false);
		
		return listResponses;
	}
	
	public List<Post> getListPost() {
		List<Post> listPost = new ArrayList<>();
		listPost.add(getPost());
		
		return listPost;
	}
	
	public ResponseModelResponses getResponseModelResponses() {
		ResponseModelResponses responses = new ResponseModelResponses();
		responses.setNameUser("TestUser");
		responses.setComment("TestComment");
		responses.setNwId(20);
		responses.setNwResId(30);
		
		return responses;
	}
	
	private Post getPost() {
		Post post = new Post();
		post.setNwId(1);
		post.setComment("TestComment");
		post.setImage("PathImage");
		post.setContReactions(10);
		post.setImageProfile("PathImageProfile");
		post.setUserId(1);
		post.setNameUser("TestUser");
		post.setUserLike(true);
		post.setListResponses(getListResponsePost());
		
		return post;
	}
	
	

}
