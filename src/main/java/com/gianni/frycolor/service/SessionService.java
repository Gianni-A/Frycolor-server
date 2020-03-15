package com.gianni.frycolor.service;

import java.io.IOException;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gianni.frycolor.entities.User;
import com.gianni.frycolor.model.ResponseApi;
import com.gianni.frycolor.repository.SessionDao;
import com.gianni.frycolor.util.SendMail;
import com.gianni.frycolor.util.Utilities;

@Service
public class SessionService {
	
	@Autowired
	ResponseApi response;
	
	@Autowired
	SessionDao repository;
	
	@Autowired
	User user;
	
	
	public ResponseApi sendChangesPassword(String emailID) {
		int userId = repository.getUserIdByEmail(emailID.toLowerCase());
		if(userId != 0) {
			String userIdEncoded = Utilities.encodeOrDecodeBase64(String.valueOf(userId), true);
			String content = "You requested to change your password, please click in this link to procced it: http://localhost:8090/session/password/" + userIdEncoded;
			try {
				SendMail.sendEmail(emailID, content);
			} catch(MessagingException | IOException e) {
				e.printStackTrace();
			}
			
			response.setCodeStatus(200);
			response.setMessage("Email sent it");
			response.setData(null);
		}
		else {
			response.setCodeStatus(404);
			response.setMessage("User not found");
			response.setData(null);
		}
		return response;
	}
	
	public ResponseApi changePasswordForgotten(String userId, String newPassword) {
		String userIdDecoded = Utilities.encodeOrDecodeBase64(userId, false);
		user = repository.getUserCredentials(Integer.parseInt(userIdDecoded));
		user.setUsPassword(newPassword);
		
		String dateTime = Utilities.getTimestamp();
		user.setUsTsUpdated(dateTime);
		
		repository.save(user);
		
		response.setCodeStatus(200);
		response.setMessage("Password changed");
		response.setData(null);
		
		return response;
	}
	
}
