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
import com.gianni.frycolor.entities.NewsReaction;
import com.gianni.frycolor.entities.UserComments;
import com.gianni.frycolor.exception.NewsException;
import com.gianni.frycolor.information.CommentsInfo;
import com.gianni.frycolor.information.NewsFeedInfo;
import com.gianni.frycolor.repository.NewsFeedDao;
import com.gianni.frycolor.repository.NewsReactionDao;
import com.gianni.frycolor.repository.UserCommentsDao;

public class NewsFeedServiceTest {
	
	private NewsFeedService service;
	
	private NewsFeedDao mockNewsFeedDao;
    private NewsReactionDao mockNewsReactionDao;
	private UserCommentsDao mockUsCommentsDao;
	
	private CommentsInfo commentsInfo;
	private NewsFeedInfo newsFeedInfo;
	
	private final int USER_ID = 1;
	private final int NW_ID = 2;
	
	@Before
	public void setup() {
		service = new NewsFeedService();
		mockNewsFeedDao = mock(NewsFeedDao.class);
		mockUsCommentsDao = mock(UserCommentsDao.class);
		mockNewsReactionDao = mock(NewsReactionDao.class);
		
		service.setNewsFeedDao(mockNewsFeedDao);
		service.setUserCommentsDao(mockUsCommentsDao);
		service.seNewsReactionDao(mockNewsReactionDao);
		
		commentsInfo = new CommentsInfo();
		newsFeedInfo = new NewsFeedInfo();
	}
	
	/* Needs to test this method, needs to check how to test multipart files
	@Test
	public void saveNewsTest() {
		
	}*/
	
	@Test(expected = NewsException.class)
	public void editNewsException() {
		service.editNews(1, "");
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
	
	@Test
	public void addReactionToPostTest() {
		when(mockNewsReactionDao.getUsersReaction(anyInt(), anyInt())).thenReturn(null);
		service.addOrRemoveReactionToPost(USER_ID, NW_ID);
		verify(mockNewsReactionDao).save(any(NewsReaction.class));
	}
	
	@Test
	public void removeReactionToPostTest() {
		when(mockNewsReactionDao.getUsersReaction(anyInt(), anyInt())).thenReturn(10);
		service.addOrRemoveReactionToPost(USER_ID, NW_ID);
		verify(mockNewsReactionDao).deleteById(anyInt());
	}

}
