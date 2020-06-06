package com.gianni.frycolor.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.gianni.frycolor.entities.NewsFeed;
import com.gianni.frycolor.entities.NewsReaction;
import com.gianni.frycolor.entities.UserComments;
import com.gianni.frycolor.entities.UserMedia;
import com.gianni.frycolor.exception.NewsException;
import com.gianni.frycolor.repository.NewsFeedDao;
import com.gianni.frycolor.repository.NewsReactionDao;
import com.gianni.frycolor.repository.UserCommentsDao;
import com.gianni.frycolor.repository.UserMediaDao;
import com.gianni.frycolor.util.Utilities;

import static com.gianni.frycolor.util.Constantes.*;

@Service
public class NewsFeedService {
	
	@Autowired
	private NewsFeedDao newsRepository;
	
	@Autowired
	private UserMediaDao userMediaRepository;
	
	@Autowired
	private UserCommentsDao userCommentsRepository;
	
	@Autowired
	private NewsReactionDao nwReactionRepository;
	
	@Autowired
	private NewsFeed newsFeed;
	
	@Autowired
	private UserMedia userMedia;
	
	@Autowired
	private UserComments userComments;
	
	final public String PATH_MEDIA_IMAGE_PROFILE = "media\\post\\";
	
	String dateTime = "";
	
	public NewsFeed saveNews(MultipartFile pathImage, String input_comment, int userId) {
		dateTime = Utilities.getTimestamp();
		newsFeed.setUsMdId(0);
		newsFeed.setUsCommentId(0);
		
		//Add an image if there is one in the request
		if(!pathImage.getOriginalFilename().isEmpty()) {
			try {
				if(pathImage.getSize() > 5000000) {
					throw new NewsException("The image is too heaver, please under 5 mb");
				}
				
				String mediaDirectory = Utilities.getPath(PATH_MEDIA_IMAGE_PROFILE);
				File convertFile = new File(mediaDirectory + pathImage.getOriginalFilename());
				convertFile.createNewFile();
				FileOutputStream fout = new FileOutputStream(convertFile);
				fout.write(pathImage.getBytes());
				fout.close();
			}
			catch(IOException e) {
				throw new NewsException(HUBO_ERROR + e.getMessage());
			}
			
			userMedia.setUsMdId(0);
			userMedia.setUsId(userId);
			userMedia.setUsMdPath(pathImage.getOriginalFilename());
			userMedia.setUsMdTsCreated(dateTime);
			userMedia.setUsMdTsUpdated(dateTime);
			userMediaRepository.save(userMedia);
			
			newsFeed.setUsMdId(userMedia.getUsMdId());
		}
		
		//Add a comment if there is one in the request
		if(!input_comment.isEmpty()) {
			
			if(input_comment.length() > 180) {
				throw new NewsException("The maximum character per comment is 180");
			}
			
			userComments.setUsComId(0);
			userComments.setUsId(userId);
			userComments.setUsComComment(input_comment);
			userComments.setUsComTsCreated(dateTime);
			userComments.setUsComTsUpdated(dateTime);
			userCommentsRepository.save(userComments);
			
			newsFeed.setUsCommentId(userComments.getUsComId());
		}
		
		newsFeed.setNwId(0);
		newsFeed.setNwStatus(1);
		newsFeed.setUsId(userId);
		newsFeed.setNwTsCreated(dateTime);
		newsFeed.setNwTsUpdated(dateTime);
		
		return newsRepository.save(newsFeed);
	}

	public UserComments editNews(int commentId, String inputComment) {
		UserComments request = new UserComments();
		dateTime = Utilities.getTimestamp();
		request = userCommentsRepository.getOne(commentId);
		request.setUsComComment(inputComment);
		request.setUsComTsUpdated(dateTime);
		
		return userCommentsRepository.save(request);
	}
	
	public void deleteNews(int nwId) {
		NewsFeed request = new NewsFeed();
		dateTime = Utilities.getTimestamp();
		
		request = newsRepository.getOne(nwId);
		request.setNwStatus(0);
		request.setNwTsUpdated(dateTime);
		
		newsRepository.save(request);
	}
	
	public void addOrRemoveReactionToPost(NewsReaction newsReaction) {
		dateTime = Utilities.getTimestamp();
		
		if(newsReaction.getNwrId() == 0) {
			//Add a reaction
			newsReaction.setNwrTsCreated(dateTime);	
			nwReactionRepository.save(newsReaction);
		}
		else {
			//Delete reaction
			nwReactionRepository.delete(newsReaction);
		}
	}

}
