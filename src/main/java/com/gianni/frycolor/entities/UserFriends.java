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

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

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
	
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	@JoinColumn(name = "FRD_US_ID")
	@ApiModelProperty(notes = "The ID of the user owner")
	@ManyToOne(fetch = FetchType.LAZY, targetEntity = User.class)
	private User frdUsId;
	
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	@JoinColumn(name = "FRD_US_ID_UF")
	@ApiModelProperty(notes = "The ID of the user of its friend")
	@ManyToOne(fetch = FetchType.LAZY, targetEntity = User.class)
	private User frdUsIdUf;
	
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

	public User getFrdUsId() {
		return frdUsId;
	}

	public void setFrdUsId(User frdUsId) {
		this.frdUsId = frdUsId;
	}

	public User getFrdUsIdUf() {
		return frdUsIdUf;
	}

	public void setFrdUsIdUf(User frdUsIdUf) {
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
