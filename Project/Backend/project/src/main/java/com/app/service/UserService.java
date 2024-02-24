package com.app.service;

import com.app.Exception.UserException;
import com.app.model.User;

public interface UserService {

	public User findUserBy(int id)throws UserException;
	
	public User findUserProfileByJwt(String jwt)throws UserException;
	
	public User findUserByEmail(String email)throws UserException;
}
