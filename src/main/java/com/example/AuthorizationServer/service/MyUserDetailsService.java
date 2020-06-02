package com.example.AuthorizationServer.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.AuthorizationServer.models.MyUserDetails;
import com.example.AuthorizationServer.models.User;
import com.example.AuthorizationServer.repository.UserRepository;

@Service
public class MyUserDetailsService implements UserDetailsService {

	@Autowired
	UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

		Optional<User> user = userRepository.findByUserName(userName);

		try {
			user.orElseThrow(() -> new UsernameNotFoundException("Not found: " + userName));

		} catch (Exception e) {
			System.out.println("userName Not found" + e);
		}
		return user.map(MyUserDetails::new).get();
	}

}
