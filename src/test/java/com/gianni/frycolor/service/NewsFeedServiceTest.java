package com.gianni.frycolor.service;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

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
	
	@Before
	public void setup() {
		service = new NewsFeedService();
		mockNewsFeedDao = mock(NewsFeedDao.class);
		mockUsCommentsDao = mock(UserCommentsDao.class);
		
		service.setNewsFeedDao(mockNewsFeedDao);
		service.setUserCommentsDao(mockUsCommentsDao);
		
		commentsInfo = new CommentsInfo();
	}
	
	@Test
	public void editNewsTest() {
		when(mockUsCommentsDao.getOne(anyInt())).thenReturn(commentsInfo.getUserComments());
		when(mockUsCommentsDao.save(any(UserComments.class))).thenReturn(commentsInfo.getUserComments());
		UserComments comments = service.editNews(1, "Test comment");
		Assert.assertEquals(1, comments.getUsComId());
		Assert.assertEquals(2, comments.getUsId());
		Assert.assertEquals("Test Comment", comments.getUsComComment());
	}
	
	@Ignore
	@Test
	public void deleteNewsTest() {
		NewsFeedInfo newsInfo = new NewsFeedInfo();
		when(mockNewsFeedDao.getOne(anyInt())).thenReturn(newsInfo.getNewsFeed());
		
	}

}
