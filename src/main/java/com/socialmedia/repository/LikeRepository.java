package com.socialmedia.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.socialmedia.entities.UserLike;

public interface LikeRepository extends JpaRepository<UserLike, Integer> {
	
	@Query(value = "select count(*) from user_like where post_id = ?",nativeQuery = true)
	public long countAllComment(int post_id);
	
	@Query(value = "select count(*) from user_like where post_id= ?",nativeQuery = true)
	public long countByPostId(int post_id);
}
