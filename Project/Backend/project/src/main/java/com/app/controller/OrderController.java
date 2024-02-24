package com.app.controller;

import java.util.List;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.Exception.OrderException;
import com.app.Exception.UserException;
import com.app.model.Address;
import com.app.model.Order;
import com.app.model.User;
import com.app.service.OrderService;
import com.app.service.UserService;

@RestController
@RequestMapping("/orders")
public class OrderController {

	@Autowired
	private OrderService orderService;

	@Autowired
	private UserService userService;
	
	@PostMapping("/")
    public ResponseEntity<Order> createOrder(@RequestBody Address shippingAddress,
    		@RequestHeader("Authorization") String jwt) throws UserException{
    	
		System.out.println("ordeers");
    	User user = userService.findUserProfileByJwt(jwt);
    	
    	System.out.println("users");
    	Order order = orderService.createOrder(user, shippingAddress);
    	
    	System.out.println("order");
    	
    	return new ResponseEntity<Order>(order,HttpStatus.CREATED);
    }
    
	@GetMapping("/user")
	public ResponseEntity<List<Order>> usersOrderHistory(
			@RequestHeader("Authorization")String jwt) throws UserException{
		
		User user = userService.findUserProfileByJwt(jwt);
    	
    	List<Order> order = orderService.usersOrderHistory(user.getId());
    	return new ResponseEntity<>(order,HttpStatus.CREATED);
	}
	
	@GetMapping("/{Id}")
	public ResponseEntity<Order> findOrderById(
			@PathVariable("Id") int orderId,
			@RequestHeader("Authorization") String jwt) throws UserException, OrderException{
		
		
		User user = userService.findUserProfileByJwt(jwt);
    	
    	Order order = orderService.findOrderById(orderId);
   
    	Hibernate.initialize(order.getOrderItems());
    	
    	return new ResponseEntity<Order>(order,HttpStatus.ACCEPTED);
	}
}
