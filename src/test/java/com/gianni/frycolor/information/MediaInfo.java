package com.gianni.frycolor.information;

import com.gianni.frycolor.entities.UserMedia;

public class MediaInfo {
	
	private UserInfo userInfo;
	
	public UserMedia getUserMedia() {
		UserMedia media = new UserMedia();
		userInfo = new UserInfo();
		media.setUsMdId(5);
		media.setUsId(userInfo.getUser());
		media.setUsMdPath("pathTest");
		
		return media;
	}

}
