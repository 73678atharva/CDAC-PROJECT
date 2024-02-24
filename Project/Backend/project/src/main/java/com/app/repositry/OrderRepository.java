package com.app.repositry;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.app.model.Order;

public interface OrderRepository extends JpaRepository<Order, Integer>{

	@Query("SELECT o FROM Order o WHERE o.user.id = :userId AND (o.orderStatus = 'PLACED' OR o.orderStatus = 'CONFIRMED' OR o.orderStatus = 'SHIPPED' OR o.orderStatus = 'DELIVERED' OR o.orderStatus = 'PENDING')")
	public List<Order> getUsersOrders(@Param("userId") Integer userId);
	
//	@Query("SELECT o FROM Order o WHERE o.id = :userId AND (o.orderStatus = 'PLACED' OR o.orderStatus = 'CONFIRMED' OR o.orderStatus = 'SHIPPED' OR o.orderStatus = 'DELIVERED')")
//	public List<Order> getUsersOrders(@Param("userId") Integer userId);

}
