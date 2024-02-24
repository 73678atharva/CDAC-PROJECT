package com.app.service;

import java.util.List;

import com.app.Exception.ProductException;
import com.app.model.Ratings;
import com.app.model.User;
import com.app.request.RatingRequest;

public interface RatingService{

	public Ratings createRating(RatingRequest req, User user)throws ProductException;
	
	public List<Ratings> getProductRating(Long productId);
}
