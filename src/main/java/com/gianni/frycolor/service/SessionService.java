package com.gianni.frycolor.service;

import java.io.IOException;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gianni.frycolor.entities.User;
import com.gianni.frycolor.exception.EmailException;
import com.gianni.frycolor.exception.UserExistException;
import com.gianni.frycolor.repository.SessionDao;
import com.gianni.frycolor.util.SendMail;
import com.gianni.frycolor.util.Utilities;

@Service
public class SessionService {
	
	@Autowired
	SessionDao repository;
	
	@Autowired
	User user;
	
	public void sendChangesPassword(String emailID) {
		Integer userId = repository.getUserIdByEmail(emailID.toLowerCase());
		
		if(userId == null) {
			throw new UserExistException("User not found");
		}
		
		String userIdEncoded = Utilities.encodeOrDecodeBase64(String.valueOf(userId), true);
		String content = "You requested to change your password, please click in this link to procced it: http://localhost:8090/session/password/" + userIdEncoded;
		try {
			SendMail.sendEmail(emailID, content);
		} catch(MessagingException | IOException e) {
			throw new EmailException(e.getMessage());
		}
		
	}
	
	public void changePasswordForgotten(String userId, String newPassword) {
		String userIdDecoded = Utilities.encodeOrDecodeBase64(userId, false);
		user = repository.getUserCredentials(Integer.parseInt(userIdDecoded));
		user.setUsPassword(newPassword);
		
		String dateTime = Utilities.getTimestamp();
		user.setUsTsUpdated(dateTime);
		
		repository.save(user);
	}
	
}
