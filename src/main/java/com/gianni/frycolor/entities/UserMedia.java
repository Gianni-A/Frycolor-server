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
@Table(name = "user_media")
@ApiModel(description = "The table user_media is to use to save path images for everything, not just for profile images")
public class UserMedia {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "US_COM_ID", columnDefinition = "int")
	@ApiModelProperty(notes = "The database generated media ID")
	private int us_md_id;
	
	@Column(name = "US_MD_PATH")
	private String us_md_path;
	
	@Column(name = "US_ID")
	@ApiModelProperty(notes = "The foreign key of the id user for the table users_app")
	private int us_id;

	public int getUs_md_id() {
		return us_md_id;
	}

	public void setUs_md_id(int us_md_id) {
		this.us_md_id = us_md_id;
	}

	public String getUs_md_path() {
		return us_md_path;
	}

	public void setUs_md_path(String us_md_path) {
		this.us_md_path = us_md_path;
	}

	public int getUs_id() {
		return us_id;
	}

	public void setUs_id(int us_id) {
		this.us_id = us_id;
	}
}
