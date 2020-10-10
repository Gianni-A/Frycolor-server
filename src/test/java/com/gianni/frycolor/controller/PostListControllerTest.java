package com.gianni.frycolor.controller;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.gianni.frycolor.information.NewsFeedInfo;
import com.gianni.frycolor.service.PostListService;

public class PostListControllerTest {
	
	private PostListController controller;
	private PostListService mockService;
	
	private NewsFeedInfo newsFeedInfo;
	
	@Before
	public void setup() {
		controller = new PostListController();
		mockService = mock(PostListService.class);
		
		controller.setPostListService(mockService);
		
		newsFeedInfo = new NewsFeedInfo();
	}
	
	@Test
	public void getNewsWithFriendsTest() {
		when(mockService.getNewsWithFriends(anyInt(), anyInt())).thenReturn(newsFeedInfo.getListPost());
		ResponseEntity<?> httpResponse = controller.getNewsWithFriends(1, 10);
		Assert.assertEquals(httpResponse.getStatusCode(), HttpStatus.OK);
	}
	
	@Test
	public void getNewsJustImages() {
		when(mockService.getNewsJustImages(anyInt(), anyInt())).thenReturn(newsFeedInfo.getListPost());
		ResponseEntity<?> httpResponse = controller.getNewsJustImages(1, 10);
		Assert.assertEquals(httpResponse.getStatusCode(), HttpStatus.OK);
	}

}
