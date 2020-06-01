package com.gianni.frycolor.service;

import java.io.IOException;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gianni.frycolor.entities.User;
import com.gianni.frycolor.entities.UserInformation;
import com.gianni.frycolor.exception.UserExistException;
import com.gianni.frycolor.exception.UserValidationsException;
import com.gianni.frycolor.repository.SignUpDao;
import com.gianni.frycolor.util.SendMail;
import com.gianni.frycolor.util.Utilities;

@Service
public class SignUpService {
	
	@Autowired
	SignUpDao data;
	
	@Autowired
	UserInformation userInfo;
	
	
	public User signUpUser(User user) {
		
		if(user.getUsUser().isEmpty() || user.getUsPassword().isEmpty() || user.getUsEmail().isEmpty()) {
			throw new UserValidationsException("Needs to comple the required data");
		}
		
		if(validateUserAndEmail(user)) {
			throw new UserExistException("It already exist the user");
		}
		
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
		
		try {
			String content = "Thank you for registered at Frycolor, "
					+ "to finish you need to enter to this URL: http://localhost:8090/users/" + user.getUsId();
			
			SendMail.sendEmail(user.getUsEmail(), content);
		} catch (MessagingException | IOException e) {
			e.printStackTrace();
		}
		
		return data.save(user);
	}
	
	@Transactional
	public void setStatusRegisterUser(int userId) {
		if(data.setStatusRegisterUser(userId, Utilities.getTimestamp()) == 0) {
			throw new UserValidationsException("There is something wrong in the server");
		}
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
