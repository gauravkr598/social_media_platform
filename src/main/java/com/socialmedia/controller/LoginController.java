package com.socialmedia.controller;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.Principal;
import java.text.SimpleDateFormat;
import java.time.OffsetTime;
import java.time.temporal.ChronoField;
import java.util.Calendar;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import com.socialmedia.message.Message;
import com.socialmedia.entities.Friends;
import com.socialmedia.entities.Friends.Request_status;
import com.socialmedia.entities.User;
import com.socialmedia.entities.UserLike;
import com.socialmedia.entities.UserPost;
import com.socialmedia.repository.UserRepository;
import com.socialmedia.service.UserService;

@Controller
@RequestMapping("/user")
public class LoginController {

	@Autowired
	private BCryptPasswordEncoder PasswordEncoder;

	@Autowired
	private UserService userService;
	
	@Autowired
	private UserRepository userRepository;

	@RequestMapping("/user-dashbord")
	public String oprnDashbord(Model m, Principal principal, HttpServletResponse response) {
		String username = principal.getName();
		System.out.println(username);
		User user = this.userRepository.getUserByUsername(username);
		m.addAttribute("user", user);
		List<UserPost> allPost = this.userService.getAllPost();
		m.addAttribute("AllPost", allPost);
		List<Friends> allRequest = this.userService.getAllRequest();
		m.addAttribute("Request", allRequest);
		return "user-login/login-dashbord";
	}

	@PostMapping("/post")
	public String userPost(@ModelAttribute UserPost userPost, @RequestParam("postImage") MultipartFile postImage,
			Principal principal, HttpSession session) {
		try {
			OffsetTime time = OffsetTime.now();
			String username = principal.getName();
			System.out.println(username);
			User user = this.userRepository.getUserByUsername(username);
			userPost.setUser(user);
			// file uploade
			// uploading Image
			File file = new ClassPathResource("static/image").getFile();
			Path path = Paths.get(file.getAbsolutePath() + File.separator + time.get(ChronoField.SECOND_OF_DAY)
					+ postImage.getOriginalFilename());
			System.out.println("File Path" + path);
			Files.copy(postImage.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
			System.out.println("File Uploaded..");
			// save file
			userPost.setImage(time.get(ChronoField.SECOND_OF_DAY) + postImage.getOriginalFilename());
			this.userService.savePost(userPost);
			session.setAttribute("message", new Message("Your Post Successfully Uploades.", "alert-success"));
			return "redirect:/user/user-dashbord";
		} catch (Exception e) {
			e.printStackTrace();
			session.setAttribute("message", new Message("Post Not Uploaded.", "alert-danger"));
			return "redirect:/user/user-dashbord";
		}

	}

	// save like
	@RequestMapping("/LikeServlet")
	@ResponseBody
	public long saveLike(@ModelAttribute UserLike userLike, Model m) {
		System.out.println(userLike.getPostId()+userLike.getLikeId()+userLike.getUserId());
		long countComment = 0;
		try {
			this.userService.saveComment(userLike);
			long findAllComment = this.userService.findAllComment(userLike.getPostId());			
			countComment = this.userService.countComment(userLike.getPostId());	
			System.out.println("Post : " + findAllComment);
			m.addAttribute("countComment", findAllComment);
			return countComment;
		} catch (Exception e) {
			return countComment;
		}

	}
	@RequestMapping("/like-details/{postId}")
	public String likeDetails(@PathVariable("postId") int postId, Model m, UserPost userPost) {
		System.out.println("post Id : " + postId);
		long findAllComment = this.userService.findAllComment(postId);
		System.out.println("Post : " + findAllComment);
		m.addAttribute("countComment", findAllComment);
		
		List<UserPost> post = this.userService.getPostById(postId);
		m.addAttribute("post", post);
		
		Object userByPostId = this.userService.getUserByPostId(postId);
		m.addAttribute("userByPostId",userByPostId);
		System.out.println("user id : " + userByPostId);
		return "user-login/like-dashbord";
	}

	@PostMapping("/user-details")
	@ResponseBody
	public User viewUserDetails(Principal principal) {
		String name = principal.getName();
		User user = this.userRepository.getUserByUsername(name);
		return user;
	}

	// user -setting
	@RequestMapping("/user-setting")
	public String updateUser(Model m, Principal principal) {
		String name = principal.getName();
		User user = this.userRepository.getUserByUsername(name);
		m.addAttribute("user", user);
		return "user-login/user-update";
	}

	@PostMapping("/update-user-details")
	public String updateUserDetails(@ModelAttribute User user, @RequestParam("profileImage") MultipartFile profileImage,
			Principal principal, HttpSession session) {
		String name = principal.getName();
		OffsetTime time = OffsetTime.now();
		User oldUser = this.userRepository.getUserByUsername(name);
		try {
			if (!profileImage.isEmpty()) {
				// old image delete
				File deletefile = new ClassPathResource("static/image").getFile();
				File file1 = new File(deletefile, oldUser.getImage());
				file1.delete();
				// update new imagge
				File savefile = new ClassPathResource("static/image").getFile();
				Path path = Paths.get(savefile.getAbsolutePath() + File.separator + time.get(ChronoField.SECOND_OF_DAY)
						+ profileImage.getOriginalFilename());
				System.out.println(path);
				Files.copy(profileImage.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
				user.setImage(+time.get(ChronoField.SECOND_OF_DAY) + profileImage.getOriginalFilename());

			} else {
				user.setImage(oldUser.getImage());
			}
			if (user.getCity() == null) {
				user.setCity(oldUser.getCity());
			}
			if (user.getPhone() == null) {
				user.setPhone(oldUser.getPhone());
			}
			if (user.getGender() == null) {
				user.setGender(oldUser.getGender());
			}
			if (user.getName() == null) {
				user.setName(oldUser.getName());
			}
			user.setPassword(oldUser.getPassword());
			user.setRole("ROLE_USER");
			user.setUsername(oldUser.getUsername());
			user.setUserId(oldUser.getUserId());
			this.userService.save(user);
			session.setAttribute("message", new Message("User details Successfully updated.", "success"));
		} catch (Exception e) {
			e.printStackTrace();
			session.setAttribute("message", new Message("User details Not updated.", "danger"));
		}
		return "redirect:/user/user-setting";

	}

	// change password
	@RequestMapping("/change")
	public String changePassword() {

		return "user-password/changePassword";
	}
	/*
	 * @PostMapping("/checkPassword")
	 * 
	 * @ResponseBody public String changePassword(@RequestParam("oldPassword")
	 * String oldPassword,Principal principal,HttpSession session,Model m) {
	 * System.out.println(oldPassword); String userName = principal.getName(); User
	 * user = this.userRepository.getUserByUsername(userName);
	 * if(this.PasswordEncoder.matches(oldPassword, user.getPassword())) {
	 * session.setAttribute("message",new
	 * Message("passwoerd successfully changed.","alert-success")); return
	 * "redirect:/user/change"; }else {
	 * m.addAttribute("passwordMissmatch","please enter a valid password");
	 * session.setAttribute("message",new
	 * Message("passwoerd Not changed.","alert-danger")); return
	 * "redirect:/user/change"; } }
	 */

	@PostMapping("/checkPassword")
	public String changePassword(@RequestParam("oldPassword") String oldPassword,
			@RequestParam("newPassword") String newPassword, Principal principal, HttpSession session, Model m) {
		System.out.println(oldPassword);
		System.out.println(newPassword);
		String userName = principal.getName();
		User user = this.userRepository.getUserByUsername(userName);
		if (this.PasswordEncoder.matches(oldPassword, user.getPassword())) {
			user.setPassword(this.PasswordEncoder.encode(newPassword));
			this.userRepository.save(user);
			session.setAttribute("message", new Message("passwoerd successfully changed.", "alert-success"));
			return "redirect:/user/change";
		} else {
			m.addAttribute("passwordMissmatch", "please enter a valid password");
			session.setAttribute("message", new Message("passwoerd not changed.", "alert-danger"));
			return "redirect:/user/change";
		}
	}

	// view full Image
	@PostMapping("/viewImage")
	@ResponseBody
	public String viewImage(Principal principal) {
		String name = principal.getName();
		User user = this.userRepository.getUserByUsername(name);
		return user.getImage();
	}

	// search user=======================
	@GetMapping("/search/{query}")
	@ResponseBody
	public List<User> searchHander(@PathVariable("query") String query, Principal principal) {
		System.out.println(query);
		principal.getName();
		List<User> user = this.userRepository.findByNameContaining(query);
		return user;
	}

	@RequestMapping("/search-user/{userId}")
	public String openSearch(@PathVariable("userId") int userId, Model m, Principal principal) {
		List<User> findById = this.userRepository.getuserById(userId);
		m.addAttribute("searchUser", findById);
		String username = principal.getName();
		User user = this.userRepository.getUserByUsername(username);
		m.addAttribute("user", user);
		List<UserPost> AllPost = this.userService.getUserPostByUser(userId);
		m.addAttribute("AllPost", AllPost);
		return "user-login/user-search";
	}

	// send Request
	@GetMapping("/sendrequest/{friendId}")
	@ResponseBody
	public int sendRequestSave(@ModelAttribute Friends friends, @PathVariable("friendId") int friendId,
			Principal principal) {
		System.out.println(friendId);
		String name = principal.getName();
		User user = this.userRepository.getUserByUsername(name);
		friends.setFriendId(friendId);
		friends.setUser(user);
		Request_status status = friends.getRequest_status().Confirm;
		friends.setRequest_status(status);
		friends.setRequest_notification_status(friends.getRequest_notification_status().No);
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat simpleformat = new SimpleDateFormat("dd MMMM yyyy hh:mm");
		friends.setDate_time(simpleformat.format(cal.getTime()));
		this.userService.saveRequest(friends);
		return friendId;
	}

}
