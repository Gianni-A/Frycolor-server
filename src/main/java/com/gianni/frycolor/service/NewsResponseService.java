package com.gianni.frycolor.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gianni.frycolor.model.RequestNewsResponse;
import com.gianni.frycolor.model.ResponseApi;
import com.gianni.frycolor.repository.impl.NewsResponseDaoImpl;

@Service
public class NewsResponseService {
	
	@Autowired
	private ResponseApi response;
	
	@Autowired
	private NewsResponseDaoImpl repositoryImpl;
	
	
	public ResponseApi addResponse(RequestNewsResponse request) {
		response = validationsResponse(request);
		if(response != null) {
			return response;
		}
		
		response.setCodeStatus(200);
		response.setMessage("Response added");
		response.setData(repositoryImpl.addResponse(request));
		
		return response;
	}
	
	public ResponseApi editResponse(int nwResId, int userId, String comment) {
		RequestNewsResponse request = new RequestNewsResponse();
		request.setNwComOriginId(nwResId);
		request.setUsId(userId);
		request.setComment(comment);
		
		response = validationsResponse(request);
		if(response != null) {
			return response;
		}
		
		response.setCodeStatus(200);
		response.setMessage("Response changed");
		response.setData(repositoryImpl.addResponse(request));
		
		return response;
	}
	
	private ResponseApi validationsResponse(RequestNewsResponse request) {
		if(request.getComment().isEmpty()) {
			response.setCodeStatus(400);
			response.setMessage("Comment input is empty");
			response.setData(null);
			return response;
		} else if(!repositoryImpl.userStatusActive(request.getUsId())) {
			response.setCodeStatus(404);
			response.setMessage("The user is not active or exist.");
			response.setData(null);
			return response;
		} else if(!repositoryImpl.originExistsOrActive(request.getNwComOriginId())) {
			response.setCodeStatus(404);
			response.setMessage("The post is not active or exist");
			response.setData(null);
			return response;
		} else {
			return null;
		}
	}
	
}
