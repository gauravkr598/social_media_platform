package com.socialmedia.entities;


import java.sql.Timestamp;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
@Entity
public class UserPost {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int postId;
	private String postTitle;
	private String image;
	@CreationTimestamp
	@Column(name="date_time", columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	private Timestamp date_time;
	
	@ManyToOne
	@JsonIgnore
	private User user;
	public UserPost(int postId, String postTitle, String image, User user) {
		super();
		this.postId = postId;
		this.postTitle = postTitle;
		this.image = image;
		this.user = user;
	}
	public UserPost() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getPostId() {
		return postId;
	}
	public void setPostId(int postId) {
		this.postId = postId;
	}
	public String getPostTitle() {
		return postTitle;
	}
	public void setPostTitle(String postTitle) {
		this.postTitle = postTitle;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	public Timestamp getDate_time() {
		return date_time;
	}
	public void setDate_time(Timestamp date_time) {
		this.date_time = date_time;
	}
	@Override
	public String toString() {
		return "UserPost [postId=" + postId + ", postTitle=" + postTitle + ", image=" + image + ", user=" + user + "]";
	}
	
}
