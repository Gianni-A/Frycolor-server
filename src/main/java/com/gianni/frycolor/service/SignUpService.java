package com.gianni.frycolor.service;

import java.io.IOException;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gianni.frycolor.entities.User;
import com.gianni.frycolor.entities.UserInformation;
import com.gianni.frycolor.model.ResponseApi;
import com.gianni.frycolor.repository.SignUpDao;
import com.gianni.frycolor.util.SendMail;
import com.gianni.frycolor.util.Utilities;

@Service
public class SignUpService {
	
	@Autowired
	SignUpDao data;
	
	@Autowired
	ResponseApi response;
	
	@Autowired
	UserInformation userInfo;
	
	
	public ResponseApi signUpUser(User user) {
		if(!validateUserAndEmail(user)) {
			String dateTime = Utilities.getTimestamp();
			user.setUsEmail(user.getUsEmail().toLowerCase());
			
			userInfo.setUsInfId(0);
			userInfo.setUsInfName(user.getUsUser());
			userInfo.setUsInfTsCreated(dateTime);
			userInfo.setUsInfTsUpdated(dateTime);
			user.setUsInfId(userInfo);
			
			//Set up the date and time of the record
			user.setUsTsCreated(dateTime);
			user.setUsTsUpdated(dateTime);
			
			response.setCodeStatus(200);
			response.setMessage("User created");
			response.setData(data.save(user));
			
			try {
				String content = "Thank you for registered at Frycolor, to finish you need to enter to this URL: http://localhost:8090/users/" + user.getUsId();
				SendMail.sendEmail(user.getUsEmail(), content);
			} catch (MessagingException | IOException e) {
				e.printStackTrace();
			}
			
			return response;
		}
		
		response.setCodeStatus(500);
		response.setMessage("It already exist the user");
		return response;
	}
	
	@Transactional
	public ResponseApi setStatusRegisterUser(int userId) {
		response = new ResponseApi();
		if(data.setStatusRegisterUser(userId) != 0) {
			response.setCodeStatus(201);
			response.setMessage("User updated");
		}
		else {
			response.setCodeStatus(500);
			response.setMessage("There is something wrong in the server");
		}
		return response;
	}
	
	//Validate if there is a user with the same name or if there is an email ID in the DB
	private boolean validateUserAndEmail(User user) {
		if(data.findByName(user.getUsUser()) != 0) {
			return true;
		}
		
		if(data.findByEmail(user.getUsEmail()) != 0) {
			return true;
		}
			
		return false;
	}

}
