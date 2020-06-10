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
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import org.springframework.stereotype.Component;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import static com.gianni.frycolor.util.Constantes.EMAIL_PATTERN;;


@Entity
@Component
@Table(name = "users_app")
@ApiModel(description = "All details about the User. ")
public class User {
	
	@Id
	@ApiModelProperty(notes = "The database generated user ID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "US_ID", columnDefinition = "int")
	private int usId;
	
	@Column(name = "US_USER")
	@NotEmpty(message = "User should not be empty")
	private String usUser;
	
	@Column(name = "US_PASSWORD")
	@NotEmpty(message = "Password should not be empty")
	private String usPassword;
	
	@NotEmpty
	@Column(name = "US_EMAIL")
	@Pattern(regexp = EMAIL_PATTERN, message = "The email format is invalid")
	private String usEmail;
	
	@Column(name = "US_STATUS")
	private int usStatus;
	
	@JoinColumn(name = "US_INF_ID")
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private UserInformation usInfId;
	
	@Column(name = "US_TS_CREATED")
	private String usTsCreated;
	
	@Column(name = "US_TS_UPDATED")
	private String usTsUpdated;
	

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

	public String getUsTsCreated() {
		return usTsCreated;
	}

	public void setUsTsCreated(String usTsCreated) {
		this.usTsCreated = usTsCreated;
	}

	public String getUsTsUpdated() {
		return usTsUpdated;
	}

	public void setUsTsUpdated(String usTsUpdated) {
		this.usTsUpdated = usTsUpdated;
	}

}
