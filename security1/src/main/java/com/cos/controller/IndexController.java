package com.cos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
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
	public @ResponseBody String user() {
		return "user";
	}
	@GetMapping("/admin")
	public @ResponseBody String admin() {
		return "admin";
	}
	@GetMapping("/manager")
	public @ResponseBody String manager() {
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
		return "joinproc";
	}
	@GetMapping("/loginProc")
	public String loginProc() {
		return "loginProc";
	}
	@Secured("ROLE_ADMIN")//접근가능한 권한설정
	@GetMapping("/info")
	public @ResponseBody String info() {
		return "개인정보";
	}
	@PreAuthorize("hasRole('ROLE_MANAGER')or hasRole('ROLE_ADMIN')")//실행되기 직전에 실행
	//@PostAuthorize()//함수가 끝나고 난뒤에 실행
	@GetMapping("/data")
	public @ResponseBody String data() {
		return "데이터정보";
	}
}
