package com.gianni.frycolor.model;

public class PostModel {
	
	private int nwId;
	private String comment;
	private String pathImage;
	private String imageProfile;
	private int userId;
	private String nameUser;
	private Integer userLike;

	public PostModel(int nwId, String comment, String pathImage, Integer userLike, String imageProfile, int userId, String nameUser) {
		super();
		this.nwId = nwId;
		this.comment = comment;
		this.pathImage = pathImage;
		this.imageProfile = imageProfile;
		this.userId = userId;
		this.nameUser = nameUser;
		this.userLike = userLike;
	}
	
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
	public String getPathImage() {
		return pathImage;
	}
	public void setPathImage(String pathImage) {
		this.pathImage = pathImage;
	}
	public String getImageProfile() {
		return imageProfile;
	}
	public void setImageProfile(String imageProfile) {
		this.imageProfile = imageProfile;
	}
	
	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getNameUser() {
		return nameUser;
	}
	public void setNameUser(String nameUser) {
		this.nameUser = nameUser;
	}
	public Integer getUserLike() {
		return userLike;
	}

	public void setUserLike(Integer userLike) {
		this.userLike = userLike;
	}

}
