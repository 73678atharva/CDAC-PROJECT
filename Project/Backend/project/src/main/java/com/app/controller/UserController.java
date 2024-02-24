package com.app.controller;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.Exception.*;
import com.app.config.JwtProvider;
import com.app.model.User;
import com.app.repositry.UserRepository;
import com.app.request.LoginRequest;
import com.app.response.AuthResponse;
import com.app.service.CustomeUserServiceImplementation;
import com.app.service.UserService;


@RestController
@RequestMapping("/user")
public class UserController{
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/profile")
	public ResponseEntity<User> getUserProfileHandler(@RequestHeader("Authorization")String jwt) throws  UserException{
		
		User user = userService.findUserProfileByJwt(jwt);
		
		return new ResponseEntity<User>(user,HttpStatus.ACCEPTED);
	}
}
