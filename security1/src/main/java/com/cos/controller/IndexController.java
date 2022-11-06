package com.cos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cos.model.User;
import com.cos.repository.UserRepository;

@Controller
public class IndexController {
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@GetMapping({"","/"})
	public String index() {
		return "index";
	}
	@GetMapping("/user")
	public String user() {
		return "user";
	}
	@GetMapping("/admin")
	public String admin() {
		return "admin";
	}
	@GetMapping("/manager")
	public String manager() {
		return "manager";
	}
	@GetMapping("/login")
	public String login() {
		return "login";
	}
	@GetMapping("/loginForm")
	public String loginForm() {
		return "loginForm";
	}
	@PostMapping("/join")
	public String join(User user) {
		user.setRole("ROLE_USER");
		String rawPassword = user.getPassword();
		String encPassword= bCryptPasswordEncoder.encode(rawPassword);
		user.setPassword(encPassword);
		userRepository.save(user);
		return "redirect:/loginForm";
	}
	@GetMapping("/joinForm")
	public String joinForm() {
		return "joinForm";
	}
	@GetMapping("/join")
	public String join(){
		return "join";
	}

}
