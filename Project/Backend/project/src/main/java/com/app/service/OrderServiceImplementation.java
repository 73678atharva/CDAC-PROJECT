package com.app.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.Exception.OrderException;
import com.app.Exception.UserException;
import com.app.model.Address;
import com.app.model.Cart;
import com.app.model.CartItem;
import com.app.model.Order;
import com.app.model.OrderItem;
import com.app.model.User;
import com.app.repositry.AddressRepository;
import com.app.repositry.CartRepository;
import com.app.repositry.OrderItemRepository;
import com.app.repositry.OrderRepository;
import com.app.repositry.UserRepository;

@Service
public class OrderServiceImplementation implements OrderService{

	@Autowired
	private CartRepository cartRepository;
	//private CartItemService cartItemService;
	@Autowired
	private CartService cartService;
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private AddressRepository addressRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private OrderItemRepository orderItemRepository;
	
	@Autowired
	private OrderRepository orderRepository;
	
	//CartItemService cartItemService,
	public OrderServiceImplementation(CartRepository cartRepository,ProductService productService,
			AddressRepository addressRepository,UserRepository userRepository, CartService cartService, OrderItemRepository orderItemRepository,
			OrderRepository orderRepository) {
		this.cartRepository = cartRepository;
		//this.cartItemService = cartItemService;
		this.productService = productService;
		this.addressRepository = addressRepository;
		this.userRepository = userRepository;
		this.orderItemRepository = orderItemRepository;
		this.orderRepository = orderRepository;
	}
	@Override
	public Order createOrder(User user, Address shippingAddress) throws UserException {
	
		shippingAddress.setUser(user);
		Address address = addressRepository.save(shippingAddress);
		user.getAddress().add(address);
		userRepository.save(user);
		
		Cart cart = cartService.findUserCart(user.getId());
		List<OrderItem> orderItems = new ArrayList<>();
		
		for(CartItem item: cart.getCartItems()) {
			OrderItem orderItem  = new OrderItem();
			
			orderItem.setPrice(item.getPrice());
			orderItem.setProduct(item.getProduct());
			orderItem.setQuantity(item.getQuantity());
			orderItem.setUserId(user.getId());
			orderItem.setDiscountPrice(cart.getDiscount());
			
			OrderItem createdOrderItem = orderItemRepository.save(orderItem);
			
			orderItems.add(createdOrderItem);
		}
		
		System.out.println(orderItems);
		Order createdOrder = new Order();
		createdOrder.setUser(user);
		createdOrder.setOrderItems(orderItems);
		createdOrder.setTotalPrice(cart.getTotalPrice());
		createdOrder.setTotalDiscountPrice(cart.getTotalDiscountPrice());
		createdOrder.setDiscount(cart.getDiscount());
		createdOrder.setTotalItems(cart.getTotalItem());
		
		createdOrder.setShippingAddress(address);
		createdOrder.setOrderDate(LocalDateTime.now());
		createdOrder.setOrderStatus("PENDING");
		createdOrder.getPaymentdetails().setStatus("PENDING");
		createdOrder.setCreatedAt(LocalDateTime.now());
		
		Order savedOrder = orderRepository.save(createdOrder);
		
		for(OrderItem item: orderItems) {
			item.setOrder(savedOrder);
			orderItemRepository.save(item);
		}
		return savedOrder;
	}

	@Override
	public List<Order> usersOrderHistory(int userId) {
		List<Order> orders = orderRepository.getUsersOrders(userId);
		for (Order order : orders) {
			int items = order.getTotalItems();
			double price = order.getTotalPrice();
			int discountPrice = order.getTotalDiscountPrice();
			int discount = order.getDiscount();
			LocalDateTime createdAt = order.getCreatedAt(); 
			LocalDateTime delivery = order.getDeliveryDate();
			
			order.setTotalItems(items);
			order.setTotalPrice(price);
			order.setTotalDiscountPrice(discountPrice);
			order.setDiscount(discount);
			order.setCreatedAt(createdAt);
			order.setDeliveryDate(delivery);
		}
		return orders;
	}

	@Override
	public Order placedOrder(int orderId) throws OrderException {
		Order order = findOrderById(orderId);
		order.setOrderStatus("PLACED");
		order.getPaymentdetails().setStatus("COMPLETED");
		return order;
	}

	@Override
	public Order confirmed(int orderId) throws OrderException {
		Order order = findOrderById(orderId);
		order.setOrderStatus("CONFIRMED");
		return orderRepository.save(order);
	}

	@Override
	public Order shippedOrder(int orderId) throws OrderException {
		Order order = findOrderById(orderId);
		order.setOrderStatus("SHIPPED");
		return orderRepository.save(order);
	}

	@Override
	public Order deliveredOrder(int orderId) throws OrderException {
		Order order = findOrderById(orderId);
		order.setOrderStatus("DELIVERED");
		return orderRepository.save(order);
	}

	@Override
	public Order cancelOrder(int orderId) throws OrderException {
		Order order = findOrderById(orderId);
		order.setOrderStatus("CANCELLED");
		return orderRepository.save(order);
	}


	@Override
	public Order findOrderById(int orderId) throws OrderException {
		Optional<Order> opt = Optional.of(orderRepository.getById(orderId));
		
		if(opt.isPresent()) {
			return opt.get();
		}
		throw new OrderException("Order Not Exist with id"+ orderId);
	}

	@Override
	public List<Order> getAllOrders() {
		return orderRepository.findAll();
	}

	@Override
	public void deleteOrder(int orderId) throws OrderException {
		Order order = findOrderById(orderId);
		orderRepository.deleteById(orderId);	
	}
}
