package com.gianni.frycolor.controller;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.gianni.frycolor.entities.User;
import com.gianni.frycolor.information.UserInfo;
import com.gianni.frycolor.service.SignUpService;

public class SignUpControllerTest {
	
	private SignUpController controller;
	private SignUpService mockService;
	
	private UserInfo userInfo;
	
	@Before
	public void setup() {
		controller = new SignUpController();
		
		userInfo = new UserInfo();
		mockService = mock(SignUpService.class);
		controller.setSignUpService(mockService);
	}
	
	@Test
	public void signUpUserTest() {
		when(mockService.signUpUser(any(User.class))).thenReturn(userInfo.getUser());
		ResponseEntity<?> httpResponse = controller.signUpUser(userInfo.getUser());
		Assert.assertEquals(httpResponse.getStatusCode(), HttpStatus.OK);
	}
	
	@Test
	public void updateUserRegisterTest() {
		controller.updateUserRegister(1);
		verify(mockService).setStatusRegisterUser(anyInt());
	}

}
