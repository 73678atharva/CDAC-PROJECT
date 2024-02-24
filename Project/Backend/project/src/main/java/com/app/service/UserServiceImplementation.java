package com.app.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.app.Exception.UserException;
import com.app.config.JwtProvider;
import com.app.model.User;
import com.app.repositry.UserRepository;

@Service
public class UserServiceImplementation implements UserService{

	private UserRepository userRepository;
	private JwtProvider jwtProvider;
	
	public UserServiceImplementation(UserRepository userRepository, JwtProvider jwtProvider) {
		this.userRepository = userRepository;
		this.jwtProvider = jwtProvider;
	}
	
	@Override
	public User findUserBy(int id) throws UserException {
	
		Optional<User> user = userRepository.findById(id);
		if(user.isPresent()) {
			return user.get();
		}
		
		throw new UserException("User Not Found with id: "+ id);
	}

	@Override
	public User findUserProfileByJwt(String jwt) throws UserException {
	
		String email = jwtProvider.getEmailFromToken(jwt);
		
		User user = userRepository.findByEmail(email);
		if(user==null) {
			throw new UserException("User not found with email: "+ email);
		}
		return user;
	}

	@Override
	public User findUserByEmail(String email) throws UserException {
		return userRepository.findByEmail(email);
		 
	}

}
