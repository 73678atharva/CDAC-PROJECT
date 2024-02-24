package com.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.Exception.ProductException;
import com.app.Exception.UserException;
import com.app.model.Ratings;
import com.app.model.User;
import com.app.request.RatingRequest;
import com.app.service.RatingService;
import com.app.service.UserService;

@RestController
@RequestMapping("/ratings")
public class RatingController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private RatingService ratingService;
	
	@PostMapping("/create")
	public ResponseEntity<Ratings> createRating(@RequestBody RatingRequest req,
			@RequestHeader("Authorization") String jwt) throws UserException, ProductException{
		 
        User user = userService.findUserProfileByJwt(jwt);
    	
    	Ratings ratings = ratingService.createRating(req, user);
    	
    	return new ResponseEntity<Ratings>(ratings,HttpStatus.CREATED);
	}
	
	@GetMapping("product/{productId}")
	public ResponseEntity<List<Ratings>>  getProductsRating(@PathVariable long productId,
			@RequestHeader("Authorization") String jwt) throws UserException, ProductException{
	 
		User user = userService.findUserProfileByJwt(jwt);
		
		List<Ratings> ratings = ratingService.getProductRating(productId);
		return new ResponseEntity<List<Ratings>>(ratings,HttpStatus.CREATED);
	}
}
