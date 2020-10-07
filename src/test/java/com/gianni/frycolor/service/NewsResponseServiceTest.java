package com.gianni.frycolor.service;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.gianni.frycolor.entities.NewsResponse;
import com.gianni.frycolor.entities.UserComments;
import com.gianni.frycolor.exception.NewsResponseException;
import com.gianni.frycolor.information.CommentsInfo;
import com.gianni.frycolor.information.NewsFeedInfo;
import com.gianni.frycolor.model.RequestNewsResponse;
import com.gianni.frycolor.model.ResponseModelResponses;
import com.gianni.frycolor.repository.impl.NewsResponseDaoImpl;

public class NewsResponseServiceTest {
	
	private NewsResponseService service;
	private NewsResponseDaoImpl mockNewsResponseDaoImpl;
	
	private NewsFeedInfo newsFeedInfo;
	private CommentsInfo commentsInfo;
	
	@Before
	public void setup() {
		service = new NewsResponseService();
		mockNewsResponseDaoImpl = mock(NewsResponseDaoImpl.class);
		service.setNewsResponseDaoImp(mockNewsResponseDaoImpl);
		
		newsFeedInfo = new NewsFeedInfo();
		commentsInfo = new CommentsInfo();
	}
	
	@Test(expected = NewsResponseException.class)
	public void addResponseException() {
		service.addResponse(newsFeedInfo.getRequestResponseWithoutComment());
	}
	
	@Test
	public void addResponseTest() {
		when(mockNewsResponseDaoImpl.userStatusActive(anyInt())).thenReturn(true);
		when(mockNewsResponseDaoImpl.postExistsOrActive(anyInt())).thenReturn(true);
		when(mockNewsResponseDaoImpl.addResponse(any(RequestNewsResponse.class))).thenReturn(newsFeedInfo.getNewsResponse());
		ResponseModelResponses response = service.addResponse(newsFeedInfo.getRequestResponse());
		Assert.assertEquals("Test Comment", response.getComment());
		Assert.assertEquals(1, response.getNwResId());
		Assert.assertEquals(2, response.getNwId());
		Assert.assertEquals("testName testLastName", response.getNameUser());
	}
	
	@Test(expected = NewsResponseException.class)
	public void editResponseException() {
		when(mockNewsResponseDaoImpl.getResponseById(anyInt())).thenReturn(newsFeedInfo.getNewsResponse());
		service.editResponse(1, "Comment dev");
	}
	
	@Test
	public void editResponseTest() {
		when(mockNewsResponseDaoImpl.getResponseById(anyInt())).thenReturn(newsFeedInfo.getNewsResponse());
		when(mockNewsResponseDaoImpl.userStatusActive(anyInt())).thenReturn(true);
		when(mockNewsResponseDaoImpl.postExistsOrActive(anyInt())).thenReturn(true);
		when(mockNewsResponseDaoImpl.responseExistsOrActive(anyInt())).thenReturn(true);
		when(mockNewsResponseDaoImpl.getCommentById(anyInt())).thenReturn(commentsInfo.getUserComments());
		service.editResponse(1, "Comment dev");
		verify(mockNewsResponseDaoImpl).editComment(any(UserComments.class));
		verify(mockNewsResponseDaoImpl).updateResponse(any(NewsResponse.class));
	}
	
	@Test
	public void deleteResponseTest() {
		when(mockNewsResponseDaoImpl.getResponseById(anyInt())).thenReturn(newsFeedInfo.getNewsResponse());
		service.deleteResponse(1);
		verify(mockNewsResponseDaoImpl).deleteResponse(any(NewsResponse.class));
	}
	
	@Test(expected = NewsResponseException.class)
	public void addOrRemoveReactionException() {
		service.addOrRemoveReaction(0, 0);
	}
	
	@Test
	public void addReactionTest() {
		when(mockNewsResponseDaoImpl.userStatusActive(anyInt())).thenReturn(true);
		when(mockNewsResponseDaoImpl.responseExistsOrActive(anyInt())).thenReturn(true);
		when(mockNewsResponseDaoImpl.getResponseReaction(anyInt(), anyInt())).thenReturn(null);
		
		service.addOrRemoveReaction(1, 1);
		
		verify(mockNewsResponseDaoImpl).addReactionResponse(anyInt(), anyInt());
	}
	
	@Test
	public void removeReactionTest() {
		when(mockNewsResponseDaoImpl.userStatusActive(anyInt())).thenReturn(true);
		when(mockNewsResponseDaoImpl.responseExistsOrActive(anyInt())).thenReturn(true);
		when(mockNewsResponseDaoImpl.getResponseReaction(anyInt(), anyInt())).thenReturn(1);
		
		service.addOrRemoveReaction(1, 1);
		
		verify(mockNewsResponseDaoImpl).deleteReactionResponse(anyInt());
	}

}
