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
@Table(name = "user_media")
@ApiModel(description = "The table user_media is to use to save path images for everything, not just for profile images")
public class UserMedia {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "US_MD_ID", columnDefinition = "int default 0")
	@ApiModelProperty(notes = "The database generated media ID")
	private int usMdId;
	
	@Column(name = "US_MD_PATH")
	private String usMdPath;
	
	@JoinColumn(name = "US_ID")
	@ApiModelProperty(notes = "The foreign key of the id user for the table users_app")
	@ManyToOne(fetch = FetchType.LAZY, targetEntity = User.class)
	private User usId;
	
	@Column(name = "US_MD_TS_CREATED")
	private String usMdTsCreated;
	
	@Column(name = "US_MD_TS_UPDATED")
	private String usMdTsUpdated;

	public int getUsMdId() {
		return usMdId;
	}

	public void setUsMdId(int usMdId) {
		this.usMdId = usMdId;
	}

	public String getUsMdPath() {
		return usMdPath;
	}

	public void setUsMdPath(String usMdPath) {
		this.usMdPath = usMdPath;
	}

	public User getUsId() {
		return usId;
	}

	public void setUsId(User usId) {
		this.usId = usId;
	}

	public String getUsMdTsCreated() {
		return usMdTsCreated;
	}

	public void setUsMdTsCreated(String usMdTsCreated) {
		this.usMdTsCreated = usMdTsCreated;
	}

	public String getUsMdTsUpdated() {
		return usMdTsUpdated;
	}

	public void setUsMdTsUpdated(String usMdTsUpdated) {
		this.usMdTsUpdated = usMdTsUpdated;
	}

	
}
