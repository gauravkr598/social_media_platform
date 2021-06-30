package com.socialmedia.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.socialmedia.entities.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	@Query(value = "select count(*) from user where username = ?",nativeQuery = true)
	public long checkEmail(String username);
	
	@Query("select u from User u where u.username = :username")
	public User getUserByUsername(@Param("username") String username);
	@Query(value = "select * from user where name Like ' %'",nativeQuery = true)
	public List<User> getuserBykey(String query);

	public List<User> findByNameContaining(String query);
	@Query(value = "select * from user where user_id = ? ",nativeQuery = true)
	public List<User> getuserById(int userId);
}
