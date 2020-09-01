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
@Table(name = "user_comments")
@ApiModel(description = "The table user_comments is use to save comments of the user for almost everything")
public class UserComments {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "US_COM_ID", columnDefinition = "int")
	@ApiModelProperty(notes = "The ID generated from database for id comment")
	private int usComId;
	
	@Column(name = "US_COM_COMMENT")
	private String usComComment;

	@JoinColumn(name = "US_ID")
	@ApiModelProperty(notes = "The ID of the user, it is a foreign key")
	@ManyToOne(fetch = FetchType.LAZY, targetEntity = User.class)
	private User usId;
	
	@Column(name = "US_COM_TS_CREATED")
	private String usComTsCreated;
	
	@Column(name = "US_COM_TS_UPDATED")
	private String usComTsUpdated;

	public int getUsComId() {
		return usComId;
	}

	public void setUsComId(int usComId) {
		this.usComId = usComId;
	}

	public String getUsComComment() {
		return usComComment;
	}

	public void setUsComComment(String usComComment) {
		this.usComComment = usComComment;
	}

	public User getUsId() {
		return usId;
	}

	public void setUsId(User usId) {
		this.usId = usId;
	}

	public String getUsComTsCreated() {
		return usComTsCreated;
	}

	public void setUsComTsCreated(String usComTsCreated) {
		this.usComTsCreated = usComTsCreated;
	}

	public String getUsComTsUpdated() {
		return usComTsUpdated;
	}

	public void setUsComTsUpdated(String usComTsUpdated) {
		this.usComTsUpdated = usComTsUpdated;
	}
}
