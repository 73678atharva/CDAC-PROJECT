package com.app.service;

import org.hibernate.internal.build.AllowSysOut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.app.Exception.ProductException;
import com.app.Exception.UserException;
import com.app.model.Cart;
import com.app.model.CartItem;
import com.app.model.Product;
import com.app.model.User;
import com.app.repositry.CartItemRepository;
import com.app.repositry.CartRepository;
import com.app.repositry.UserRepository;
import com.app.request.AddItemRequest;

@Service
public class CartServiceImplementation implements CartService{

	@Autowired
	private CartRepository cartRepository;
	
	@Autowired
	private CartItemRepository cartItemRepository;
	
	@Autowired
	private CartItemService cartItemService;
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private UserRepository userRepository;
	
	public CartServiceImplementation(CartRepository cartRepository,CartItemRepository cartItemRepository, ProductService productService, CartItemService cartItemService, UserRepository userRepository) {
		this.cartRepository = cartRepository;
		this.cartItemRepository = cartItemRepository;
		this.productService = productService;
		this.cartItemService = cartItemService;
		this.userRepository  = userRepository;
	}
	
	@Override
	public Cart createCart(User user) {
		Cart cart = new Cart();
		cart.setUser(user);
		return cartRepository.save(cart);
	}
	
	@Override
	public String addCartItem(int userId, AddItemRequest req) throws ProductException {
		System.out.println(userId);
		System.out.println("In add cartietm");
		
		Cart cart = cartRepository.findByUserId(userId);
		
		System.out.println("cart id"+cart.getId());
		
		Product product =productService.findProductById(req.getProductId());
		System.out.println(product.getId());
		
		//CartItem isPresent = cartItemService.isCartItemExist(cart, product, userId);
		//System.out.println("cartitem");
		//if(isPresent==null) {
			CartItem cartItem = new CartItem();
			cartItem.setProduct(product);
			cartItem.setCart(cart);
			cartItem.setQuantity(req.getQuantity());
			cartItem.setUserId(userId);
			
			
			int price = req.getQuantity() * product.getDiscountPrice();
			cartItem.setPrice(price);
			
			CartItem createdCartItem = cartItemService.createCartItem(cartItem);
			cart.getCartItems().add(createdCartItem);
		
		return "Items Added To Cart";
	}

	@SuppressWarnings("unused")
	@Override
	public Cart findUserCart(int userId) throws UserException, UsernameNotFoundException {
	
		Cart cart = cartRepository.findByUserId(userId);
        System.out.println("cart id" + cart.getId());
//	    if (cart == null) {
//	    	System.out.println("cart is null so in if methods");
//	        cart = new Cart();
//	        User user = userRepository.findById(userId).orElseThrow(() -> new UsernameNotFoundException("User not found with ID: " + userId));
//	        cart.setUser(user);
//	        return cartRepository.save(cart);
//	    }
	     if(cart!=null) {
	    	 System.out.println("cart is not null so in else methods");
	    	int totalPrice = 0;
			int totalDiscountPrice =0;
			int totalItem = 0;
			
			for(CartItem cartItem :cart.getCartItems()) {
				totalPrice = totalPrice + cartItem.getPrice();
				totalDiscountPrice = totalDiscountPrice + cartItem.getDiscountPrice();
				totalItem = totalItem +  cartItem.getQuantity();
			
				System.out.println(totalItem);
			cart.setTotalDiscountPrice(totalDiscountPrice);
			cart.setTotalPrice(totalPrice);
			cart.setTotalItem(totalItem);
			cart.setDiscount(totalPrice - totalDiscountPrice);
			}
			System.out.println("cart is null");
	    }
		return cartRepository.save(cart);
		
	}

	
}
