package com.socialmedia.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import com.socialmedia.entities.User;


public class UserDetailsServiceImpl implements UserDetailsService{
	@Autowired
	private com.socialmedia.repository.UserRepository userRepository;


	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.getUserByUsername(username);
		if(user==null) {
			throw new UsernameNotFoundException("Could Not found User");
		}
		CoustumUserDetais userDetails=new CoustumUserDetais(user);
		return userDetails;
	}

}
