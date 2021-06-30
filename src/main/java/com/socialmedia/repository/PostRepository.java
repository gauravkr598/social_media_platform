package com.socialmedia.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.socialmedia.entities.UserPost;

public interface PostRepository extends JpaRepository<UserPost, Integer>{
	@Query(value = "select  * from user_post where post_id= ?",nativeQuery = true)
	public List<UserPost> getPostByPostId(int post_id);
	@Query(value = "select * from user_post where user_user_id = ? ",nativeQuery = true)
	public List<UserPost> getPostByUser(int user_user_id);
	
	@Query(value = "select  * from user_post  order by  post_id desc",nativeQuery = true)
	public List<UserPost> getAllPostByDesc();
	
	@Query(value = "select user.image,user.name,user.username,user.gender,user.city,user.user_id,user.date_time,user.phone from user INNER JOIN user_post on user.user_id=user_post.user_user_id where post_id= ? ",nativeQuery = true)
	public Object getUserByPostId(int post_id);
}
