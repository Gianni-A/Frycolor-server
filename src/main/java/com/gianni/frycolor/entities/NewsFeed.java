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
@Table(name = "news")
@ApiModel(description = "All details about the news feed of the user ")
public class NewsFeed {
	
	@Id
	@ApiModelProperty(notes = "The database generated news ID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "NW_ID", columnDefinition = "int")
	private int nw_id;
	
	@Column(name = "US_ID")
	private int us_id;
	
	@Column(name = "US_COMMENT_ID")
	private int us_comment_id;

	@Column(name = "US_MD_ID")
	@ApiModelProperty(notes = "The foreign key for the table user_media")
	private int us_md_id;

	public int getNw_id() {
		return nw_id;
	}

	public void setNw_id(int nw_id) {
		this.nw_id = nw_id;
	}

	public int getUs_id() {
		return us_id;
	}

	public void setUs_id(int us_id) {
		this.us_id = us_id;
	}

	public int getUs_comment_id() {
		return us_comment_id;
	}

	public void setUs_comment_id(int us_comment_id) {
		this.us_comment_id = us_comment_id;
	}

	public int getUs_md_id() {
		return us_md_id;
	}

	public void setUs_md_id(int us_md_id) {
		this.us_md_id = us_md_id;
	}

}
