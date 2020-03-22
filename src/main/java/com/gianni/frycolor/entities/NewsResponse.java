package com.gianni.frycolor.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@Entity
@Component
@Table(name = "news_response")
@ApiModel(description = "The table news_response is use to save reactions of the users for each post")
public class NewsResponse {
	
	@Id
	@ApiModelProperty(notes = "The database generated comment response ID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "NW_RES_ID", columnDefinition = "int")
	private int nwResId;
	
	@ApiModelProperty(notes = "User ID from the table users_app (User.java)")
	@Column(name = "US_ID")
	private int usId;
	
	@ApiModelProperty(notes = "Comment ID from the table user_comments (UserComments.java)")
	@Column(name = "US_COM_ID")
	private int usComId;
	
	//This field is going to implement when we want to add response to the responses of a comment.
	@ApiModelProperty(notes = "NewsFeed or Comment ID from the tables news and user_comments (NewsFeed.java) | (UserComments.java).")
	@Column(name = "NW_COM_ORIGIN_ID")
	private int nwComOriginId;
	
	@Column(name = "NW_RES_TS_CREATED")
	private String nwResTsCreated;
	
	@Column(name = "NW_RES_TS_UPDATED")
	private String nwResTsUpdated;

	public int getNwResId() {
		return nwResId;
	}

	public void setNwResId(int nwResId) {
		this.nwResId = nwResId;
	}

	public int getUsId() {
		return usId;
	}

	public void setUsId(int usId) {
		this.usId = usId;
	}

	public int getUsComId() {
		return usComId;
	}

	public void setUsComId(int usComId) {
		this.usComId = usComId;
	}

	public int getNwComOriginId() {
		return nwComOriginId;
	}

	public void setNwComOriginId(int nwComOriginId) {
		this.nwComOriginId = nwComOriginId;
	}

	public String getNwResTsCreated() {
		return nwResTsCreated;
	}

	public void setNwResTsCreated(String nwResTsCreated) {
		this.nwResTsCreated = nwResTsCreated;
	}

	public String getNwResTsUpdated() {
		return nwResTsUpdated;
	}

	public void setNwResTsUpdated(String nwResTsUpdated) {
		this.nwResTsUpdated = nwResTsUpdated;
	}

}
