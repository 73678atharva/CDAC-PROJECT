package com.app.repositry;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.app.model.Ratings;

public interface RatingRepository extends JpaRepository<Ratings, Long>{

	@Query("SELECT r from Ratings r WHERE r.product.id=: productId")
	public List<Ratings> getAllProductsRating(@Param("productId") Long productId);
}
