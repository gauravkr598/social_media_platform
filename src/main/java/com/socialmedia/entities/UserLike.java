package com.socialmedia.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Entity
public class UserLike {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int likeId;
	private int postId;
	private int userId;
	public int getLikeId() {
		return likeId;
	}
	public void setLikeId(int likeId) {
		this.likeId = likeId;
	}
	public int getPostId() {
		return postId;
	}
	public void setPostId(int postId) {
		this.postId = postId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public UserLike(int likeId, int postId, int userId) {
		super();
		this.likeId = likeId;
		this.postId = postId;
		this.userId = userId;
	}
	public UserLike() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "UserLike [likeId=" + likeId + ", postId=" + postId + ", userId=" + userId + "]";
	}
	
}
