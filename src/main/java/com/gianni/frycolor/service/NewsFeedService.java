package com.gianni.frycolor.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.gianni.frycolor.entities.NewsFeed;
import com.gianni.frycolor.model.ResponseApi;
import com.gianni.frycolor.repository.FriendsDao;
import com.gianni.frycolor.repository.NewsFeedDao;
import com.gianni.frycolor.util.Utilities;

@Service
public class NewsFeedService {
	
	@Autowired
	private NewsFeedDao newsRepository;
	
	@Autowired
	private FriendsDao friendsRepository;
	
	@Autowired
	private ResponseApi response;
	
	@Autowired
	private NewsFeed newsFeed;
	
	final public String PATH_MEDIA_IMAGE_PROFILE = "media\\post\\";
	
	
	public ResponseApi getAllNews(int userId) {
		return null;
	}
	
	
	public ResponseApi getNewsPerUser(int userId) {
		return null;
	}
	
	public ResponseApi saveNews(MultipartFile pathImage, String comment, int userId) throws IOException {
		//Add an image if there is one
		if(pathImage.getOriginalFilename() != "") {
			String mediaDirectory = Utilities.getPath(PATH_MEDIA_IMAGE_PROFILE);
			File convertFile = new File(mediaDirectory + pathImage.getOriginalFilename());
			convertFile.createNewFile();
			FileOutputStream fout = new FileOutputStream(convertFile);
			fout.write(pathImage.getBytes());
			fout.close();
		}
		
		//Add a comment if there is one
		if(!comment.equals("")) {
			
		}
		return response;
	}

}
