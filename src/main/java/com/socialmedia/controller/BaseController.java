package com.socialmedia.controller;

import java.util.Random;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.socialmedia.entities.User;
import com.socialmedia.message.Message;
import com.socialmedia.otp.EmailService;
import com.socialmedia.service.UserService;

@Controller
public class BaseController {
	
	@Autowired
	private BCryptPasswordEncoder PasswordEncoder;
	
	@Autowired
	private UserService userService;
	
	Random random = new Random(1000);
	@Autowired
	private EmailService emailService;
	
	@RequestMapping("/")
	public String openSignupDashbord(Model m) {
		m.addAttribute("title","user-dashbord");
		System.out.println("hello ");
		return "user-dashbord/signup-dashbord";
		}
//user login page
		@RequestMapping("/signin")
		public String openLoginPage(Model m) {
		m.addAttribute("title","user login page");
			return "user-login/login";
		}
		
//check email
	@PostMapping("/checkEmail")
	@ResponseBody
	public String checkEmail(@ModelAttribute User user) {
		try {
			long count = this.userService.count(user.getUsername());
			System.out.println("User Count is."+count);
			if(count==0) {
				return "#@#notCount#@#This Email Is Available.#@#"; 
			}else {
				return "#@#success#@#This Email Is Already Exist.#@#"; 
			}
		}catch (Exception e) {
			return "#@#failure#@#+e.printStackTrace()+.#@#"; 
		}
	}
//register form submit		
		@PostMapping("/submit-form")
		public String saveUser(@ModelAttribute User user,Model m,HttpSession session) {
			System.out.println("hello user");
			try {
				System.err.println(user.getGender()); 
				if(user.getGender().equals("Male")==true){
					user.setImage("default.png"); }
				else {
					   user.setImage("defaultFemale.png"); 
					}
					user.setRole("ROLE_USER");
					user.setPassword(PasswordEncoder.encode(user.getPassword()));
					
					// send otp============================================================= 
					String email = user.getUsername();
					System.out.println("Email  : " + email);
					
					//generating otp.
					int otp = random.nextInt(99999);
					System.out.println("OTP : "  +otp);
					String subject="OTP Request";
					String message=""
									+"<div style='border:1px solid blue; padding=20px'>"
									+"Dear Customer,"
									+"<div style='border:1px solid blue; padding=20px'>"				 
									+"Your OTP is :  &nbsp;&nbsp;"  +  otp  +"&nbsp;&nbsp; Do not share it with anyone by any means. This is confidential and to be used by you only."
									+"</div>"
									+"Warm regards,\r\n"
									+ "Social Media Platform"
									+"</div>";
					String to=email;
					boolean flag = this.emailService.sendemail(message,subject,to);
					System.out.println(flag);
					if(flag) {
						session.setAttribute("myotp", otp);
						session.setAttribute("email",email);
						session.setAttribute("message", new Message("OTP Successfully send your email.","alert-success"));
						session.setAttribute("present-user", user);
						 return "email-otp/register-otp";
					}else {
						session.setAttribute("message", new Message("OTP Not send Something went wrong.","alert-success"));
						session.setAttribute("present-user", user);
						 return "email-otp/register-otp";
					}
					   
				}catch (Exception e) {
					e.printStackTrace(); 
					return "email-otp/register-otp"; 
				}
			}	
// verify otp
		@PostMapping("/verify-otp")
		public String otpVerify(@RequestParam("otp") int otp,HttpSession session){
			System.out.println(otp);
			int myOtp=(int) session.getAttribute("myotp");
			System.out.println(myOtp);
			String myEmail=(String) session.getAttribute("email");
			System.out.println(myEmail);
			if(myOtp==otp) {
				User user = (User) session.getAttribute("present-user");
				System.out.println("User Details is : " + user);
				if(user!=null) {
					
					
					this.userService.save(user);
					session.setAttribute("message", new Message("User Successfully Register","alert-successs"));
					return "email-otp/register-otp";
				}
			}else {
				session.setAttribute("message", new Message("OTP Not  Varify Please Enter valid OTP","alert-danger"));
				return "email-otp/register-otp"; 
			}
			session.setAttribute("message", new Message("User Not Register","alert-danger"));
			return "email-otp/register-otp";
		}

//Login time 
	@RequestMapping("/checkpassword")
	public String checkEmailAndPassword(HttpSession session) {
		session.setAttribute("message", new Message("Please check your email and password.","alert-danger"));
		return "redirect:/user/signin";
	}
}
