package com.socialmedia.entities;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonFormat;
@Entity
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int userId;
	private String name;
	@Column(unique = true)
	private String username;
	private String password;
	private String gender;
	private String image;
	private Long phone;
	private String city;
	private String role;
	@CreationTimestamp
	@Column(name="date_time", columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	private Timestamp date_time;
	@OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY,mappedBy = "user")
	private List<UserPost> userPost=new ArrayList<UserPost>();
	public User(int userId, String name, String username, String password, String gender, String image, Long phone,
			String city, String role, Timestamp date_time) {
		super();
		this.userId = userId;
		this.name = name;
		this.username = username;
		this.password = password;
		this.gender = gender;
		this.image = image;
		this.phone = phone;
		this.city = city;
		this.role = role;
		this.date_time = date_time;
	}
	@Override
	public String toString() {
		return "User [userId=" + userId + ", name=" + name + ", username=" + username + ", password=" + password
				+ ", gender=" + gender + ", image=" + image + ", phone=" + phone + ", city=" + city + ", role=" + role
				+ ", date_time=" + date_time + ", userPost=" + userPost + "]";
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public Long getPhone() {
		return phone;
	}
	public void setPhone(Long phone) {
		this.phone = phone;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public Timestamp getDate_time() {
		return date_time;
	}
	public void setDate_time(Timestamp date_time) {
		this.date_time = date_time;
	}
	public List<UserPost> getUserPost() {
		return userPost;
	}
	public void setUserPost(List<UserPost> userPost) {
		this.userPost = userPost;
	}
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	 
}
