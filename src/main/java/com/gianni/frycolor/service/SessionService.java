package com.gianni.frycolor.service;

import java.io.IOException;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gianni.frycolor.entities.User;
import com.gianni.frycolor.exception.EmailException;
import com.gianni.frycolor.exception.UserExistException;
import com.gianni.frycolor.model.ResponseSuccessMsg;
import com.gianni.frycolor.repository.SessionDao;
import com.gianni.frycolor.util.SendMail;
import com.gianni.frycolor.util.Utilities;

import static com.gianni.frycolor.util.Constantes.INVALID_EMAIL_FORMAT;

@Service
public class SessionService {
	
	@Autowired
	SessionDao repository;
	
	@Autowired
	User user;
	
	public ResponseSuccessMsg sendChangesPassword(String emailID) {
		
		if(!Utilities.validateEmailFormat(emailID)) {
			throw new EmailException(INVALID_EMAIL_FORMAT);
		}
		
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
		
		ResponseSuccessMsg response = new ResponseSuccessMsg();
		response.setMessage("Email sent it");
		return response;
	}
	
	public ResponseSuccessMsg changePasswordForgotten(String userId, String newPassword) {
		String userIdDecoded = Utilities.encodeOrDecodeBase64(userId, false);
		user = repository.getUserCredentials(Integer.parseInt(userIdDecoded));
		user.setUsPassword(newPassword);
		
		String dateTime = Utilities.getTimestamp();
		user.setUsTsUpdated(dateTime);
		
		repository.save(user);
		
		ResponseSuccessMsg response = new ResponseSuccessMsg();
		response.setMessage("Password has been changed");
		
		return response;
	}
	
}
