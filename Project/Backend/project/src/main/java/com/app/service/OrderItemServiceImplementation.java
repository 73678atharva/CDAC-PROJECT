package com.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.model.OrderItem;
import com.app.repositry.OrderItemRepository;

@Service
public class OrderItemServiceImplementation implements OrderItemService{

	@Autowired
	private OrderItemRepository orderItemRepository;

	
	public OrderItemServiceImplementation(OrderItemRepository orderItemRepository) {
		this.orderItemRepository = orderItemRepository;
	}
	@Override
	public OrderItem createOrderItem(OrderItem orderItem) {
		// TODO Auto-generated method stub
		return orderItemRepository.save(orderItem);
	}

}
