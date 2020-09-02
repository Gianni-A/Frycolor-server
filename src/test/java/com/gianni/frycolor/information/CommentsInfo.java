package com.gianni.frycolor.information;

import com.gianni.frycolor.entities.UserComments;

public class CommentsInfo {
	
	private UserInfo userInfo;
	
	public UserComments getUserComments() {
		UserComments comments = new UserComments();
		userInfo = new UserInfo();
		comments.setUsComId(1);
		comments.setUsId(userInfo.getUser());
		comments.setUsComComment("Test Comment");
		
		return comments;
	}

}
