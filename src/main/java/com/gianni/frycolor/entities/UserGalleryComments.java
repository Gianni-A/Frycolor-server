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
@Table(name = "user_gallery_comments")
@ApiModel(description = "All details about comments of the users on each (post, new, image)")
public class UserGalleryComments {
	
	@Id
	@ApiModelProperty(notes = "The database generated comment ID for each (post, new, image)")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "GA_COM_ID", columnDefinition = "int")
	private int ga_com_id;
	
	@Column(name = "GA_COM_GA_ID")
	private int ga_com_ga_id;
	
	@Column(name = "GA_COM_COMMENT")
	private String ga_com_comment;

	public int getGa_com_id() {
		return ga_com_id;
	}

	public void setGa_com_id(int ga_com_id) {
		this.ga_com_id = ga_com_id;
	}

	public int getGa_com_ga_id() {
		return ga_com_ga_id;
	}

	public void setGa_com_ga_id(int ga_com_ga_id) {
		this.ga_com_ga_id = ga_com_ga_id;
	}

	public String getGa_com_comment() {
		return ga_com_comment;
	}

	public void setGa_com_comment(String ga_com_comment) {
		this.ga_com_comment = ga_com_comment;
	}

}
