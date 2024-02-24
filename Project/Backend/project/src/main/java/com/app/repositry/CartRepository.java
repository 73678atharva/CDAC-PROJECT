package com.app.repositry;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.app.model.Cart;

public interface CartRepository extends JpaRepository<Cart, Integer> {
	
	@Query("SELECT c FROM Cart c  WHERE c.user.id = :userId")
	//@Query("SELECT c FROM Cart c JOIN c.user u WHERE u.id = :userId")
	public Cart findByUserId(@Param("userId") int userId);
	
//    
//    @Query("SELECT c FROM Cart c WHERE c.user.id =: userId")
//    public Cart findByUserId(@Param("userId") int userId);
}
