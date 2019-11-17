package com.gianni.frycolor.entities;


import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


@Entity
@Table(name = "users_app")
@ApiModel(description = "All details about the User. ")
public class User {
	
	@Id
	@ApiModelProperty(notes = "The database generated user ID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "US_ID", columnDefinition = "int")
	private int usId;
	
	@Column(name = "US_USER")
	private String usUser;
	
	@Column(name = "US_PASSWORD")
	private String usPassword;
	
	@Column(name = "US_EMAIL")
	//@Pattern(regexp = "/^(([^<>()\\[\\]\\\\.,;:\\s@\"]+(\\.[^<>()\\[\\]\\\\.,;:\\s@\"]+)*)|(\".+\"))@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$/")
	//@Pattern(regexp = "^[_A-Za-z0-9-\\\\+]+(\\\\.[_A-Za-z0-9-]+)*@\" + \"[A-Za-z0-9-]+(\\\\.[A-Za-z0-9]+)*(\\\\.[A-Za-z]{2,})$")
	private String usEmail;
	
	@Column(name = "US_STATUS")
	private int usStatus;
	
	@JoinColumn(name = "US_INF_ID")
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private UserInformation usInfId;

	public int getUsId() {
		return usId;
	}

	public void setUsId(int usId) {
		this.usId = usId;
	}

	public String getUsUser() {
		return usUser;
	}

	public void setUsUser(String usUser) {
		this.usUser = usUser;
	}

	public String getUsPassword() {
		return usPassword;
	}

	public void setUsPassword(String usPassword) {
		this.usPassword = usPassword;
	}

	public String getUsEmail() {
		return usEmail;
	}

	public void setUsEmail(String usEmail) {
		this.usEmail = usEmail;
	}

	public int getUsStatus() {
		return usStatus;
	}

	public void setUsStatus(int usStatus) {
		this.usStatus = usStatus;
	}

	public UserInformation getUsInfId() {
		return usInfId;
	}

	public void setUsInfId(UserInformation usInfId) {
		this.usInfId = usInfId;
	}

}
