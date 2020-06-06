package com.gianni.frycolor.model;

import java.util.Optional;

import org.springframework.stereotype.Component;

import com.gianni.frycolor.entities.UserComments;

@Component
public class ResponsePost {

	private int nwResId;
	private Optional<UserComments> comment;
	private String nameUser;
	private int contReactions;
	
	public int getNwResId() {
		return nwResId;
	}
	public void setNwResId(int nwResId) {
		this.nwResId = nwResId;
	}
	public Optional<UserComments> getComment() {
		return comment;
	}
	public void setComment(Optional<UserComments> comment) {
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
}
