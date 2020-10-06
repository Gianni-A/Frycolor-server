package com.gianni.frycolor.service;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.gianni.frycolor.entities.User;
import com.gianni.frycolor.exception.EmailException;
import com.gianni.frycolor.exception.UserExistException;
import com.gianni.frycolor.exception.UserValidationsException;
import com.gianni.frycolor.information.UserInfo;
import com.gianni.frycolor.repository.SessionDao;
import com.gianni.frycolor.util.SendMail;

import org.junit.Assert;

@PrepareForTest({SendMail.class})
@RunWith(PowerMockRunner.class)
public class SessionServiceTest {
	
	private SessionService service;
	private SessionDao mockSession;
	
	private UserInfo userInfo;
	
	private final String USERNAME = "userTest";
	private final String PASSWORD = "passwordTest";
	
	@Before
	public void setup() {
		service = new SessionService();
		service.repository = mock(SessionDao.class);
		mockSession = service.repository;
		userInfo = new UserInfo();
	}
	
	@Test(expected = UserValidationsException.class)
	public void logInException() {
		when(mockSession.logIn(anyString(), anyString())).thenReturn(null);
		service.logIn(USERNAME, PASSWORD);
	}
	
	@Test
	public void logInTest() {
		when(mockSession.logIn(anyString(), anyString())).thenReturn(userInfo.getUser());
		User user = service.logIn(USERNAME, PASSWORD);
		Assert.assertEquals("Test", user.getUsUser());
		Assert.assertEquals("123456", user.getUsPassword());
	}
	
	@Test(expected = EmailException.class)
	public void sendChangePasswordEmailIncorrectTest() {
		String email = "Tested";
		service.sendChangesPassword(email);
	}
	
	@Test(expected = UserExistException.class)
	public void sendChangePasswordUserNotFoundTest() {
		String email = "test@gmail.com";
		when(mockSession.getUserIdByEmail(anyString())).thenReturn(null);
		service.sendChangesPassword(email);
	}
	
	@Test
	public void sendChangePasswordTest() throws AddressException, MessagingException, IOException {
		String email = "test@gmail.com";
		when(mockSession.getUserIdByEmail(anyString())).thenReturn(10);
		
		PowerMockito.mockStatic(SendMail.class);
		
		service.sendChangesPassword(email);
		
		PowerMockito.verifyStatic();
		SendMail.sendEmail(anyString(), anyString());
		
	}
	
	@Test(expected = UserExistException.class)
	public void changePasswordForgottenException() {
		String userId = "MQ==";
		String password = "987654";
		when(mockSession.getUserCredentials(anyInt())).thenReturn(null);
		service.changePasswordForgotten(userId, password);
	}
	
	@Test
	public void changePasswordForgottenTest() {
		String userId = "MQ==";
		String password = "987654";
		UserInfo userInfo = new UserInfo();
		
		when(mockSession.getUserCredentials(anyInt())).thenReturn(userInfo.getUser());
		
		service.changePasswordForgotten(userId, password);
		
		verify(mockSession).save(any(User.class));
	}

}
