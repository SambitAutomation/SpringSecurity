package com.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.entity.JwtRequest;
import com.spring.entity.JwtResponse;
import com.spring.security.JwtHelper;

@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	UserDetailsService userDetailsService;
	
	@Autowired
	AuthenticationManager manager;
	
	@Autowired
	JwtHelper helper;
	
	@PostMapping("/login")
	public ResponseEntity<JwtResponse> authentication(@RequestBody JwtRequest jwtRequest){
		this.doAuthentication(jwtRequest.getUsername(), jwtRequest.getPassword());
		
		UserDetails userDetails = userDetailsService.loadUserByUsername(jwtRequest.getUsername());
		String token = helper.generateToken(userDetails);
		JwtResponse response = new JwtResponse();
		response.setToken(token);
		response.setUsername(userDetails.getUsername());
		
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	public void doAuthentication(String username, String password) {
		UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(username, password);
		try {
			manager.authenticate(authentication);
		}catch (BadCredentialsException e) {
			throw new BadCredentialsException("Invalid Credentiual !!");
		}
	}
}
