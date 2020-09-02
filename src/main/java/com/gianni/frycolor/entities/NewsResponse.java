package com.gianni.frycolor.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
	@JoinColumn(name = "US_ID")
	@ManyToOne(fetch = FetchType.LAZY, targetEntity = User.class)
	private User usId;
	
	@ApiModelProperty(notes = "Comment ID from the table user_comments (UserComments.java)")
	@JoinColumn(name = "US_COM_ID")
	@ManyToOne(fetch = FetchType.LAZY, targetEntity = UserComments.class)
	private UserComments usComId;
	
	//This field is going to implement when we want to add response to the responses of a comment.
	@ApiModelProperty(notes = "NewsFeed or Comment ID from the tables news and user_comments (NewsFeed.java) | (UserComments.java).")
	@Column(name = "NW_COM_ORIGIN_ID")
	private int nwComOriginId;
	
	@ApiModelProperty(notes = "Status of the response, if status is 1 = Active | 0 = NOT Active")
	@Column(name = "NW_RES_STATUS")
	private int nwResStatus;
	
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

	public User getUsId() {
		return usId;
	}

	public void setUsId(User usId) {
		this.usId = usId;
	}

	public UserComments getUsComId() {
		return usComId;
	}

	public void setUsComId(UserComments usComId) {
		this.usComId = usComId;
	}

	public int getNwComOriginId() {
		return nwComOriginId;
	}

	public void setNwComOriginId(int nwComOriginId) {
		this.nwComOriginId = nwComOriginId;
	}

	public int getNwResStatus() {
		return nwResStatus;
	}

	public void setNwResStatus(int nwResStatus) {
		this.nwResStatus = nwResStatus;
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
