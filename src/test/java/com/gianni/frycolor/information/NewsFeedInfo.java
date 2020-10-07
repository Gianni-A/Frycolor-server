package com.gianni.frycolor.information;

import com.gianni.frycolor.entities.NewsFeed;
import com.gianni.frycolor.entities.NewsResponse;
import com.gianni.frycolor.model.RequestNewsResponse;

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

}
