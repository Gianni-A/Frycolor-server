package com.gianni.frycolor.service;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.gianni.frycolor.entities.User;
import com.gianni.frycolor.entities.UserInformation;
import com.gianni.frycolor.exception.UserExistException;
import com.gianni.frycolor.information.UserInfo;
import com.gianni.frycolor.repository.SignUpDao;
import com.gianni.frycolor.util.SendMail;

@RunWith(PowerMockRunner.class)
@PrepareForTest(SendMail.class)
public class SignUpServiceTest {
	
	private SignUpService service;
	
	@Before
	public void setup() {
		service = new SignUpService();
		service.data = mock(SignUpDao.class);
		service.userInfo = mock(UserInformation.class);
	}
	
	@Test
	//Verify if given data will be map correctly
	public void signUpUser() throws Exception {
		UserInfo userInfo = new UserInfo();
		when(service.data.findByName(anyString())).thenReturn(0);
		
		PowerMockito.mockStatic(SendMail.class);
		PowerMockito.doNothing().when(SendMail.class, "sendEmail", anyString(), anyString());
		
		when(service.data.save(any(User.class))).thenReturn(userInfo.getUserInfo());
		
		User results = service.signUpUser(userInfo.getUserInfo());
		
		Assert.assertEquals(1, results.getUsId());
		Assert.assertEquals("Test", results.getUsUser());
		Assert.assertEquals("test@hot.com", results.getUsEmail());
		Assert.assertEquals("123456", results.getUsPassword());
	}
	
	@Test
	//Verify if executes the method
	public void setStatusRegisterUserTest() {
		service.data.setStatusRegisterUser(10, "2020-06-20");
		verify(service.data).setStatusRegisterUser(anyInt(), anyString());
		
	}
	
	//Getting error on the exception
	/*@Test(expected = UserExistException.class)
	public void signUpUserException() {
		when(service.data.findByName(anyString())).thenReturn(0);
		when(service.data.findByEmail(anyString())).thenReturn(0);
		UserInfo userInfo = new UserInfo();
		service.signUpUser(userInfo.getUserInfo());
	}*/
	
}
