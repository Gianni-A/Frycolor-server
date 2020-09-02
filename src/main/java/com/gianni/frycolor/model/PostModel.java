package com.gianni.frycolor.model;

public class PostModel {
	
	private int nwId;
	private String comment;
	private String pathImage;
	private String nameUser;

	public PostModel(int nwId, String comment, String pathImage, String nameUser) {
		super();
		this.nwId = nwId;
		this.comment = comment;
		this.pathImage = pathImage;
		this.nameUser = nameUser;
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
	public String getNameUser() {
		return nameUser;
	}

	public void setNameUser(String nameUser) {
		this.nameUser = nameUser;
	}
	

}
