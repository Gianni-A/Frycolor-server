package com.gianni.frycolor.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;

import org.springframework.stereotype.Component;

@Entity
@Component
@Table(name = "user_information")
public class UserInformation {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "US_INF_ID")
	private int usInfId;
	
	@NotEmpty(message = "the name is empty")
	@Column(name = "US_INF_NAME")
	private String usInfName;
	
	@Column(name = "US_INF_LASTNAME")
	private String usInfLastname;
	
	@Column(name = "US_INF_BIRTHDAY")
	@Temporal(TemporalType.TIMESTAMP)
	private Date usInfBirthday;
	
	@Column(name = "US_INF_PATH_IMAGE")
	private String usInfPath_image;
	
	@Column(name = "US_INF_CITY")
	private String usInfCity;
	
	@Column(name = "US_INF_STATE")
	private String usInfState;
	
	@Column(name = "US_INF_COUNTRY")
	private String usInfCountry;

	public int getUsInfId() {
		return usInfId;
	}

	public void setUsInfId(int usInfId) {
		this.usInfId = usInfId;
	}

	public String getUsInfName() {
		return usInfName;
	}

	public void setUsInfName(String usInfName) {
		this.usInfName = usInfName;
	}

	public String getUsInfLastname() {
		return usInfLastname;
	}

	public void setUsInfLastname(String usInfLastname) {
		this.usInfLastname = usInfLastname;
	}

	public Date getUsInfBirthday() {
		return usInfBirthday;
	}

	public void setUsInfBirthday(Date usInfBirthday) {
		this.usInfBirthday = usInfBirthday;
	}

	public String getUsInfPath_image() {
		return usInfPath_image;
	}

	public void setUsInfPath_image(String usInfPath_image) {
		this.usInfPath_image = usInfPath_image;
	}

	public String getUsInfCity() {
		return usInfCity;
	}

	public void setUsInfCity(String usInfCity) {
		this.usInfCity = usInfCity;
	}

	public String getUsInfState() {
		return usInfState;
	}

	public void setUsInfState(String usInfState) {
		this.usInfState = usInfState;
	}

	public String getUsInfCountry() {
		return usInfCountry;
	}

	public void setUsInfCountry(String usInfCountry) {
		this.usInfCountry = usInfCountry;
	}

}
