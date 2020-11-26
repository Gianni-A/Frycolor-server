package com.gianni.frycolor.model;

public class ResponseModelResponses {
	
	private int userId;
	private String nameUser;
	private String comment;
	private int nwId;
	private int nwResId;
	
	
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
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public int getNwId() {
		return nwId;
	}
	public void setNwId(int nwId) {
		this.nwId = nwId;
	}
	public int getNwResId() {
		return nwResId;
	}
	public void setNwResId(int nwResId) {
		this.nwResId = nwResId;
	}
}
