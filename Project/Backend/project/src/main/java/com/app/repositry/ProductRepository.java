package com.app.repositry;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.app.model.Product;

public interface ProductRepository extends JpaRepository<Product, Integer>{
	
//	@Query("SELECT p FROM Product p " + 
//	           "WHERE (p.minPrice IS NULL AND :maxPrice IS NULL) OR (p.discountPrice BETWEEN :minPrice AND :maxPrice) " +
//	           "AND (:minDiscount IS NULL OR p.discountPercent >= :minDiscount) " +
//	           "ORDER BY " +
//	           "CASE WHEN :sort = 'price_low' THEN p.discountPrice END ASC, " +
//	           "CASE WHEN :sort = 'price_high' THEN p.discountPrice END DESC")
//	    public List<Product> filterProducts(
//	            @Param("minPrice") Integer minPrice,
//	            @Param("maxPrice") Integer maxPrice,
//	            @Param("minDiscount") Integer minDiscount,
//	            @Param("sort") String sort
//	    );

	@Query("SELECT p FROM Product p where p.quantity = quantity")
	    public List<Product> filterProducts(@Param("quantity") Integer quantity );
	
	@Query("SELECT p FROM Product p where p.id =:productId")
    public Optional<Product> findById(@Param("productId") Long productId );
}
