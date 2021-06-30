package com.socialmedia.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.socialmedia.entities.Friends;

public interface FriendsRepository extends JpaRepository<Friends, Integer> {
	@Query(value = "select * from friends order by date_time desc",nativeQuery = true)
	public List<Friends> getAllFriendRequest();
}
