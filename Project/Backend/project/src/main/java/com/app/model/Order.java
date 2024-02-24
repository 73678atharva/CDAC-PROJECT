package com.app.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="`order`")
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Column(name="`order_id`")
	private String orderId;
	
	@ManyToOne
	private User user;
	
	@OneToMany(mappedBy = "order",cascade = CascadeType.ALL)
	private List<OrderItem> orderItems = new ArrayList<>();
	
	private LocalDateTime orderDate;
	
	private LocalDateTime deliveryDate;
	
	@OneToOne
	private Address shippingAddress;
	
	@Embedded
	private PaymentDetails paymentdetails = new PaymentDetails();
	
	private double totalPrice;
	
	private Integer totalDiscountPrice;
	
	private Integer discount;
	
	private String orderStatus;
	
	private int totalItems;
	
	private LocalDateTime createdAt;

	
	public Order() {}

	public Order(int id, String orderId, User user, List<OrderItem> orderItems, LocalDateTime orderDate,
			LocalDateTime deliveryDate, Address shippingAddress, PaymentDetails paymentdetails, double totalPrice,
			Integer totalDiscountPrice, Integer discount, String orderStatus, int totalItems,
			LocalDateTime createdAt) {
		super();
		this.id = id;
		this.orderId = orderId;
		this.user = user;
		this.orderItems = orderItems;
		this.orderDate = orderDate;
		this.deliveryDate = deliveryDate;
		this.shippingAddress = shippingAddress;
		this.paymentdetails = paymentdetails;
		this.totalPrice = totalPrice;
		this.totalDiscountPrice = totalDiscountPrice;
		this.discount = discount;
		this.orderStatus = orderStatus;
		this.totalItems = totalItems;
		this.createdAt = createdAt;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<OrderItem> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(List<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}

	public LocalDateTime getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(LocalDateTime orderDate) {
		this.orderDate = orderDate;
	}

	public LocalDateTime getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(LocalDateTime deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public Address getShippingAddress() {
		return shippingAddress;
	}

	public void setShippingAddress(Address shippingAddress) {
		this.shippingAddress = shippingAddress;
	}

	public PaymentDetails getPaymentdetails() {
		return paymentdetails;
	}

	public void setPaymentdetails(PaymentDetails paymentdetails) {
		this.paymentdetails = paymentdetails;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public Integer getTotalDiscountPrice() {
		return totalDiscountPrice;
	}

	public void setTotalDiscountPrice(Integer totalDiscountPrice) {
		this.totalDiscountPrice = totalDiscountPrice;
	}

	public Integer getDiscount() {
		return discount;
	}

	public void setDiscount(Integer discount) {
		this.discount = discount;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public int getTotalItems() {
		return totalItems;
	}

	public void setTotalItems(int totalItems) {
		this.totalItems = totalItems;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}	
}
