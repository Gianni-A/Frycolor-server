package com.gianni.frycolor.controller;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.gianni.frycolor.information.UserInfo;
import com.gianni.frycolor.model.ResponseSuccessMsg;
import com.gianni.frycolor.service.SessionService;

public class SessionControllerTest {
	
	private SessionController controller;
	private SessionService mockService;
	
	private UserInfo userInfo;
	
	@Before
	public void setup() {
		controller = new SessionController();
		mockService = mock(SessionService.class);
		
		controller.setSessionService(mockService);
		
		userInfo = new UserInfo();
	}
	
	@Test
	public void logInTest() {
		when(mockService.logIn(anyString(), anyString())).thenReturn(userInfo.getUser());
		ResponseEntity<?> httpResponse = controller.logIn("TestUsername", "TestPassword");
		Assert.assertEquals(httpResponse.getStatusCode(), HttpStatus.OK);
	}
	
	@Test
	public void requestChangePasswordForgottenTest() {
		when(mockService.sendChangesPassword(anyString())).thenReturn(new ResponseSuccessMsg("TestSuccess"));
		ResponseEntity<?> httpResponse = controller.requestChangePasswordForgotten("EmailTest");
		Assert.assertEquals(httpResponse.getStatusCode(), HttpStatus.OK);
	}
	
	@Test
	public void changePasswordForgottenTest() {
		when(mockService.changePasswordForgotten(anyString(), anyString())).thenReturn(new ResponseSuccessMsg("TestSuccess"));
		ResponseEntity<?> httpResponse = controller.changePasswordForgotten("UserIdBase64", "NewPassword");
		Assert.assertEquals(httpResponse.getStatusCode(), HttpStatus.OK);
	}

}
