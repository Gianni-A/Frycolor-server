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
import com.gianni.frycolor.model.ResponseApi;
import com.gianni.frycolor.repository.NewsFeedDao;
import com.gianni.frycolor.repository.NewsReactionDao;
import com.gianni.frycolor.repository.UserCommentsDao;
import com.gianni.frycolor.repository.UserMediaDao;
import com.gianni.frycolor.util.Utilities;

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
	private ResponseApi response;
	
	@Autowired
	private NewsFeed newsFeed;
	
	@Autowired
	private UserMedia userMedia;
	
	@Autowired
	private UserComments userComments;
	
	final public String PATH_MEDIA_IMAGE_PROFILE = "media\\post\\";
	
	String dateTime = "";
	
	
	public ResponseApi getAllNews(int userId) {
		return null;
	}
	
	
	public ResponseApi getNewsPerUser(int userId) {
		return null;
	}
	
	public ResponseApi saveNews(MultipartFile pathImage, String input_comment, int userId) throws IOException {
		dateTime = Utilities.getTimestamp();
		newsFeed.setUsMdId(0);
		newsFeed.setUsCommentId(0);
		
		//Add an image if there is one
		if(!pathImage.getOriginalFilename().isEmpty()) {
			String mediaDirectory = Utilities.getPath(PATH_MEDIA_IMAGE_PROFILE);
			File convertFile = new File(mediaDirectory + pathImage.getOriginalFilename());
			convertFile.createNewFile();
			FileOutputStream fout = new FileOutputStream(convertFile);
			fout.write(pathImage.getBytes());
			fout.close();
			
			userMedia.setUsMdId(0);
			userMedia.setUsId(userId);
			userMedia.setUsMdPath(pathImage.getOriginalFilename());
			userMedia.setUsMdTsCreated(dateTime);
			userMedia.setUsMdTsUpdated(dateTime);
			userMediaRepository.save(userMedia);
			
			newsFeed.setUsMdId(userMedia.getUsMdId());
		}
		
		//Add a comment if there is one
		if(!input_comment.isEmpty()) {
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
		
		response.setCodeStatus(200);
		response.setMessage("Data inserted");
		response.setData(newsRepository.save(newsFeed));
		
		return response;
	}

	public ResponseApi editNews(int commentId, String inputComment) {
		UserComments request = new UserComments();
		dateTime = Utilities.getTimestamp();
		request = userCommentsRepository.getOne(commentId);
		request.setUsComComment(inputComment);
		request.setUsComTsUpdated(dateTime);
		
		response.setCodeStatus(200);
		response.setMessage("Comment updated");
		response.setData(userCommentsRepository.save(request));
		
		return response;
	}
	
	public ResponseApi deleteNews(int nwId) {
		NewsFeed request = new NewsFeed();
		dateTime = Utilities.getTimestamp();
		
		request = newsRepository.getOne(nwId);
		request.setNwStatus(0);
		request.setNwTsUpdated(dateTime);

		response.setCodeStatus(200);
		response.setMessage("Post deleted");
		response.setData(newsRepository.save(request));
		
		return response;
	}
	
	public ResponseApi addOrRemoveReactionToPost(NewsReaction newsReaction) {
		dateTime = Utilities.getTimestamp();
		
		if(newsReaction.getNwrId() == 0) {
			//Add a reaction
			newsReaction.setNwrTsCreated(dateTime);
			
			response.setCodeStatus(200);
			response.setMessage("Reaction's news added");
			response.setData(nwReactionRepository.save(newsReaction));
		}
		else {
			//Delete reaction
			nwReactionRepository.delete(newsReaction);
			
			response.setCodeStatus(200);
			response.setMessage("Reaction's news deleted");
			response.setData(null);
		}
		
		return response;
	}

}
