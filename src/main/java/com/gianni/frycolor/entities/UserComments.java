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
@Table(name = "user_comments")
@ApiModel(description = "The table user_comments is use to save comments of the user for almost everything")
public class UserComments {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "US_COM_ID", columnDefinition = "int")
	@ApiModelProperty(notes = "The ID generated from database for id comment")
	private int us_com_id;
	
	@Column(name = "US_COM_COMMENT")
	private String us_com_comment;

	@Column(name = "US_ID")
	@ApiModelProperty(notes = "The ID of the user, it is a foreign key")
	private int us_id;

	public int getUs_com_id() {
		return us_com_id;
	}

	public void setUs_com_id(int us_com_id) {
		this.us_com_id = us_com_id;
	}

	public String getUs_com_comment() {
		return us_com_comment;
	}

	public void setUs_com_comment(String us_com_comment) {
		this.us_com_comment = us_com_comment;
	}

	public int getUs_id() {
		return us_id;
	}

	public void setUs_id(int us_id) {
		this.us_id = us_id;
	}
}
