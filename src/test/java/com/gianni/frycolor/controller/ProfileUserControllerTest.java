package com.gianni.frycolor.controller;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import com.gianni.frycolor.entities.UserInformation;
import com.gianni.frycolor.information.FriendsInfo;
import com.gianni.frycolor.information.UserInfo;
import com.gianni.frycolor.model.RequestChangePassword;
import com.gianni.frycolor.model.ResponseSuccessMsg;
import com.gianni.frycolor.service.ProfileUserService;

public class ProfileUserControllerTest {
	
	private ProfileUserController controller;
	private ProfileUserService mockService;
	
	private UserInfo userInfo;
	private FriendsInfo friendsInfo;
	
	@Before
	public void setup() {
		controller = new ProfileUserController();
		mockService = mock(ProfileUserService.class);
		controller.setProfileUserService(mockService);
		
		userInfo = new UserInfo();
		friendsInfo = new FriendsInfo();
	}
	
	@Test
	public void getUserInformationTest() {
		when(mockService.getUserInformation(anyInt(), anyInt())).thenReturn(userInfo.getUserProfileModel());
		ResponseEntity<?> httpResponse = controller.getUserInformation(1, 2);
		Assert.assertEquals(httpResponse.getStatusCode(), HttpStatus.OK);
	}
	
	@Test
	public void updateUserInformationTest() {
		when(mockService.updateUserInformation(any(UserInformation.class))).thenReturn(userInfo.getUserInformation());
		ResponseEntity<?> httpResponse = controller.updateUserInformation(userInfo.getUserInformation());
		Assert.assertEquals(httpResponse.getStatusCode(), HttpStatus.OK);
	}
	
	@Test
	public void getListFriendsTest() {
		when(mockService.getListFriends(anyInt())).thenReturn(userInfo.getListUserInformation());
		ResponseEntity<?> httpResponse = controller.getListFriends(1);
		Assert.assertEquals(httpResponse.getStatusCode(), HttpStatus.OK);
	}
	
	@Test
	public void listFriendRequestTest() {
		when(mockService.getListFriendRequest(anyInt())).thenReturn(friendsInfo.getListIdsFriends());
		ResponseEntity<?> httpResponse = controller.listFriendRequest(2);
		Assert.assertEquals(httpResponse.getStatusCode(), HttpStatus.OK);
	}
	
	@Test
	public void friendRequestTest() {
		when(mockService.friendRequest(anyInt(), anyInt())).thenReturn(friendsInfo.getUserFriends());
		ResponseEntity<?> httpResponse = controller.friendRequest(1, 2);
		Assert.assertEquals(httpResponse.getStatusCode(), HttpStatus.OK);
	}
	
	@Test
	public void approveRejectFriendTest() {
		when(mockService.approveRejectFriend(anyInt(), anyString())).thenReturn(new ResponseSuccessMsg("Success Test"));
		ResponseEntity<?> httpResponse = controller.approveRejectFriend(7, "APPROVE");
		Assert.assertEquals(httpResponse.getStatusCode(), HttpStatus.OK);
	}
	
	@Test
	public void deleteFriendTest() {
		when(mockService.deleteFriend(anyInt(), anyInt())).thenReturn(new ResponseSuccessMsg("Success Test"));
		ResponseEntity<?> httpResponse = controller.deleteFriend(1, 2);
		Assert.assertEquals(httpResponse.getStatusCode(), HttpStatus.OK);
	}
	
	@Test
	@Ignore
	public void addOrUpdateMediaProfileTest() {
		when(mockService.addOrUpdateMediaProfile(any(MultipartFile.class), 7));
		ResponseEntity<?> httpResponse = controller.addOrUpdateMediaProfile(any(MultipartFile.class), 1);
		Assert.assertEquals(httpResponse.getStatusCode(), HttpStatus.OK);
	}
	
	@Test
	public void changePasswordTest() {
		when(mockService.changePassword(any(RequestChangePassword.class))).thenReturn(userInfo.getUser());
		ResponseEntity<?> httpResponse = controller.changePassword(any(RequestChangePassword.class));
		Assert.assertEquals(httpResponse.getStatusCode(), HttpStatus.OK);
	}

}
