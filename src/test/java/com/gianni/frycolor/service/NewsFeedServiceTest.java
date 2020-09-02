package com.gianni.frycolor.service;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.gianni.frycolor.entities.NewsFeed;
import com.gianni.frycolor.entities.UserComments;
import com.gianni.frycolor.information.CommentsInfo;
import com.gianni.frycolor.information.NewsFeedInfo;
import com.gianni.frycolor.repository.NewsFeedDao;
import com.gianni.frycolor.repository.UserCommentsDao;

public class NewsFeedServiceTest {
	
	private NewsFeedService service;
	private NewsFeedDao mockNewsFeedDao;
	private UserCommentsDao mockUsCommentsDao;
	
	private CommentsInfo commentsInfo;
	private NewsFeedInfo newsFeedInfo;
	
	@Before
	public void setup() {
		service = new NewsFeedService();
		mockNewsFeedDao = mock(NewsFeedDao.class);
		mockUsCommentsDao = mock(UserCommentsDao.class);
		
		service.setNewsFeedDao(mockNewsFeedDao);
		service.setUserCommentsDao(mockUsCommentsDao);
		
		commentsInfo = new CommentsInfo();
		newsFeedInfo = new NewsFeedInfo();
	}
	
	@Test
	public void editNewsTest() {
		when(mockNewsFeedDao.getOne(anyInt())).thenReturn(newsFeedInfo.getNewsFeed());
		when(mockUsCommentsDao.getOne(anyInt())).thenReturn(commentsInfo.getUserComments());
		when(mockUsCommentsDao.save(any(UserComments.class))).thenReturn(commentsInfo.getUserComments());
		when(mockNewsFeedDao.save(any(NewsFeed.class))).thenReturn(newsFeedInfo.getNewsFeed());
		NewsFeed nw = service.editNews(1, "Test");
		Assert.assertNotNull(nw);
	}
	

	@Test
	public void deleteNewsTest() {
		when(mockNewsFeedDao.getOne(anyInt())).thenReturn(newsFeedInfo.getNewsFeed());
		service.deleteNews(1);
		verify(mockNewsFeedDao).save(any(NewsFeed.class));
	}

}
