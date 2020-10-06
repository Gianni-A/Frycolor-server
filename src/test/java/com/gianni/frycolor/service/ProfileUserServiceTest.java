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
import com.gianni.frycolor.exception.UserValidationsException;
import com.gianni.frycolor.information.FriendsInfo;
import com.gianni.frycolor.information.UserInfo;
import com.gianni.frycolor.model.RequestChangePassword;
import com.gianni.frycolor.model.UserProfileModel;
import com.gianni.frycolor.repository.FriendsDao;
import com.gianni.frycolor.repository.ProfileUserDao;
import com.gianni.frycolor.repository.SessionDao;
import com.gianni.frycolor.util.ValidationsDao;

import org.junit.Assert;

public class ProfileUserServiceTest {
	
	private ProfileUserService service;
	private ProfileUserDao mockProfile;
	private FriendsDao mockFriends;
	private SessionDao mockSession;
	private ValidationsDao mockValidations;
	
	private UserInfo userInfo;
	private FriendsInfo friendsInfo;
	
	private final int USER_ID_TEST = 1;
	private final int USER_LOGGED_ID_TEST = 2;
	
	@Before
	public void setup() {
		service = new ProfileUserService();
		
		service.repository = mock(ProfileUserDao.class);
		service.repFriend = mock(FriendsDao.class);
		service.repSession = mock(SessionDao.class);
		service.utilValidations = mock(ValidationsDao.class);
		
		mockProfile = service.repository;
		mockFriends = service.repFriend;
		mockSession = service.repSession;
		mockValidations = service.utilValidations;
		
		userInfo = new UserInfo();
		friendsInfo = new FriendsInfo();
	}
	
	@Test(expected = UserExistException.class)
	public void getUserInformationTestException() {
		when(mockProfile.getUserInfoById(anyInt())).thenReturn(null);
		service.getUserInformation(USER_ID_TEST, USER_LOGGED_ID_TEST);
	}
	
	@Test
	public void getUserInformationTest() {
		when(mockProfile.getUserInfoById(anyInt())).thenReturn(userInfo.getUserInformation());
		when(mockSession.getOne(anyInt())).thenReturn(userInfo.getUserLogged());
		when(mockFriends.isFriendWithUserLogged(any(User.class), any(User.class))).thenReturn(friendsInfo.getUserFriends());
		
		UserProfileModel user = service.getUserInformation(USER_ID_TEST, USER_LOGGED_ID_TEST);
		Assert.assertEquals(1, user.getFriendRequestId());
		Assert.assertEquals("RESPONSE", user.getStatusFriend());
		
		
		Assert.assertEquals(1, user.getUserInformation().getUsInfId());
		Assert.assertEquals("testName", user.getUserInformation().getUsInfName());
		Assert.assertEquals("testLastName", user.getUserInformation().getUsInfLastname());
		Assert.assertEquals("1995-05-24", user.getUserInformation().getUsInfBirthday());
		Assert.assertEquals("Farmington Hills", user.getUserInformation().getUsInfCity());
		Assert.assertEquals("Michigan", user.getUserInformation().getUsInfState());
	}
	
	@Test(expected = UserExistException.class)
	public void updateUserInformationTestException() {
		when(mockValidations.userActiveOrExist(anyInt())).thenReturn(false);
		service.updateUserInformation(userInfo.getUserInformation());
	}
	
	@Test
	public void updateUserInformationTest() {
		when(mockValidations.userActiveOrExist(anyInt())).thenReturn(true);
		
		service.updateUserInformation(userInfo.getUserInformation());
		
		verify(mockProfile).save(any(UserInformation.class));
	}
	
	@Test
	public void getListFriendsTest() {
		List<UserInformation> listFriends = null;
		FriendsInfo friendsInfo = new FriendsInfo();
		when(mockFriends.getIdListFriends(any(User.class))).thenReturn(friendsInfo.getListIdsFriends());
		listFriends = service.getListFriends(USER_ID_TEST);
		Assert.assertNotNull(listFriends);
	}
	
	@Test
	public void getListFriendRequestTest() {
		when(mockSession.getOne(anyInt())).thenReturn(userInfo.getUser());
		when(mockFriends.getListFriendRequest(any(User.class))).thenReturn(friendsInfo.getListIdsFriends());
		List<UserFriends> listFriends = service.getListFriendRequest(USER_LOGGED_ID_TEST);

		Assert.assertNotEquals(0, listFriends.size());
	}
	
	@Test(expected = FriendsException.class)
	public void getListFriendRequestException() {
		when(mockSession.getOne(anyInt())).thenReturn(userInfo.getUser());
		when(mockFriends.getListFriendRequest(any(User.class))).thenReturn(new ArrayList<UserFriends>());
		service.getListFriendRequest(USER_LOGGED_ID_TEST);
	}
	
	@Test
	public void friendRequestTest() {
		when(mockSession.getOne(anyInt())).thenReturn(userInfo.getUser());
		
		service.friendRequest(USER_ID_TEST, USER_LOGGED_ID_TEST);
		
		verify(mockFriends).save(any(UserFriends.class));
	}
	
	@Test
	public void approveRejectFriendApprove() {
		when(mockFriends.getOne(anyInt())).thenReturn(friendsInfo.getUserFriends());
		
		service.approveRejectFriend(USER_ID_TEST, "APPROVE");
		
		verify(mockFriends).save(any(UserFriends.class));
	}
	
	@Test
	public void approveRejectFriendReject() {
		when(mockFriends.getOne(anyInt())).thenReturn(friendsInfo.getUserFriends());
		
		service.approveRejectFriend(USER_ID_TEST, "REJECT");
		
		verify(mockFriends).delete(any(UserFriends.class));
	}
	
	@Test
	public void addFriendTest() {
		when(mockSession.getOne(anyInt())).thenReturn(userInfo.getUser());
		when(mockSession.getOne(anyInt())).thenReturn(userInfo.getUserFriend());
		when(mockFriends.save(any(UserFriends.class))).thenReturn(friendsInfo.getUserFriends());
		UserFriends friend = service.friendRequest(1,2);
		Assert.assertEquals(1, friend.getFrdUsId().getUsId());
		Assert.assertEquals(2, friend.getFrdUsIdUf().getUsId());
	}
	
	@Test
	public void deleteFriendTest() {
		when(mockSession.getOne(anyInt())).thenReturn(userInfo.getUser());
		when(mockSession.getOne(anyInt())).thenReturn(userInfo.getUserFriend());
		
		service.deleteFriend(USER_ID_TEST, USER_LOGGED_ID_TEST);
		
		verify(mockFriends).deleteFriend(any(User.class), any(User.class));
	}
	
	@Test(expected = UserValidationsException.class)
	public void changePasswordException() {
		when(mockSession.getOne(anyInt())).thenReturn(userInfo.getUser());
		
		service.changePassword(new RequestChangePassword());
		
		verify(mockSession).save(any(User.class));
	}
	
	@Test
	public void changePasswordTest() {
		when(mockSession.getOne(anyInt())).thenReturn(userInfo.getUser());
		
		service.changePassword(userInfo.getReqChangePasswordInfo());
		
		verify(mockSession).save(any(User.class));
	}
	
	//Needs to test the last method, needs to check how to test multipart files
	/*@Test
	public void addOrUpdateMediaProfileTest() {
		
	}*/
}
