package com.notes.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.notes.DAO.NoteRepository;
import com.notes.DAO.UserRepository;
import com.notes.Model.User;

@Controller
public class MainController {
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private NoteRepository noteRepository;
	
	@GetMapping("/")
	public String home(Model model) {
		model.addAttribute("title", "Notes App");
		return "PageHome";
	}
	
	@GetMapping("/register")
	public String register() {
		return "register";
	}
	
	@PostMapping("/register")
	public String registerHandler(@ModelAttribute User user) {
//		System.out.println(user);
		user.setPassword(this.passwordEncoder.encode(user.getPassword()));
		user.setRole("ROLE_USER");
		this.userRepository.save(user);
		return "redirect:/";
	}
	
	@GetMapping("/login")
	public String login() {
		return "login";
	}
}
