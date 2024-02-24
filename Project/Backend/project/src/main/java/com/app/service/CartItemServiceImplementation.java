package com.app.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.app.Exception.CartItemException;
import com.app.Exception.UserException;
import com.app.model.Cart;
import com.app.model.CartItem;
import com.app.model.Product;
import com.app.model.User;
import com.app.repositry.CartItemRepository;
import com.app.repositry.CartRepository;

@Service
public class CartItemServiceImplementation implements CartItemService{

	@Autowired
	private CartItemRepository cartItemRepository;
	private CartRepository cartRepository;
	private UserService userService;
	private CartService cartService;
	

	public CartItemServiceImplementation(@Lazy CartItemRepository cartItemRepository, @Lazy CartRepository cartRepository, @Lazy UserService userService, @Lazy CartService cartService) {
		this.cartItemRepository = cartItemRepository;
		this.cartRepository = cartRepository;
		this.userService = userService;
		this.cartService = cartService;
	}
	
	@Override
	public CartItem createCartItem(CartItem cartItem) {
		// TODO Auto-generated method stub
		cartItem.setQuantity(1);
		cartItem.setPrice(cartItem.getProduct().getPrice() * cartItem.getQuantity());
		cartItem.setDiscountPrice(cartItem.getProduct().getDiscountPrice() * cartItem.getQuantity());
		
		CartItem createdCartItem = cartItemRepository.save(cartItem);
		return createdCartItem;

	}

	@Override
	public CartItem updateCartItem(int userId, int id, CartItem cartItem) throws CartItemException, UserException {
		CartItem item = findCartItemById(id);
		User user = userService.findUserBy(userId);
		
		if(user.getId()==(userId)) {
			item.setQuantity(cartItem.getQuantity());
			item.setPrice(item.getQuantity() * item.getProduct().getPrice());
			item.setDiscountPrice(item.getProduct().getDiscountPrice() * item.getQuantity());
		}
		return cartItemRepository.save(item);
	}

	@Override
	public CartItem isCartItemExist(Cart cart, Product product, int userId) {
		System.out.println("In cart item exist method");
		CartItem cartItem = cartItemRepository.isCartItemExist(cart, product, userId);
		System.out.println("below cart item exist method");
		return cartItem;
	}

	@Override
	public void removeCartItem(int userId, int cartItemId) throws CartItemException, UserException {
		CartItem cartItem = findCartItemById(cartItemId);
		User user = userService.findUserBy(cartItem.getId());
		
		User reqUser = userService.findUserBy(userId);
		
		if(user.getId()==reqUser.getId()) {
			cartItemRepository.deleteById(cartItemId);
		}
		else {
			throw new UserException("You can not remove another users Item");
		}
	}

	@Override
	public CartItem findCartItemById(int cartItemId) throws CartItemException {
		Optional<CartItem> opt = cartItemRepository.findById(cartItemId);
		
		if(opt.isPresent()) {
			return opt.get();
		}
		else {
			throw new CartItemException("CartItem not found with id: "+ cartItemId);
		}
	}

}
