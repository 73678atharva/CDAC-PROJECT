package com.app.repositry;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.app.model.Cart;
import com.app.model.CartItem;
import com.app.model.Product;
import com.app.model.User;

public interface CartItemRepository extends JpaRepository<CartItem, Integer> {
	
//	@Query("SELECT ci FROM CartItem ci WHERE ci.cart = :cart AND ci.product = :product AND ci.userId = :userId")
//	public CartItem isCartItemExist(@Param("cart") Cart cart, @Param("product") Product product,
//	        @Param("userId") int userId);
//	
	@Query("SELECT c FROM CartItem ci JOIN ci.cart c JOIN ci.product p WHERE c = :cart AND p = :product AND ci = :userId")
    public CartItem isCartItemExist(@Param("cart") Cart cart, @Param("product") Product product,
  		@Param("userId") int userId);
	

	
//	@Query("select c from cart_item as ci inner join cart as c inner join product as p on ci.id = c.id and ci.product_id = p.id where ci.cart_id = c.id and ci.product_id = p.id")
}
