package com.gianni.frycolor.util;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import static org.mockito.Matchers.anyInt;

import com.gianni.frycolor.repository.ProfileUserDao;


public class ValidationsDaoTest {
	
	private ValidationsDao service;
	
	@Before
	public void setup() {
		service = new ValidationsDao();
		service.repository = mock(ProfileUserDao.class);
	}
	
	@Test
	public void userActiveOrExistTest() {
		when(service.repository.existUser(anyInt())).thenReturn(10);
		when(service.repository.isUserActive(anyInt())).thenReturn(5);
		Assert.assertTrue((service.userActiveOrExist(7)));
	}
	
	@Test
	public void userNotActiveOrExistTest() {
		when(service.repository.existUser(anyInt())).thenReturn(0);
		when(service.repository.isUserActive(anyInt())).thenReturn(0);
		Assert.assertFalse((service.userActiveOrExist(7)));
	}

}
