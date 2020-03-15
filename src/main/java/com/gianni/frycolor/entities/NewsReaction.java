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
	private int nwrId;
	
	@Column(name = "US_ID")
	private int usId;
	
	@Column(name = "NW_ID")
	private int nwId;
	
	@Column(name = "NWR_TS_CREATED")
	private String nwrTsCreated;
	
	@Column(name = "NWR_TS_UPDATED")
	private String nwrTsUpdated;

	public int getNwrId() {
		return nwrId;
	}

	public void setNwrId(int nwrId) {
		this.nwrId = nwrId;
	}

	public int getUsId() {
		return usId;
	}

	public void setUsId(int usId) {
		this.usId = usId;
	}

	public int getNwId() {
		return nwId;
	}

	public void setNwId(int nwId) {
		this.nwId = nwId;
	}

	public String getNwrTsCreated() {
		return nwrTsCreated;
	}

	public void setNwrTsCreated(String nwrTsCreated) {
		this.nwrTsCreated = nwrTsCreated;
	}

	public String getNwrTsUpdated() {
		return nwrTsUpdated;
	}

	public void setNwrTsUpdated(String nwrTsUpdated) {
		this.nwrTsUpdated = nwrTsUpdated;
	}
}
