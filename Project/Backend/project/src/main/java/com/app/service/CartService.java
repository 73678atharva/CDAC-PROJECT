package com.app.service;

import com.app.Exception.ProductException;
import com.app.Exception.UserException;
import com.app.model.Cart;
import com.app.model.User;
import com.app.request.AddItemRequest;

public interface CartService {

	public Cart createCart(User user);
	
	public String addCartItem(int userId, AddItemRequest req)throws ProductException;
	
	public Cart findUserCart(int userId) throws UserException;
}
