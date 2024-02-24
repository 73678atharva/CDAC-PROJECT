package com.app.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.app.Exception.OrderException;
import com.app.Exception.UserException;
import com.app.model.Address;
import com.app.model.Order;
import com.app.model.User;

public interface OrderService {
	
	public Order createOrder(User user, Address shippingAddress) throws UserException;
	
	public Order findOrderById(int orderId) throws OrderException;
	
	public List<Order> usersOrderHistory(int userId);
	
	public Order placedOrder(int orderId) throws OrderException;
	
	public Order confirmed(int orderId) throws OrderException;

	public Order shippedOrder(int orderId) throws OrderException;
	
	public Order deliveredOrder(int orderId) throws OrderException;
	
	public Order cancelOrder(int orderId) throws OrderException;
	
	public List<Order> getAllOrders();
	
	public void deleteOrder(int orderId)throws OrderException;
}
