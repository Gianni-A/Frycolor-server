package com.gianni.frycolor.controller;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.gianni.frycolor.information.NewsFeedInfo;
import com.gianni.frycolor.model.RequestNewsResponse;
import com.gianni.frycolor.model.ResponseSuccessMsg;
import com.gianni.frycolor.service.NewsResponseService;

public class NewsResponseControllerTest {
	
	private NewsResponseController controller;
	private NewsResponseService mockService;
	
	private NewsFeedInfo newsFeedInfo;
	
	@Before
	public void setup() {
		controller = new NewsResponseController();
		mockService = mock(NewsResponseService.class);
		
		controller.setNewsResponseService(mockService);
		
		newsFeedInfo = new NewsFeedInfo();
	}
	
	@Test
	public void addResponseTest() {
		when(mockService.addResponse(any(RequestNewsResponse.class))).thenReturn(newsFeedInfo.getResponseModelResponses());
		ResponseEntity<?> httpResponse = controller.addResponse(any(RequestNewsResponse.class));
		Assert.assertEquals(httpResponse.getStatusCode(), HttpStatus.OK);
	}
	
	@Test
	public void editResponseTest() {
		when(mockService.editResponse(anyInt(), anyString())).thenReturn(newsFeedInfo.getNewsResponse());
		ResponseEntity<?> httpResponse = controller.editResponse(10, "TestComment");
		Assert.assertEquals(httpResponse.getStatusCode(), HttpStatus.OK);
	}
	
	@Test
	public void deleteResponseTest() {
		controller.deleteResponse(10);
		verify(mockService).deleteResponse(anyInt());
	}
	
	@Test
	public void AddOrRemoveReactionTest() {
		when(mockService.addOrRemoveReaction(anyInt(), anyInt())).thenReturn(new ResponseSuccessMsg("SuccessTest"));
		ResponseEntity<?> httpResponse = controller.AddOrRemoveReaction(anyInt(), anyInt());
		Assert.assertEquals(httpResponse.getStatusCode(), HttpStatus.OK);
	}

}
