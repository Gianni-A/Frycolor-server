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
import com.gianni.frycolor.information.UserInfo;
import com.gianni.frycolor.repository.SessionDao;
import com.gianni.frycolor.util.SendMail;

@PrepareForTest({SendMail.class})
@RunWith(PowerMockRunner.class)
public class SessionServiceTest {
	
	private SessionService service;
	
	@Before
	public void setup() {
		service = new SessionService();
		service.repository = mock(SessionDao.class);
	}
	
	@Test(expected = EmailException.class)
	public void sendChangePasswordEmailIncorrectTest() {
		String email = "Tested";
		service.sendChangesPassword(email);
	}
	
	@Test(expected = UserExistException.class)
	public void sendChangePasswordUserNotFoundTest() {
		String email = "test@gmail.com";
		when(service.repository.getUserIdByEmail(anyString())).thenReturn(null);
		service.sendChangesPassword(email);
	}
	
	@Test
	public void sendChangePasswordTest() throws AddressException, MessagingException, IOException {
		String email = "test@gmail.com";
		when(service.repository.getUserIdByEmail(anyString())).thenReturn(10);
		
		PowerMockito.mockStatic(SendMail.class);
		
		service.sendChangesPassword(email);
		
		PowerMockito.verifyStatic();
		SendMail.sendEmail(anyString(), anyString());
		
	}
	
	@Test
	public void changePasswordForgottenTest() {
		String userId = "MQ==";
		String password = "987654";
		UserInfo userInfo = new UserInfo();
		
		when(service.repository.getUserCredentials(anyInt())).thenReturn(userInfo.getUser());
		
		service.changePasswordForgotten(userId, password);
		
		verify(service.repository).save(any(User.class));
	}

}
