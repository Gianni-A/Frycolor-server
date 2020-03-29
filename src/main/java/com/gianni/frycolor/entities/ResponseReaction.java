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
@Table(name = "response_reaction")
@ApiModel(description = "All details about the reactions of the people about the responses (likes)")
public class ResponseReaction {
	
	@Id
	@ApiModelProperty(notes = "The database generated reaction response ID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "RR_ID", columnDefinition = "int")
	private int rrId;
	
	@Column(name = "US_ID")
	private int usId;
	
	@Column(name = "NW_RES_ID")
	private int nwResId;
	
	@Column(name = "RR_TS_CREATED")
	private String rrTsCreated;

	public int getRrId() {
		return rrId;
	}

	public void setRrId(int rrId) {
		this.rrId = rrId;
	}

	public int getUsId() {
		return usId;
	}

	public void setUsId(int usId) {
		this.usId = usId;
	}

	public int getNwResId() {
		return nwResId;
	}

	public void setNwResId(int nwResId) {
		this.nwResId = nwResId;
	}

	public String getRrTsCreated() {
		return rrTsCreated;
	}

	public void setRrTsCreated(String rrTsCreated) {
		this.rrTsCreated = rrTsCreated;
	}

}
