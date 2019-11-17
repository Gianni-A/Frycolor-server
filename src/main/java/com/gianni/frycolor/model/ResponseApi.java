package com.gianni.frycolor.model;

import org.springframework.stereotype.Component;

@Component
public class ResponseApi {
	
	private int codeStatus;
	private String message;
	private Object objectData;
	
	/*public ResponseApi() {
		super();
	}*/
	
	public int getCodeStatus() {
		return codeStatus;
	}
	public void setCodeStatus(int codeStatus) {
		this.codeStatus = codeStatus;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Object getData() {
		return objectData;
	}
	public void setData(Object objectData) {
		this.objectData = objectData;
	}

}
