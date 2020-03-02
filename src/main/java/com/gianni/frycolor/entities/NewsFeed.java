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
	
	@Column(name = "NW_US_ID")
	private int nw_us_id;
	
	@Column(name = "NW_COMMENT")
	private String nw_comment;
	
	@Column(name = "NW_PATH_IMG_ID")
	private int nw_path_img_id;

	public int getNw_id() {
		return nw_id;
	}

	public void setNw_id(int nw_id) {
		this.nw_id = nw_id;
	}

	public int getNw_us_id() {
		return nw_us_id;
	}

	public void setNw_us_id(int nw_us_id) {
		this.nw_us_id = nw_us_id;
	}

	public String getNw_comment() {
		return nw_comment;
	}

	public void setNw_comment(String nw_comment) {
		this.nw_comment = nw_comment;
	}

	public int getNw_path_img_id() {
		return nw_path_img_id;
	}

	public void setNw_path_img_id(int nw_path_img_id) {
		this.nw_path_img_id = nw_path_img_id;
	}

}
