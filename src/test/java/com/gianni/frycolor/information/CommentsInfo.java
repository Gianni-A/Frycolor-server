package com.gianni.frycolor.information;

import com.gianni.frycolor.entities.UserComments;

public class CommentsInfo {
	
	public UserComments getUserComments() {
		UserComments comments = new UserComments();
		comments.setUsComId(1);
		comments.setUsId(2);
		comments.setUsComComment("Test Comment");
		
		return comments;
	}

}
