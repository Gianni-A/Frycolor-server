package com.gianni.frycolor.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name = "user_friends")
@ApiModel(description = "IDS of friends of each user")
public class UserFriends {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "FRD_ID", columnDefinition = "int")
	@ApiModelProperty(notes = "The database generated userFriend ID")
	private int frdId;
	
	@Column(name = "FRD_US_ID", columnDefinition = "int")
	@ApiModelProperty(notes = "The ID of the user owner")
	private int frdUsId;
	
	@Column(name = "FRD_US_ID_UF", columnDefinition = "int")
	@ApiModelProperty(notes = "The ID of the user of its friend")
	private int frdUsIdUf;
	
	@Column(name = "FRD_TS_CREATED")
	private String frdTsCreated;
	
	@Column(name = "FRD_TS_UPDATED")
	private String frdTsUpdated;

	public int getFrdId() {
		return frdId;
	}

	public void setFrdId(int frdId) {
		this.frdId = frdId;
	}

	public int getFrdUsId() {
		return frdUsId;
	}

	public void setFrdUsId(int frdUsId) {
		this.frdUsId = frdUsId;
	}

	public int getFrdUsIdUf() {
		return frdUsIdUf;
	}

	public void setFrdUsIdUf(int frdUsIdUf) {
		this.frdUsIdUf = frdUsIdUf;
	}

	public String getFrdTsCreated() {
		return frdTsCreated;
	}

	public void setFrdTsCreated(String frdTsCreated) {
		this.frdTsCreated = frdTsCreated;
	}

	public String getFrdTsUpdated() {
		return frdTsUpdated;
	}

	public void setFrdTsUpdated(String frdTsUpdated) {
		this.frdTsUpdated = frdTsUpdated;
	}
}
