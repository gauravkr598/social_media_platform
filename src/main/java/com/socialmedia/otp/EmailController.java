package com.socialmedia.otp;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.socialmedia.repository.UserRepository;

@Controller
public class EmailController {
	Random random = new Random(1000);
	@Autowired
	private EmailService emailService;
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@GetMapping("/forgot")
	public String openEmailForm() {
		
		return "email-otp/forgot-password";
	}

}
