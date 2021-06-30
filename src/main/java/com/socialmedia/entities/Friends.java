package com.socialmedia.entities;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
@Entity
public class Friends {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@ManyToOne
	private User user;
	private String name;
	private String city;
	public enum Request_notification_status{
		No,Yes;
	}
	public enum Request_status{
		Panding,Confirm,Reject;
	}
	@Enumerated(EnumType.STRING)
	private Request_notification_status request_notification_status;
	@Enumerated(EnumType.STRING)
	private Request_status request_status;
	private int friendId;
	private String date_time;
	public Friends(int id, User user, Request_notification_status request_notification_status,
			Request_status request_status, int friendId, String date_time) {
		super();
		this.id = id;
		this.user = user;
		this.request_notification_status = request_notification_status;
		this.request_status = request_status;
		this.friendId = friendId;
		this.date_time = date_time;
	}
	public Friends() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Request_notification_status getRequest_notification_status() {
		return request_notification_status;
	}
	public void setRequest_notification_status(Request_notification_status request_notification_status) {
		this.request_notification_status = request_notification_status;
	}
	public Request_status getRequest_status() {
		return request_status;
	}
	public void setRequest_status(Request_status request_status) {
		this.request_status = request_status;
	}
	public int getFriendId() {
		return friendId;
	}
	public void setFriendId(int friendId) {
		this.friendId = friendId;
	}
	public String getDate_time() {
		return date_time;
	}
	public void setDate_time(String string) {
		this.date_time = string;
	}
	@Override
	public String toString() {
		return "Friends [id=" + id + ", user=" + user + ", request_notification_status=" + request_notification_status
				+ ", request_status=" + request_status + ", friendId=" + friendId + ", date_time=" + date_time + "]";
	}
	
	 
}
