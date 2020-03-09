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
@Table(name = "news_reaction")
@ApiModel(description = "All details about the reactions of the people about the news (likes, emojis, etc)")
public class NewsReaction {
	
	@Id
	@ApiModelProperty(notes = "The database generated reaction news ID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "NWR_ID", columnDefinition = "int")
	private int nwr_id;
	
	@Column(name = "US_ID")
	private int us_id;
	
	@Column(name = "NW_ID")
	private int nw_id;

	public int getNwr_id() {
		return nwr_id;
	}

	public void setNwr_id(int nwr_id) {
		this.nwr_id = nwr_id;
	}

	public int getUs_id() {
		return us_id;
	}

	public void setUs_id(int us_id) {
		this.us_id = us_id;
	}

	public int getNw_id() {
		return nw_id;
	}

	public void setNw_id(int nw_id) {
		this.nw_id = nw_id;
	}
}
