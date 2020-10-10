package com.gianni.frycolor.controller;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import com.gianni.frycolor.information.NewsFeedInfo;
import com.gianni.frycolor.model.ResponseSuccessMsg;
import com.gianni.frycolor.service.NewsFeedService;

public class NewsFeedControllerTest {
	
	private NewsFeedController controller;
	private NewsFeedService mockService;
	
	private NewsFeedInfo newsFeedInfo;
	
	@Before
	public void setup() {
		controller = new NewsFeedController();
		mockService = mock(NewsFeedService.class);
		controller.setNewsFeedService(mockService);
		
		newsFeedInfo = new NewsFeedInfo();
	}
	
	@Test
	@Ignore
	public void saveNewsTest() {
		when(mockService.saveNews(any(MultipartFile.class), anyString(), anyInt())).thenReturn(newsFeedInfo.getNewsFeed());
		ResponseEntity<?> httpResponse = controller.saveNews(any(MultipartFile.class), "CommentTest", 1);
		Assert.assertEquals(httpResponse.getStatusCode(), HttpStatus.OK);
	}
	
	@Test
	public void editNewsTest() {
		when(mockService.editNews(anyInt(), anyString())).thenReturn(newsFeedInfo.getNewsFeed());
		ResponseEntity<?> httpResponse = controller.editNews(10, "CommentTest");
		Assert.assertEquals(httpResponse.getStatusCode(), HttpStatus.OK);
	}

	@Test
	public void deleteNewsTest() {
		when(mockService.deleteNews(anyInt())).thenReturn(new ResponseSuccessMsg("Success Test"));
		ResponseEntity<?> httpResponse = controller.deleteNews(10);
		Assert.assertEquals(httpResponse.getStatusCode(), HttpStatus.OK);
	}
	
	@Test
	public void addOrRemoveReactionToPostTest() {
		when(mockService.addOrRemoveReactionToPost(anyInt(), anyInt())).thenReturn(new ResponseSuccessMsg("Success Test"));
		ResponseEntity<?> httpResponse = controller.addOrRemoveReactionToPost(1, 10);
		Assert.assertEquals(httpResponse.getStatusCode(), HttpStatus.OK);
	}
}
