package com.app.model;


import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class OrderItem {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@JsonIgnore
	@ManyToOne
	private Order order;
	
	@ManyToOne
	private Product product;
	
	private int quantity;
	
	private Integer price;
	
	private Integer discountPrice;
	
	private int userId;
	
	private LocalDateTime deliveryDate; 
	
	public OrderItem() {}

	
	public OrderItem(int id, Order order, Product product, int quantity, Integer price, Integer discountPrice,
			int userId, LocalDateTime deliveryDate) {
		super();
		this.id = id;
		this.order = order;
		this.product = product;
		this.quantity = quantity;
		this.price = price;
		this.discountPrice = discountPrice;
		this.userId = userId;
		this.deliveryDate = deliveryDate;
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public Integer getDiscountPrice() {
		return discountPrice;
	}

	public void setDiscountPrice(Integer discountPrice) {
		this.discountPrice = discountPrice;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public LocalDateTime getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(LocalDateTime deliveryDate) {
		this.deliveryDate = deliveryDate;
	}
	
	
}

