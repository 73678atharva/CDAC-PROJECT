package com.app.service;


import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.app.Exception.CartItemException;
import com.app.Exception.UserException;
import com.app.model.Cart;
import com.app.model.CartItem;
import com.app.model.Product;


public interface CartItemService {

	public CartItem createCartItem(CartItem cartItem);
	
	public CartItem updateCartItem(int userId, int id, CartItem cartItem) throws CartItemException, UserException;
	
	public CartItem isCartItemExist(Cart cart, Product product, int userId);
	
	public void removeCartItem(int userId, int cartItemId) throws CartItemException, UserException;
	
	public CartItem findCartItemById(int cartItemId)throws CartItemException;
	
	
	
	
}
