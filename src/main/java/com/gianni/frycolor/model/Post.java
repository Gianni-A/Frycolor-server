package com.gianni.frycolor.model;

import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class Post {
	
	private int nwId;
	private String comment;
	private String image;
	private int contReactions;
	private String imageProfile;
	private String nameUser;
	private boolean userLike;
	private List<ResponsePost> listResponses;
	
	
	public int getNwId() {
		return nwId;
	}
	public void setNwId(int nwId) {
		this.nwId = nwId;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public int getContReactions() {
		return contReactions;
	}
	public void setContReactions(int contReactions) {
		this.contReactions = contReactions;
	}
	public List<ResponsePost> getListResponses() {
		return listResponses;
	}
	public void setListResponses(List<ResponsePost> listResponses) {
		this.listResponses = listResponses;
	}
	public boolean isUserLike() {
		return userLike;
	}
	public void setUserLike(boolean userLike) {
		this.userLike = userLike;
	}
	public String getImageProfile() {
		return imageProfile;
	}
	public void setImageProfile(String imageProfile) {
		this.imageProfile = imageProfile;
	}
	public String getNameUser() {
		return nameUser;
	}
	public void setNameUser(String nameUser) {
		this.nameUser = nameUser;
	}

}
