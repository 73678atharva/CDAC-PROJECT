package com.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.Exception.ProductException;
import com.app.Exception.UserException;
import com.app.model.Cart;
import com.app.model.User;
import com.app.request.AddItemRequest;
import com.app.response.ApiResponse;
import com.app.service.CartService;
import com.app.service.UserService;

@RestController
@RequestMapping("/cart")
public class CartController {

	@Autowired
	private CartService cartService;
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/")
	public ResponseEntity<Cart> findUserCart(@RequestHeader("Authorization") String jwt) throws UserException{
		User user = userService.findUserProfileByJwt(jwt);
		Cart cart = cartService.findUserCart(user.getId());
		
		return new ResponseEntity<Cart>(cart,HttpStatus.OK);
	}
	
	@PutMapping("/add")
    public ResponseEntity<ApiResponse> addItemToCart(@RequestBody AddItemRequest req,
	@RequestHeader("Authorization") String jwt)throws UserException,ProductException{
		
		User user = userService.findUserProfileByJwt(jwt);
		System.out.println("cart controller above");
		
		cartService.addCartItem(user.getId(), req);
        System.out.println("cart controller");
        
		ApiResponse res = new ApiResponse();
		res.setMessage("Item Added To Cart");
		res.setStatus(true);
		return new ResponseEntity<>(res,HttpStatus.OK);	
	}
    
	
    
}
