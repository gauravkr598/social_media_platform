
package com.socialmedia.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.socialmedia.entities.Friends;
import com.socialmedia.entities.User;
import com.socialmedia.entities.UserLike;
import com.socialmedia.entities.UserPost;
import com.socialmedia.repository.FriendsRepository;
import com.socialmedia.repository.LikeRepository;
import com.socialmedia.repository.PostRepository;
import com.socialmedia.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PostRepository postRepository;
	
	@Autowired
	private LikeRepository likeRepository;
	
	@Autowired
	private FriendsRepository friendsRepository;
	
	public User save(User user) {
		
		return this.userRepository.save(user);
	}
	
	public long count(String username) {
		
		return this.userRepository.checkEmail(username);
	}
	
	public UserPost savePost(UserPost userPost) {
		
		return this.postRepository.save(userPost);
	}
	 
	public List<UserPost> getAllPost() {
		
		return this.postRepository.getAllPostByDesc();
	}
	public void saveComment(UserLike userLike) {
		 
		this.likeRepository.save(userLike);
	}
	public long countComment(int post_id){
		
		return this.likeRepository.countAllComment(post_id);
	}
	public long findAllComment(int post_id) {
		
		return this.likeRepository.countByPostId(post_id);
	}
	
	public List<UserPost> getPostById(int post_id) {
		
		return this.postRepository.getPostByPostId(post_id);
	}
	//save request
	public Friends saveRequest(Friends friends) {
		
		return this.friendsRepository.save(friends);
	}
	//get user by user Id
	public List<UserPost> getUserPostByUser(int userId) {
		
		return this.postRepository.getPostByUser(userId);
	}
	//all friend Request 
	public List<Friends> getAllRequest() {
		
		return this.friendsRepository.getAllFriendRequest();
	}
	//get user by post id 
	public Object getUserByPostId(int post_id) {
		
		return this.postRepository.getUserByPostId(post_id);
	}
}
