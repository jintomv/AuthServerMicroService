package com.example.AuthorizationServer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.AuthorizationServer.models.User;
import com.example.AuthorizationServer.repository.UserRepository;

@RestController
public class UserController {

	@Autowired
	UserRepository repository;
	
	@Autowired
	PasswordEncoder encoder;
	
//	@PostMapping("/save")
//	public User create(@RequestBody User res) {
//		
//		res.setPassword(encoder.encode(res.getPassword()));
//		return repository.save(res);
//	}
	
	
	@GetMapping("/save")
	public User create() {
		User user = new User();
		user.setUserName("manage");
		user.setPassword(encoder.encode("manage"));
		user.setRoles("MANAGE_USER");
		user.setActive(true);
		return repository.save(user);
	}
	
}
