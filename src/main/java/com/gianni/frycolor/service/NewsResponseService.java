package com.gianni.frycolor.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gianni.frycolor.entities.NewsResponse;
import com.gianni.frycolor.entities.UserComments;
import com.gianni.frycolor.entities.UserInformation;
import com.gianni.frycolor.exception.NewsResponseException;
import com.gianni.frycolor.model.RequestNewsResponse;
import com.gianni.frycolor.model.ResponseModelResponses;
import com.gianni.frycolor.model.ResponseSuccessMsg;
import com.gianni.frycolor.model.ValidateResponsesModel;
import com.gianni.frycolor.repository.impl.NewsFeedDaoImpl;
import com.gianni.frycolor.repository.impl.NewsResponseDaoImpl;

@Service
public class NewsResponseService {
	
	@Autowired
	private NewsResponseDaoImpl repositoryImpl;
	
	@Autowired NewsFeedDaoImpl repositoryNewsFeedImpl;
	
	@Autowired
	public void setNewsResponseDaoImp(NewsResponseDaoImpl repository) {repositoryImpl = repository;}
	
	public ResponseModelResponses addResponse(RequestNewsResponse request) {
		ResponseModelResponses response = new ResponseModelResponses();
		ValidateResponsesModel validation = new ValidateResponsesModel();
		validation.setComment(request.getComment());
		validation.setUsId(request.getUsId());
		validation.setNwComOriginId(request.getNwComOriginId());
		validation.setNwResComOriginId(0);
		
		String validateMsg = validationsResponse(validation);
		if(!validateMsg.isEmpty()) {
			throw new NewsResponseException(validateMsg);
		}
		
		NewsResponse saveValue = repositoryImpl.addResponse(request);
		UserInformation user = saveValue.getUsId().getUsInfId();
		response.setUserId(request.getUsId());
		response.setNameUser(user.getUsInfName() + " " + user.getUsInfLastname());
		response.setComment(saveValue.getUsComId().getUsComComment());
		response.setNwId(saveValue.getNwComOriginId());
		response.setNwResId(saveValue.getNwResId());
		
		return response;
	}
	
	public NewsResponse editResponse(int nwResId, String comment) {
		ValidateResponsesModel validation = new ValidateResponsesModel();
		NewsResponse getResponse = repositoryImpl.getResponseById(nwResId);
		
		
		validation.setUsId(getResponse.getUsId().getUsId());
		validation.setComment(comment);
		validation.setNwResComOriginId(nwResId);
		validation.setNwComOriginId(0);
		
		String validateMsg = validationsResponse(validation);
		if(!validateMsg.isEmpty()) {
			throw new NewsResponseException(validateMsg);
		}
		
		UserComments editComment = repositoryImpl.getCommentById(getResponse.getUsComId().getUsComId());
		editComment.setUsComComment(comment);
		repositoryImpl.editComment(editComment);
		
		return repositoryImpl.updateResponse(getResponse);
	}
	
	public ResponseSuccessMsg deleteResponse(int nwId, int nwResId) {
		
		NewsResponse nwResponse = new NewsResponse();
		nwResponse = repositoryImpl.getResponseById(nwResId);
		
		repositoryImpl.deleteResponse(nwResponse);
		
		final String json = "{\"nwId\":"+nwId+", \"nwResId\": "+nwResId+"}";
		
		ResponseSuccessMsg message = new ResponseSuccessMsg("Response of a post deleted", json);
		return message;
	}
	
	public ResponseSuccessMsg addOrRemoveReaction(int nwResId, int userId) {
		String validateMsg = validateResponseForReaction(userId, nwResId);
		if(!validateMsg.isEmpty()) {
			throw new NewsResponseException(validateMsg);
		}
		
		Integer reaction = repositoryImpl.getResponseReaction(nwResId, userId);
		ResponseSuccessMsg message = null;
		
		if(reaction != null) {
			repositoryImpl.deleteReactionResponse(reaction);
			message = new ResponseSuccessMsg("Reaction of a response deleted");
		}
		else {
			repositoryImpl.addReactionResponse(nwResId, userId);
			message = new ResponseSuccessMsg("Reaction of a response added");
		}
		
		return message;
	}
	
	//Validations before to insert or edit the response
	private String validationsResponse(ValidateResponsesModel validate) {
		if(validate.getComment().isEmpty()) {
			validate = null;
			return "Comment input is empty";
		} else if(!repositoryImpl.userStatusActive(validate.getUsId())) {
			validate = null;
			return "The user is not active or exist.";
		} else if(validate.getNwComOriginId() != 0) {
			if(!repositoryImpl.postExistsOrActive(validate.getNwComOriginId())) {
				validate = null;
				return "The post is not active or exist";
			}
			return "";
		} else if(validate.getNwResComOriginId() != 0) {
			if(!repositoryImpl.responseExistsOrActive(validate.getNwResComOriginId())) {
				validate = null;
				return "The response of a post is not active or exist";
			}
			return "";
		}
		
		return "";
	}
	
	private String validateResponseForReaction(int userId, int rrId) {
		if(!repositoryImpl.userStatusActive(userId)) {
			return "The user is not active or exist.";
		} else if(!repositoryImpl.responseExistsOrActive(rrId)) {
			return "The response of a post is not active or exist";
		} else {
			return "";
		}
	}
}
