package com.gianni.frycolor.information;

import com.gianni.frycolor.entities.NewsFeed;

public class NewsFeedInfo {
	
	private CommentsInfo commentsInfo;
	private UserInfo userInfo;
	private MediaInfo mediaInfo;
	
	public NewsFeed getNewsFeed() {
		NewsFeed news = new NewsFeed();
		userInfo = new UserInfo();
		commentsInfo = new CommentsInfo();
		mediaInfo = new MediaInfo();
		news.setNwId(1);
		news.setNwStatus(1);
		news.setUsCommentId(commentsInfo.getUserComments());
		news.setUsId(userInfo.getUser());
		news.setUsMdId(mediaInfo.getUserMedia());
		
		return news;
	}

}
