package com.gianni.frycolor.entities;

import java.sql.Timestamp;

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
@Table(name = "news")
@ApiModel(description = "All details about the news feed of the user ")
public class NewsFeed {
	
	@Id
	@ApiModelProperty(notes = "The database generated news ID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "NW_ID", columnDefinition = "int")
	private int nwId;
	
	@Column(name = "US_ID")
	private int usId;
	
	@Column(name = "US_COMMENT_ID")
	private int usCommentId;

	@Column(name = "US_MD_ID")
	@ApiModelProperty(notes = "The foreign key for the table user_media")
	private int usMdId;
	
	@Column(name = "NW_STATUS")
	@ApiModelProperty(notes = "The status means if it is 1 = can show the post, 0 = the post is hide")
	private int nwStatus;
	
	@Column(name = "NW_TS_CREATED")
	private String nwTsCreated;
	
	@Column(name = "NW_TS_UPDATED")
	private String nwTsUpdated;

	public int getNwId() {
		return nwId;
	}

	public void setNwId(int nwId) {
		this.nwId = nwId;
	}

	public int getUsId() {
		return usId;
	}

	public void setUsId(int usId) {
		this.usId = usId;
	}

	public int getUsCommentId() {
		return usCommentId;
	}

	public void setUsCommentId(int usCommentId) {
		this.usCommentId = usCommentId;
	}

	public int getUsMdId() {
		return usMdId;
	}

	public void setUsMdId(int usMdId) {
		this.usMdId = usMdId;
	}

	public int getNwStatus() {
		return nwStatus;
	}

	public void setNwStatus(int nwStatus) {
		this.nwStatus = nwStatus;
	}

	public String getNwTsCreated() {
		return nwTsCreated;
	}

	public void setNwTsCreated(String nwTsCreated) {
		this.nwTsCreated = nwTsCreated;
	}

	public String getNwTsUpdated() {
		return nwTsUpdated;
	}

	public void setNwTsUpdated(String nwTsUpdated) {
		this.nwTsUpdated = nwTsUpdated;
	}

}
