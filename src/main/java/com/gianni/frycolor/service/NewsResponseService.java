package com.gianni.frycolor.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gianni.frycolor.entities.NewsResponse;
import com.gianni.frycolor.entities.UserComments;
import com.gianni.frycolor.model.RequestNewsResponse;
import com.gianni.frycolor.model.ResponseApi;
import com.gianni.frycolor.model.ValidateResponsesModel;
import com.gianni.frycolor.repository.impl.NewsResponseDaoImpl;

@Service
public class NewsResponseService {
	
	@Autowired
	private ResponseApi response;
	
	@Autowired
	private NewsResponseDaoImpl repositoryImpl;
	
	
	public ResponseApi addResponse(RequestNewsResponse request) {
		response = new ResponseApi();
		ValidateResponsesModel validation = new ValidateResponsesModel();
		validation.setComment(request.getComment());
		validation.setUsId(request.getUsId());
		validation.setNwComOriginId(request.getNwComOriginId());
		validation.setNwResComOriginId(0);
		
		response = validationsResponse(validation);
		if(response.getCodeStatus() != 0) {
			return response;
		}
		
		response.setCodeStatus(200);
		response.setMessage("Response added");
		response.setData(repositoryImpl.addResponse(request));
		
		return response;
	}
	
	public ResponseApi editResponse(int nwResId, String comment) {
		response = new ResponseApi();
		ValidateResponsesModel validation = new ValidateResponsesModel();
		NewsResponse getResponse = repositoryImpl.getResponseById(nwResId);
		
		validation.setUsId(getResponse.getUsId());
		validation.setComment(comment);
		validation.setNwResComOriginId(nwResId);
		validation.setNwComOriginId(0);
		
		response = validationsResponse(validation);
		if(response.getCodeStatus() != 0) {
			return response;
		}
		
		UserComments editComment = repositoryImpl.getCommentById(getResponse.getUsComId());
		editComment.setUsComComment(comment);
		repositoryImpl.editComment(editComment);
		
		response.setCodeStatus(200);
		response.setMessage("Response changed");
		response.setData(repositoryImpl.updateResponse(getResponse));
		
		return response;
	}
	
	//Validations before to insert the response
	private ResponseApi validationsResponse(ValidateResponsesModel validate) {
		if(validate.getComment().isEmpty()) {
			response.setCodeStatus(400);
			response.setMessage("Comment input is empty");
			response.setData(null);
			validate = null;
			return response;
		} else if(!repositoryImpl.userStatusActive(validate.getUsId())) {
			response.setCodeStatus(404);
			response.setMessage("The user is not active or exist.");
			response.setData(null);
			validate = null;
			return response;
		} else if(validate.getNwComOriginId() != 0) {
			if(!repositoryImpl.postExistsOrActive(validate.getNwComOriginId())) {
				response.setCodeStatus(404);
				response.setMessage("The post is not active or exist");
				response.setData(null);
				validate = null;
				return response;
			}
			
			return response;
		} else if(validate.getNwResComOriginId() != 0) {
			if(!repositoryImpl.responseExistsOrActive(validate.getNwResComOriginId())) {
				response.setCodeStatus(404);
				response.setMessage("The response of a post is not active or exist");
				response.setData(null);
				validate = null;
				return response;
			}
			return response;
		}
		
		return response;
	}
	
}
