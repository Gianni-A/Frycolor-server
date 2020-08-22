package com.gianni.frycolor.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import org.hibernate.annotations.ColumnDefault;
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
	private int nwId;
	
	@ApiModelProperty(notes = "User ID from the table users_app (User.java)")
	@JoinColumn(name = "US_ID")
	@ManyToOne(fetch = FetchType.LAZY, targetEntity = User.class)
	private User usId;
	
	@ApiModelProperty(notes = "Comment ID from the table user_comments (UserComments.java)")
	@JoinColumn(name = "US_COMMENT_ID")
	@OneToOne(fetch = FetchType.LAZY, targetEntity = UserComments.class)
	private UserComments usCommentId;

	@ApiModelProperty(notes = "User Media ID, from the table user_media (UserMedia.java)")
	@JoinColumn(name = "US_MD_ID")
	@OneToOne(fetch = FetchType.LAZY, targetEntity = UserMedia.class)
	private UserMedia usMdId;
	
	@Column(name = "NW_STATUS")
	@ApiModelProperty(notes = "The status means if it is 1 = can show the post, 0 = the post is hide")
	private int nwStatus;
	
	@Column(name = "NW_TS_CREATED")
	private String nwTsCreated;
	
	@Column(name = "NW_TS_UPDATED")
	private String nwTsUpdated;

	public int getNwId() {
		return nwId;
	}

	public void setNwId(int nwId) {
		this.nwId = nwId;
	}

	public User getUsId() {
		return usId;
	}

	public void setUsId(User usId) {
		this.usId = usId;
	}

	public UserComments getUsCommentId() {
		return usCommentId;
	}

	public void setUsCommentId(UserComments usCommentId) {
		this.usCommentId = usCommentId;
	}

	public UserMedia getUsMdId() {
		return usMdId;
	}

	public void setUsMdId(UserMedia usMdId) {
		this.usMdId = usMdId;
	}

	public int getNwStatus() {
		return nwStatus;
	}

	public void setNwStatus(int nwStatus) {
		this.nwStatus = nwStatus;
	}

	public String getNwTsCreated() {
		return nwTsCreated;
	}

	public void setNwTsCreated(String nwTsCreated) {
		this.nwTsCreated = nwTsCreated;
	}

	public String getNwTsUpdated() {
		return nwTsUpdated;
	}

	public void setNwTsUpdated(String nwTsUpdated) {
		this.nwTsUpdated = nwTsUpdated;
	}

}
