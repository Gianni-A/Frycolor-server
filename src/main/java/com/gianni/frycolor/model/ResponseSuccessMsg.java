package com.gianni.frycolor.model;

public class ResponseSuccessMsg {
	
	private String message;
	private String dataJSON;

	public ResponseSuccessMsg(String message) {
		super();
		this.message = message;
	}
	
	public ResponseSuccessMsg(String message, String dataJSON) {
		super();
		this.message = message;
		this.dataJSON = dataJSON;
	}

	public String getMessage() {
		return message;
	}
	
	public String getDataJSON() {
		return dataJSON;
	}

}
