package com.gianni.frycolor.repository.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gianni.frycolor.entities.NewsResponse;
import com.gianni.frycolor.entities.ResponseReaction;
import com.gianni.frycolor.entities.User;
import com.gianni.frycolor.entities.UserComments;
import com.gianni.frycolor.entities.UserInformation;
import com.gianni.frycolor.model.RequestNewsResponse;
import com.gianni.frycolor.model.ResponsePost;
import com.gianni.frycolor.repository.NewsFeedDao;
import com.gianni.frycolor.repository.NewsResponseDao;
import com.gianni.frycolor.repository.ResponseReactionDao;
import com.gianni.frycolor.repository.SessionDao;
import com.gianni.frycolor.repository.UserCommentsDao;
import com.gianni.frycolor.util.Utilities;
import com.gianni.frycolor.util.ValidationsDao;

@Component
public class NewsResponseDaoImpl {
	
	@Autowired
	private NewsResponseDao repository;
	
	@Autowired
	private UserCommentsDao userCommentsRepository;
	
	@Autowired
	private NewsFeedDao newsFeedRepository;
	
	@Autowired
	private ResponseReactionDao responseReactionRepository;
	
	@Autowired
	private NewsResponse newsResponse;
	
	@Autowired
	private UserComments userComments;
	
	@Autowired
	private ValidationsDao validations;
	
	@Autowired
	private SessionDao userRepository;
	
	private String dateTime;
	
	public NewsResponse addResponse(RequestNewsResponse request) {
		User user = userRepository.getOne(request.getUsId());
		
		dateTime = Utilities.getTimestamp();
		//Adding a comment into user_comments
		userComments.setUsComId(0);
		userComments.setUsId(userRepository.getOne(request.getUsId()));
		userComments.setUsComComment(request.getComment());
		userComments.setUsComTsCreated(dateTime);
		userComments.setUsComTsUpdated(dateTime);
		userComments = userCommentsRepository.save(userComments);
		
		//Adding the response
		newsResponse.setNwResId(0);
		newsResponse.setUsId(user);
		newsResponse.setUsComId(userComments);
		newsResponse.setNwComOriginId(request.getNwComOriginId());
		newsResponse.setNwResStatus(1);
		newsResponse.setNwResTsCreated(dateTime);
		newsResponse.setNwResTsUpdated(dateTime);
		
		return repository.save(newsResponse);
	}
	
	public UserComments editComment(UserComments comment) {
		dateTime = Utilities.getTimestamp();
		comment.setUsComTsUpdated(dateTime);
		return userCommentsRepository.save(comment);
	}
	
	public NewsResponse updateResponse(NewsResponse response) {
		dateTime = Utilities.getTimestamp();
		response.setNwResTsUpdated(dateTime);
		return repository.save(response);
	}
	
	public void deleteResponse(NewsResponse response) {
		dateTime = Utilities.getTimestamp();
		response.setNwResStatus(0);
		response.setNwResTsUpdated(dateTime);
		repository.save(response);
	}
	
	public void addReactionResponse(int nwResId, int userId) {
		ResponseReaction reaction = new ResponseReaction();
		reaction.setRrId(0);
		reaction.setNwResId(nwResId);
		reaction.setUsId(userId);
		reaction.setRrTsCreated( Utilities.getTimestamp() );
		responseReactionRepository.save(reaction);
	}
	
	public void deleteReactionResponse(int nwResId) {
		responseReactionRepository.deleteById(nwResId);
	}
	
	public boolean userStatusActive(int userId) {
		return validations.userActiveOrExist(userId);
	}
	
	public boolean postExistsOrActive(int newsId) {
		if(newsFeedRepository.existsById(newsId)) {
			if(newsFeedRepository.isPostActive(newsId) <= 0) {
				return false;
			} else {
				return true;
			}
		} else {
			return false;
		}
	}
	
	public boolean responseExistsOrActive(int nwResId) {
		if(repository.existsById(nwResId)) {
			if(repository.isResponseActive(nwResId) <= 0) {
				return false;
			} else {
				return true;
			}
		} else {
			return false;
		}
	}
	
	public NewsResponse getResponseById(int nwResId) {
		return repository.getOne(nwResId);
	}
	
	public UserComments getCommentById(int comId) {
		return userCommentsRepository.getOne(comId);
	}
	
	public Integer getResponseReaction(int nwResId, int userId) {
		return responseReactionRepository.getResponseReaction(nwResId, userId);
	}
	
	public int getCountResReactionNews(int nwrId) {
		return responseReactionRepository.getCountResReactionNews(nwrId);
	}
	
	public List<ResponsePost> getAllResponseFromPost(int origin, int userIdLogged) {
		List<ResponsePost> listResponse = new ArrayList<>();
		
		List<NewsResponse> nwsResponse = repository.getAllResponsesByNwId(origin);
		nwsResponse.stream().forEach(n -> {
			ResponsePost response = new ResponsePost();
			UserInformation userInfo = n.getUsId().getUsInfId();
			
			response.setNwResId(n.getNwResId());
			response.setNameUser(userInfo.getUsInfName() + " " + userInfo.getUsInfLastname());
			response.setComment(n.getUsComId().getUsComComment());
			
			int contReactions = getCountResReactionNews(n.getNwResId());
			response.setContReactions(contReactions);
			response.setUserLike(getResponseReaction(n.getNwResId(), userIdLogged) != null ? true : false);
			response.setDateTime(n.getNwResTsUpdated());
			
			listResponse.add(response);
		});
		
		return listResponse;
	}
	
}
