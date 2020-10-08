package com.gianni.frycolor.service;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.gianni.frycolor.exception.PostListException;
import com.gianni.frycolor.information.NewsFeedInfo;
import com.gianni.frycolor.model.Post;
import com.gianni.frycolor.model.PostModel;
import com.gianni.frycolor.repository.impl.NewsResponseDaoImpl;
import com.gianni.frycolor.repository.impl.PostListDaoImpl;

public class PostListServiceTest {
	
	private PostListService service;
	
	private PostListDaoImpl mockPostListDao;
	private NewsResponseDaoImpl mockNewsResponseDao;
	
	private NewsFeedInfo newsFeedInfo;
	
	@Before
	public void setup() {
		service = new PostListService();
		mockPostListDao = mock(PostListDaoImpl.class);
		mockNewsResponseDao = mock(NewsResponseDaoImpl.class);
		
		service.setPostListDaoImpl(mockPostListDao);
		service.setNewsResponseDaoImpl(mockNewsResponseDao);
		
		newsFeedInfo = new NewsFeedInfo();
	}
	
	@Test(expected = PostListException.class)
	public void getNewsWithFriendsException() {
		when(mockPostListDao.getAllListPost(anyInt())).thenReturn(new ArrayList<PostModel>());
		service.getNewsWithFriends(1, 10);
	}
	
	@Test
	public void getNewsWithFriendsTest() {
		when(mockPostListDao.getAllListPost(anyInt())).thenReturn(newsFeedInfo.getListPostModel());
		when(mockPostListDao.getTotalReactionsByNwId(anyInt())).thenReturn(10);
		when(mockNewsResponseDao.getAllResponseFromPost(anyInt(), anyInt())).thenReturn(newsFeedInfo.getListResponsePost());
		List<Post> response = service.getNewsWithFriends(1, 10);
		
		Post post = response.get(0);
		
		Assert.assertEquals(1, post.getNwId());
		Assert.assertEquals("CommentTest", post.getComment());
		Assert.assertEquals(10, post.getContReactions());
		Assert.assertEquals("PathImage", post.getImage());
		Assert.assertEquals("ProfileImage", post.getImageProfile());
		Assert.assertEquals("TestUser", post.getNameUser());
		Assert.assertEquals(3, post.getUserId());
	}
	
	//I didn't do test for the another method, it is almost the same just a condition to show just images

}
