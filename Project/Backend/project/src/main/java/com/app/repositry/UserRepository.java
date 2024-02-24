package com.app.repositry;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.app.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {

    @Query("SELECT u FROM User u WHERE u.email = :email")
    public User findByEmail(@Param("email") String email);
    
}

//	public interface UserRepository extends JpaRepository<User, Integer>{
//		
//		//@Query("SELECT u from User u WHERE u.email =: email")
//		public User findByEmail(String email);

