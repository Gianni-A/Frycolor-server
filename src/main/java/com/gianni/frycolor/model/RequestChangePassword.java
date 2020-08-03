package com.gianni.frycolor.model;

public class RequestChangePassword {
	
	private int userId;
	private String actualPassword;
	private String newPassword;
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getActualPassword() {
		return actualPassword;
	}
	public void setActualPassword(String actualPassword) {
		this.actualPassword = actualPassword;
	}
	public String getNewPassword() {
		return newPassword;
	}
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

}
