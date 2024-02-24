package com.app.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.app.Exception.ProductException;
import com.app.model.Product;
import com.app.model.Ratings;
import com.app.model.User;
import com.app.repositry.RatingRepository;
import com.app.request.RatingRequest;

@Service
public class RatingServiceImplementation implements RatingService{

	private RatingRepository ratingRepository;
	private ProductService productService;
	
	public RatingServiceImplementation(RatingRepository ratingRepository,ProductService productService ) {
		this.ratingRepository = ratingRepository;
		this.productService = productService;
	}
	@Override
	public Ratings createRating(RatingRequest req, User user) throws ProductException {
		Product product = productService.findProductById(req.getProductId());
		
		Ratings ratings = new Ratings();
		ratings.setProduct(product);
		ratings.setUser(user);
		ratings.setRating(req.getRating());
		ratings.setCreatedAt(LocalDateTime.now());
		return ratingRepository.save(ratings);
	}

	@Override
	public List<Ratings> getProductRating(Long productId) {
		
		return ratingRepository.getAllProductsRating(productId);
	}

}
