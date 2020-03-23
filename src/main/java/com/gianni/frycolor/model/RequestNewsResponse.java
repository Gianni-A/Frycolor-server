package com.gianni.frycolor.model;

import org.springframework.stereotype.Component;

@Component
public class RequestNewsResponse {
	
	private int usId;
	private int nwComOriginId;
	private String comment;
	
	public int getUsId() {
		return usId;
	}
	public void setUsId(int usId) {
		this.usId = usId;
	}
	public int getNwComOriginId() {
		return nwComOriginId;
	}
	public void setNwComOriginId(int nwComOriginId) {
		this.nwComOriginId = nwComOriginId;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}

}
