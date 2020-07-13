package com.gianni.frycolor.information;

import com.gianni.frycolor.entities.NewsFeed;

public class NewsFeedInfo {
	
	public NewsFeed getNewsFeed() {
		NewsFeed news = new NewsFeed();
		news.setNwId(1);
		news.setNwStatus(1);
		news.setUsCommentId(2);
		news.setUsId(3);
		news.setUsMdId(5);
		
		return news;
	}

}
