package com.gianni.frycolor.service;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.gianni.frycolor.entities.User;
import com.gianni.frycolor.entities.UserFriends;
import com.gianni.frycolor.entities.UserInformation;
import com.gianni.frycolor.exception.FriendsException;
import com.gianni.frycolor.exception.UserExistException;
import com.gianni.frycolor.information.FriendsInfo;
import com.gianni.frycolor.information.UserInfo;
import com.gianni.frycolor.repository.FriendsDao;
import com.gianni.frycolor.repository.ProfileUserDao;
import com.gianni.frycolor.repository.SessionDao;
import com.gianni.frycolor.util.ValidationsDao;

import org.junit.Assert;

public class ProfileUserServiceTest {
	
	private ProfileUserService service;
	private FriendsDao mockFriends;
	
	private UserInfo userInfo;
	private FriendsInfo friendsInfo;
	
	private final int USER_ID_TEST = 1;
	
	@Before
	public void setup() {
		service = new ProfileUserService();
		service.repository = mock(ProfileUserDao.class);
		service.repFriend = mock(FriendsDao.class);
		service.repSession = mock(SessionDao.class);
		mockFriends = service.repFriend;
		service.utilValidations = mock(ValidationsDao.class);
		userInfo = new UserInfo();
		friendsInfo = new FriendsInfo();
	}
	
	@Test(expected = UserExistException.class)
	public void getUserInformationTestException() {
		when(service.repository.getUserInfoById(anyInt())).thenReturn(null);
		service.getUserInformation(USER_ID_TEST);
	}
	
	@Test
	public void getUserInformationTest() {
		when(service.repository.getUserInfoById(anyInt())).thenReturn(userInfo.getUserInformation());
		
		UserInformation user = service.getUserInformation(1);
		
		Assert.assertEquals(1, user.getUsInfId());
		Assert.assertEquals("testName", user.getUsInfName());
		Assert.assertEquals("testLastName", user.getUsInfLastname());
		Assert.assertEquals("1995-05-24", user.getUsInfBirthday());
		Assert.assertEquals("Farmington Hills", user.getUsInfCity());
		Assert.assertEquals("Michigan", user.getUsInfState());
		
	}
	
	@Test(expected = UserExistException.class)
	public void updateUserInformationTestException() {
		when(service.utilValidations.userActiveOrExist(anyInt())).thenReturn(false);
		service.updateUserInformation(userInfo.getUserInformation());
	}
	
	@Test
	public void updateUserInformationTest() {
		when(service.utilValidations.userActiveOrExist(anyInt())).thenReturn(true);
		
		service.updateUserInformation(userInfo.getUserInformation());
		
		verify(service.repository).save(any(UserInformation.class));
	}
	
	@Test(expected = FriendsException.class)
	public void getListFriendsTestException() {
		when(service.repSession.getOne(anyInt())).thenReturn(userInfo.getUser());
		when(mockFriends.getIdListFriends(any(User.class))).thenReturn(new ArrayList<>());
		service.getListFriends(USER_ID_TEST);
	}
	
	@Test
	public void getListFriendsTest() {
		List<UserInformation> listFriends = null;
		FriendsInfo friendsInfo = new FriendsInfo();
		when(service.repFriend.getIdListFriends(any(User.class))).thenReturn(friendsInfo.getListIdsFriends());
		listFriends = service.getListFriends(USER_ID_TEST);
		Assert.assertNotNull(listFriends);
	}
	
	@Test
	public void addFriendTest() {
		when(service.repSession.getOne(anyInt())).thenReturn(userInfo.getUser());
		when(service.repSession.getOne(anyInt())).thenReturn(userInfo.getUserFriend());
		when(service.repFriend.save(any(UserFriends.class))).thenReturn(friendsInfo.getUserFriends());
		UserFriends friend = service.addFriend(1,2);
		Assert.assertEquals(1, friend.getFrdUsId().getUsId());
		Assert.assertEquals(2, friend.getFrdUsIdUf().getUsId());
	}
	
	/*@Test(expected = FriendsException.class)
	public void addFriendTestException() {
		when(mockFriends.addNewFriend(anyInt(), anyInt(), anyString(), anyString())).thenReturn(0);
		service.addFriend(friendsInfo.getUserFriends());
	}*/
	
	@Test
	public void deleteFriendTest() throws Exception {
		mockFriends.delete(friendsInfo.getUserFriends());
		service.deleteFriend(1,2);
		verify(mockFriends).delete(any(UserFriends.class));
	}
	
	//Needs to test the last method, needs to check how to test multipart files
	/*@Test
	public void addOrUpdateMediaProfileTest() {
		
	}*/
}
