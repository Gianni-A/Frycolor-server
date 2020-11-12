package com.gianni.frycolor.model;


import org.springframework.stereotype.Component;

@Component
public class ResponsePost {

	private int nwResId;
	private String comment;
	private String nameUser;
	private int contReactions;
	private boolean userLike;
	private String dateTime;
	
	public int getNwResId() {
		return nwResId;
	}
	public void setNwResId(int nwResId) {
		this.nwResId = nwResId;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getNameUser() {
		return nameUser;
	}
	public void setNameUser(String nameUser) {
		this.nameUser = nameUser;
	}
	public int getContReactions() {
		return contReactions;
	}
	public void setContReactions(int contReactions) {
		this.contReactions = contReactions;
	}
	public boolean isUserLike() {
		return userLike;
	}
	public void setUserLike(boolean userLike) {
		this.userLike = userLike;
	}
	public String getDateTime() {
		return dateTime;
	}
	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}
}
